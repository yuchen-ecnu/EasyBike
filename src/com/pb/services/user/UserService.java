package com.pb.services.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.springframework.stereotype.Service;

import com.pb.entity.Bike;
import com.pb.entity.Trip;
import com.pb.entity.User;
import com.pb.json.ActiveUserInfo;
import com.pb.json.ActiveUserSqlJson;
import com.pb.json.BikeUseTime;
import com.pb.json.RideTimes;
import com.pb.json.UserIncreaseInfo;
import com.pb.json.UserInfoForAndroid;
import com.pb.json.UserInfoForWeb;
import com.pb.services.common.CommService;

@Service(value = "UserService")
public class UserService extends CommService {

	public List<User> login(String username, String password) {
		List<User> list = baseDAO.findBySQLForVO(
				"SELECT user_id as userId,name as name,gender as gender,password as password,phone as phone,user_type as userType FROM user WHERE phone = ? AND password = ?;",
				User.class, new Object[] { username, password });
		return list;
	}

	public List<UserInfoForAndroid> register(String phonenumber, String password) {
		List<UserInfoForAndroid> result = new ArrayList<UserInfoForAndroid>();
		List<UserInfoForAndroid> test = baseDAO.findBySQLForVO(
				"SELECT user_id as userId,name as name,gender as gender,password as password,phone as phone,user_type as userType FROM user WHERE phone = ? ;",
				UserInfoForAndroid.class, new Object[] { phonenumber });
		if (test.size() != 0) {
			UserInfoForAndroid u = new UserInfoForAndroid();
			u.setErrMsg("手机号码已注册！");
			result.add(u);
			return result;
		}
		baseDAO.executeSQL("INSERT INTO user (phone,password,user_type,registertime=NOW()) VALUES (?,?,'user');",
				new Object[] { phonenumber, password });
		result = baseDAO.findBySQLForVO(
				"SELECT user_id as userId,name as name,gender as gender,password as password,phone as phone,user_type as userType FROM user WHERE phone = ? AND password = ?;",
				UserInfoForAndroid.class, new Object[] { phonenumber, password });
		return result;
	}

	public List<UserInfoForWeb> getUserByPage(String PNO, String PSIZE) {
		int page = Integer.parseInt(PNO);
		int size = Integer.parseInt(PSIZE);
		int start = (page - 1) * size;
		List<UserInfoForWeb> result = baseDAO.findBySQLForVO(
				"SELECT user_id as userId,name as name,gender as gender,password as password,phone as phone,user_type as userType FROM user WHERE user_type = 'user' limit ?,?; ",
				UserInfoForWeb.class, new Object[] { start, size });
		return result;
	}

	public List<UserInfoForWeb> getBlackUserByPage(String PNO, String PSIZE) {
		int page = Integer.parseInt(PNO);
		int size = Integer.parseInt(PSIZE);
		int start = (page - 1) * size;
		List<UserInfoForWeb> result = baseDAO.findBySQLForVO(
				"SELECT user_id as userId,name as name,gender as gender,password as password,phone as phone,user_type as userType FROM user WHERE user_type = 'blacklist' limit ?,?; ",
				UserInfoForWeb.class, new Object[] { start, size });
		return result;
	}

	public boolean deleteUser(String userid) {
		int id = Integer.parseInt(userid);
		List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?", new Object[] { id });
		if (temp.size() == 0)
			return false;
		User u = temp.get(0);
		u.setUserType("blacklist");
		baseDAO.save(u);
		return true;
	}

	public boolean userBack(String userid) {
		int id = Integer.parseInt(userid);
		List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?", new Object[] { id });
		if (temp.size() == 0)
			return false;
		User u = temp.get(0);
		u.setUserType("user");
		baseDAO.save(u);
		return true;
	}
	
