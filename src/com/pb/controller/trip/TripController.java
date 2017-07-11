package com.pb.controller.trip;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Position;
import com.pb.json.BaseJson;
import com.pb.json.Point;
import com.pb.json.TripInfoForWeb;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/trip")
public class TripController extends BaseController {

	/**
	 * 记录轨迹前，申请tripid
	 * 
	 * @param request
	 * @param response
	 * @return trip唯一识别码：trip_id
	 */
	@RequestMapping(value = "/buildTrip", method = { RequestMethod.POST })
	@ResponseBody
	public String buildTrip(HttpServletRequest request,
			HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String bikeId = request.getParameter("bikeId");
		String userId = request.getParameter("userId");
		String result = tripService.buildTrip(userId, bikeId);
		if (result.equals("0001")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("创建记录出错，请联系管理员！");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("创建记录成功！");
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 记录轨迹点
	 * 
	 * @param request
	 * @param response
	 * @return 成功插入：retCode = 0000 插入失败：retCode = 0001
	 */
	@RequestMapping(value = "/insertPoint", method = { RequestMethod.POST })
	@ResponseBody
	public String insertPoint(HttpServletRequest request,
			HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String tripid = request.getParameter("tripid");
		String altitude = request.getParameter("altitude");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		boolean result = tripService.insertPoint(latitude, longitude, tripid,
				altitude, province, city);
		if (!result) {
			bj.setRetcode("0001");
			bj.setErrorMsg("插入點出錯，请联系管理员！");
		} else {
			bj.setRetcode("0000");
			bj.setErrorMsg("插入點成功！");
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 根据Tripid获取轨迹
	 * 
	 * @param tripid
	 * @return 成功返回List<Point> 正确码：0000 失败返回错误码：0001
	 */
	@RequestMapping(value = "/getTrace", method = { RequestMethod.POST })
	@ResponseBody
	public String getTrace(HttpServletRequest request,
			HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String tripid = request.getParameter("tripid");
		List<Point> result = tripService.getTraceByTripid(tripid);
		if (result.size() == 0) {
			bj.setErrorMsg("轨迹不存在！");
			bj.setRetcode("0001");
		} else {
			bj.setErrorMsg("获取轨迹成功！");
			bj.setRetcode("0000");
		}
		bj.setObj(result);
		System.out.println(bj);
		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 根据用户ID查找对应的所有轨迹(按照时间的先后顺序)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getTracesById", method = { RequestMethod.POST })
	@ResponseBody
	public String getTracesById(HttpServletRequest request,
			HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String userid = request.getParameter("userid");
		List<TripInfoForWeb> result = tripService.getTraceByUserid(userid);
		if (result.size() == 0) {
			bj.setErrorMsg("么有记录！");
			bj.setRetcode("0001");
		} else {
			bj.setErrorMsg("数据获取成功！");
			bj.setRetcode("0000");
		}
		bj.setObj(result);

		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/getEndPoints", method = { RequestMethod.POST })
	@ResponseBody
	public String getEndPoints (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		List<Position> result = tripService.getEndPoints();
		if(result.size()==0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("暂无数据！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("获取成功！");
		}
		 
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/getStartPoints", method = { RequestMethod.POST })
	@ResponseBody
	public String getStartPoints (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		List<Position> result = tripService.getStartPoints();
		if(result.size()==0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("暂无数据！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("获取成功！");
		}
		 
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
}
