package com.pb.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Trip;
import com.pb.entity.User;
import com.pb.json.ActiveUserInfo;
import com.pb.json.BaseJson;
import com.pb.json.BikeUseTime;
import com.pb.json.RideTimes;
import com.pb.json.UserIncreaseInfo;
import com.pb.json.UserInfoForAndroid;
import com.pb.json.UserInfoForWeb;
import com.pb.services.user.UserService;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<User> result = userService.login(username, password);
		if (result.size() == 0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("密码错误！");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("登陆成功！");
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String phonenumber = request.getParameter("phonenumber");
		String password = request.getParameter("password");
		List<UserInfoForAndroid> result = userService.register(phonenumber, password);
		if (result.size() == 0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("注册失败！");
		} else if (result.get(0).getErrMsg() == null) {

			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("注册成功！");
			bj.setErrorMsg(result.get(0).getErrMsg());
		} else {
			bj.setRetcode("0001");
			bj.setObj(result);
			bj.setErrorMsg(result.get(0).getErrMsg());
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 分页查询用户列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getUserByPage", method = { RequestMethod.POST })
	@ResponseBody
	public String getUserByPage(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String PNO = request.getParameter("PNO");
		String PSIZE = request.getParameter("PSIZE");
		List<UserInfoForWeb> result = userService.getUserByPage(PNO, PSIZE);
		if (result.size() == 0) {
			bj.setRetcode("0002");
			bj.setErrorMsg("超出范围！");
		} else if (result.size() < Integer.parseInt(PSIZE)) {
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("最后一页");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
		}

		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 * 分页查询黑名单用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getBlackList", method = { RequestMethod.POST })
	@ResponseBody
	public String getBlackList(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String PNO = request.getParameter("PNO");
		String PSIZE = request.getParameter("PSIZE");
		List<UserInfoForWeb> result = userService.getBlackUserByPage(PNO, PSIZE);
		if (result.size() == 0) {
			bj.setRetcode("0002");
			bj.setErrorMsg("超出范围！");
		} else if (result.size() < Integer.parseInt(PSIZE)) {
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("最后一页");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 拉黑用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String userid = request.getParameter("userid");
		if (userid == null) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
			return JsonUtil.getInstance().obj2json(bj);
		}
		boolean result = userService.deleteUser(userid);
		if (!result) {
			bj.setRetcode("0002");
			bj.setErrorMsg("不存在该用户！");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 恢复黑名单用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userBack", method = { RequestMethod.POST })
	@ResponseBody
	public String userBack(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String userid = request.getParameter("userid");
		if (userid == null) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
			return JsonUtil.getInstance().obj2json(bj);
		}
		boolean result = userService.userBack(userid);
		if (!result) {
			bj.setRetcode("0002");
			bj.setErrorMsg("不存在该用户！");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
		}

		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 *	获取用户最近一周内的骑行次数
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userRideTimes", method = { RequestMethod.POST })
	@ResponseBody
	public String userRideTimes(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String userid = request.getParameter("userid");
		if (userid == null) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
			return JsonUtil.getInstance().obj2json(bj);
		}
		List<RideTimes> result = userService.userRideTimes(userid);
		if(result.size()==0){
			bj.setErrorMsg("用户不存在！");
			bj.setRetcode("0001");
		}else{
			bj.setErrorMsg("获取成功！");
			bj.setObj(result);
			bj.setRetcode("0000");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 * 獲取用戶騎行時長的統計數據信息
	 * 
	 * @param userid
	 * @return json字符串
	 */
	@RequestMapping(value = "/getBikeUseTime", method = { RequestMethod.POST })
	@ResponseBody
	public String getBikeUseTime(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String userid = request.getParameter("userid");
		if (userid == null) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
			return JsonUtil.getInstance().obj2json(bj);
		}
		List<BikeUseTime> result = userService.getBikeUseTime(userid);
		if(result.size()==0){
			bj.setErrorMsg("用户不存在！");
			bj.setRetcode("0001");
		}else{
			bj.setErrorMsg("获取成功！");
			bj.setObj(result);
			bj.setRetcode("0000");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 * 獲取用戶骑行起止点
	 * 
	 * @param userid
	 * @return json字符串
	 */
	@RequestMapping(value = "/getRideBeginAndEnd", method = { RequestMethod.POST })
	@ResponseBody
	public String getRideBeginAndEnd(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String userid = request.getParameter("userid");
		if (userid == null) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
			return JsonUtil.getInstance().obj2json(bj);
		}
		List<Trip> result = userService.getRideBeginAndEnd(userid);
		if(result.size()==0){
			bj.setErrorMsg("用户不存在或无历史记录！");
			bj.setRetcode("0001");
		}else{
			bj.setErrorMsg("获取成功！");
			bj.setObj(result);
			bj.setRetcode("0000");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 * 獲取用戶性別統計信息
	 * 
	 * @param userid
	 * @return json字符串
	 */
	@RequestMapping(value = "/getUserGender", method = { RequestMethod.POST })
	@ResponseBody
	public String getUserGender(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String result = userService.getUserGender();
		if(!result.contains(":")){
			bj.setErrorMsg("查询出错");
			bj.setRetcode("0001");
		}else{
			bj.setErrorMsg("获取成功！");
			bj.setObj(result);
			bj.setRetcode("0000");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 * 獲取用戶近期增长情况统计
	 * 
	 * @param userid
	 * @return json字符串
	 */
	@RequestMapping(value = "/getUserIncrease", method = { RequestMethod.POST })
	@ResponseBody
	public String getUserIncrease(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		List<UserIncreaseInfo> result = userService.getUserIncrease();
		
		bj.setErrorMsg("获取成功！");
		bj.setObj(result);
		bj.setRetcode("0000");
		
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 * 獲取最近一周的活躍用戶數
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getActiveUser", method = { RequestMethod.POST })
	@ResponseBody
	public String getActiveUser(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		List<ActiveUserInfo> result = userService.getActiveUser();
		
		bj.setErrorMsg("获取成功！");
		bj.setObj(result);
		bj.setRetcode("0000");
		
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	
	@RequestMapping(value = "/editUser", method = { RequestMethod.POST })
	@ResponseBody
	public String editUser(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String userid = request.getParameter("userid");
		String gender = request.getParameter("gender");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		if (userid == null||gender == null||name ==null ||phone==null) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求数据不能为空！");
			return JsonUtil.getInstance().obj2json(bj);
		}
		String result = userService.editUser(userid, gender, name, phone);
		if (result=="0003") {
			bj.setRetcode("0003");
			bj.setErrorMsg("不存在该用户！");
		}else if(result =="0002"){
			bj.setRetcode("0002");
			bj.setErrorMsg("手机号已存在！");
		}
		else {
			bj.setRetcode("0000");
			bj.setObj(result);
		}

		return JsonUtil.getInstance().obj2json(bj);
	}
}