	/**
	 * 获取用户最近一周骑行次数（起始时间为当前时间）
	 * @param userid
	 * @return
	 */
	public List<RideTimes> userRideTimes(String userid) {
		int id = Integer.parseInt(userid);
		List<RideTimes> result = new ArrayList<RideTimes>();
		Date endDate = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		cl.add(Calendar.DATE, -7);
		Date startDate = cl.getTime();
		Timestamp start = new Timestamp(startDate.getTime());
		Timestamp end = new Timestamp(endDate.getTime());
		
		/**
		 * 判断是否存在用户
		 */
		List<User> users = baseDAO.findByHQL("from User user where user.userId = ?", new Object[] { id });
		if (users.size() == 0)
			return result;
		
		List<Trip> temp = baseDAO.findByHQL("from Trip t where t.user.userId = ? and startTime >= ? and startTime <= ? ", new Object[] { id ,start,end});
		int [] tmp  = {0,0,0,0,0,0,0};
		for (int i = 0; i < temp.size(); i++) {
			Trip t = temp.get(i);
			Date d = new Date();
			d=t.getStartTime();
			int week = getWeekOfDate(d);
			tmp[week]++;
		}
		for (int i = 0; i < tmp.length; i++) {
			RideTimes r = new RideTimes();
			r.setDay(i+"");
			r.setTimes(tmp[i]+"");
			result.add(r);
		}
		return result;
	}

	/**
	 * 獲取用戶的騎行時間統計數據
	 * 
	 * @param userid
	 * @return
	 */
	public List<BikeUseTime> getBikeUseTime(String userid) {
		int id = Integer.parseInt(userid);
		List<BikeUseTime> result = new ArrayList<BikeUseTime>();
		
		//檢查用戶
		List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?", new Object[] { id });
		if (temp.size() == 0)
			return result;
		
		//獲取數據
		int [] tmp  = {0,0,0,0,0,0,0,0,0};
		List<Trip> trip = baseDAO.findByHQL("FROM Trip t WHERE t.user.userId = ?",new Object[]{id});
		for (int i = 0; i < trip.size(); i++) {
			Trip t = trip.get(i);
			//獲取騎行時長
			if(t.getStartTime()==null||t.getEndTime()==null)continue;
			int during = (int) ((t.getEndTime().getTime()-t.getStartTime().getTime())/(60*1000));
			if(during>=0&&during<15) tmp[0]++;
			else if(during>=15&&during<30) tmp[1]++;
			else if(during>=30&&during<45) tmp[2]++;
			else if(during>=45&&during<60) tmp[3]++;
			else if(during>=60&&during<75) tmp[4]++;
			else if(during>=75&&during<90) tmp[5]++;
			else if(during>=90&&during<105) tmp[6]++;
			else if(during>=105&&during<120) tmp[7]++;
			else tmp[8]++;
		}
		BikeUseTime b0 = new BikeUseTime();b0.setTimeZone("0-15分钟");b0.setTimes(tmp[0]+"");result.add(b0);
		BikeUseTime b1 = new BikeUseTime();b1.setTimeZone("15-30分钟");b1.setTimes(tmp[1]+"");result.add(b1);
		BikeUseTime b2 = new BikeUseTime();b2.setTimeZone("30-45分钟");b2.setTimes(tmp[2]+"");result.add(b2);
		BikeUseTime b3 = new BikeUseTime();b3.setTimeZone("45-60分钟");b3.setTimes(tmp[3]+"");result.add(b3);
		BikeUseTime b4 = new BikeUseTime();b4.setTimeZone("1小时-1小时15分钟");b4.setTimes(tmp[4]+"");result.add(b4);
		BikeUseTime b5 = new BikeUseTime();b5.setTimeZone("1小时15分钟-1小时30分钟");b5.setTimes(tmp[5]+"");result.add(b5);
		BikeUseTime b6 = new BikeUseTime();b6.setTimeZone("1小时30分钟-1小时45分钟");b6.setTimes(tmp[6]+"");result.add(b6);
		BikeUseTime b7 = new BikeUseTime();b7.setTimeZone("1小时45分钟-2小时");b7.setTimes(tmp[7]+"");result.add(b7);
		BikeUseTime b8 = new BikeUseTime();b8.setTimeZone(">2小时");b8.setTimes(tmp[8]+"");result.add(b8);
		
		return result;
	}
	
	/**
	 * 獲取用戶骑行起止点
	 * 
	 * @param userid
	 * @return
	 */
	public List<Trip> getRideBeginAndEnd(String userid) {
		int id = Integer.parseInt(userid);
		List<Trip> result = new ArrayList<Trip>();
		
		//檢查用戶
		List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?", new Object[] { id });
		if (temp.size() == 0)
			return result;
		
		//獲取數據
		List<Trip> trip = baseDAO.findByHQL("FROM Trip t WHERE t.user.userId = ?",new Object[]{id});
		for (int i = 0; i < trip.size(); i++) {
			trip.get(i).getUser().setPassword("");
		}
		return trip;
	}
	
	/**
	 * 获取用户性别分布
	 * @return
	 */
	public String getUserGender() {
		//獲取數據
		List<User> male = baseDAO.findByHQL("FROM User user where user.gender = '男'");
		List<User> female = baseDAO.findByHQL("FROM User user where user.gender = '女'");

		return male.size()+":"+female.size();
	}

	/**
	 * 获取用户增长情况
	 * @return
	 */
	public List<UserIncreaseInfo> getUserIncrease() {
		//初始化日期
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		Calendar c= Calendar.getInstance();
		c.setTime(new Date());
		Timestamp now = new Timestamp(c.getTimeInMillis());
		c.add(Calendar.MONTH, -1);
		Timestamp oneMonthAgo = new Timestamp(c.getTimeInMillis());
		
		List<User> user = baseDAO.findByHQL("FROM User user where user.registetime > ?",new Object[]{oneMonthAgo});
		
		List<UserIncreaseInfo> result = new ArrayList<UserIncreaseInfo>();
		int []a = new int[31];
		for (int i = 0; i < a.length; i++) a[i]=0;
		for(User u : user){
			Timestamp t = u.getRegistetime();
			int day = (int) ((now.getTime()-t.getTime())/(12*60*60*1000));
			if(day>=0&&day<=31){
				a[day]++;
			}
		}
		
		Calendar cur= Calendar.getInstance();
		cur.setTime(new Date());
		for (int i = 0; i < a.length; i++) {
			UserIncreaseInfo uin = new UserIncreaseInfo();
			uin.setDate(format.format(cur.getTime()));
			uin.setNumber(a[i]);
			result.add(uin);
			cur.add(Calendar.DATE, -1);
		}
		
		return result;
	}
	
	
	public List<ActiveUserInfo> getActiveUser() {
		//初始化日期
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		Calendar c= Calendar.getInstance();
		c.setTime(new Date());
		Timestamp now = new Timestamp(c.getTimeInMillis());
		c.add(Calendar.DATE, -6);
		Timestamp oneWeekAgo = new Timestamp(c.getTimeInMillis());
		
		List<ActiveUserSqlJson> trips = baseDAO.findBySQLForVO("select distinct user_id as userid, start_time as starttime FROM Trip where start_time > ?;",
				ActiveUserSqlJson.class, new Object[]{oneWeekAgo});
		
		List<ActiveUserInfo> result = new ArrayList<ActiveUserInfo>();
		int []a = new int[7];
		for (int i = 0; i < a.length; i++) a[i]=0;
		
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
		
		for(ActiveUserSqlJson t : trips){
			Timestamp time = t.getTime();
			a[getWeekOfDate(time)]++;
		}
		
		Calendar cur= Calendar.getInstance();
		cur.setTime(new Date());
		for (int i = 0; i < a.length; i++) {
			ActiveUserInfo uin = new ActiveUserInfo();
			uin.setDate(weekDaysName[getWeekOfDate(cur.getTime())]);
			uin.setNum(a[getWeekOfDate(cur.getTime())]+"");
			result.add(uin);
			cur.add(Calendar.DATE, -1);
		}
		
		return result;
	}
	
	public String editUser(String userid, String gender, String name, String phone) {
		int id = Integer.parseInt(userid);
		List<User> tmp = baseDAO.findByHQL("from User user where user.phone = ?", new Object[] { phone });
		if (tmp.size() != 0){
			return "0002";
		}
		List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?", new Object[] { id });
		if (temp.size() == 0)
			return "0003";
		User u = temp.get(0);
		u.setName(name);
		u.setGender(gender);
		u.setPhone(phone);
		baseDAO.save(u);
		return "0000";
	}
	
	
	
	/**
	 * 辅助函数：根据日期获取星期（0表示星期日）
	 * 
	 * @param date
	 * @return
	 */
	public int getWeekOfDate(Date date){
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
		return intWeek; 
	}
}
