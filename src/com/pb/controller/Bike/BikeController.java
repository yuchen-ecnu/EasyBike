package com.pb.controller.Bike;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Mobike;
import com.pb.entity.Test;
import com.pb.json.BaseJson;
import com.pb.json.BikeInfo;
import com.pb.json.MobikeDistanceJson;
import com.pb.json.MobikeTraceCountJson;
import com.pb.json.MobikeTypeJson;
import com.pb.json.UserInfoForWeb;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/bike")
public class BikeController extends BaseController {
	@RequestMapping(value = "/getAllBike", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllBike(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		List<BikeInfo> list = bikeService.getBikeInfo();

		bj.setObj(list);
		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 分页查询单车列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getBikeByPage", method = { RequestMethod.POST })
	@ResponseBody
	public String getBikeByPage(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String PNO = request.getParameter("PNO");
		String PSIZE = request.getParameter("PSIZE");
		if(PNO==null || PSIZE==null ){
			bj.setRetcode("0003");
			bj.setErrorMsg("未传参数!");
			return JsonUtil.getInstance().obj2json(bj);
		}
		List<BikeInfo> result = bikeService.getBikeByPage(PNO, PSIZE);
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
	
	@RequestMapping(value = "/unlockBike", method = { RequestMethod.POST })
	@ResponseBody
	public String unlockBike(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String bikeId = request.getParameter("bikeId");
		String result = bikeService.unlockBike(bikeId);
		if (result.equals("0001")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("车辆不存在！");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("获取密码成功！");
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	// 获取摩拜单车
	@RequestMapping(value = "/getMobike", method = { RequestMethod.POST })
	@ResponseBody
	public String getMobike(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		List<MobikeTraceCountJson> list = bikeService.getMobike();

		bj.setObj(list);
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	// 获取摩拜单车
		@RequestMapping(value = "/getMobikeByPage", method = { RequestMethod.POST })
		@ResponseBody
		public String getMobikeByPage(HttpServletRequest request, HttpServletResponse response) {
			BaseJson bj = new BaseJson();
			String PNO = request.getParameter("PNO");
			String PSIZE = request.getParameter("PSIZE");
			List<MobikeTraceCountJson> list = bikeService.getMobikeByPage(PNO,PSIZE);
			if(list.size()==0) {
				bj.setRetcode("0001");
				bj.setErrorMsg("超出范围！");
			}else{
				bj.setRetcode("0000");
				bj.setObj(list);
				bj.setErrorMsg("获取数据成功！");
			}
			return JsonUtil.getInstance().obj2json(bj);
		}

	// 根据Id获取摩拜单车
	@RequestMapping(value = "/getmobikeById", method = { RequestMethod.POST })
	@ResponseBody
	public String getmobikeById(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
		} else {
			int i = Integer.parseInt(id);
			List<Mobike> list = bikeService.getmobikeById(i);
			if (list.size() == 0) {
				bj.setErrorMsg("车辆不存在！");
				bj.setRetcode("0002");
			} else {
				bj.setErrorMsg("请求成功！");
				bj.setRetcode("0000");
				bj.setObj(list);
			}
		}
		return JsonUtil.getInstance().obj2json(bj);
	}

	@RequestMapping(value = "/lockBike", method = { RequestMethod.POST })
	@ResponseBody
	public String lockBike(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String bikeId = request.getParameter("bikeId");
		String tripid = request.getParameter("tripid");
		String result = bikeService.lockBike(bikeId, tripid);
		if (result.equals("0001")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("车辆状态不正确！");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("锁车成功！");
		}

		return JsonUtil.getInstance().obj2json(bj);
	}

	@RequestMapping(value = "/modifyBikePassword", method = { RequestMethod.POST })
	@ResponseBody
	public String modifyBikePassword(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String bikeId = request.getParameter("bikeID");
		String password = request.getParameter("password");
		String result = bikeService.modifyBikePassword(bikeId, password);
		if (result == "0001") {
			bj.setRetcode("0001");
			bj.setErrorMsg("密码长度不得超过8位");
		} else {
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("修改密码成功！");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}

	@RequestMapping(value = "/batchAddition", method = { RequestMethod.POST })
	@ResponseBody
	public String batchAddition(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String bikeid = request.getParameter("bikeid");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		String result = bikeService.batchAddition(bikeid, latitude, longitude, amount, type);
		bj.setRetcode("0000");
		bj.setObj(result);
		bj.setErrorMsg("批量添加成功！");
		return JsonUtil.getInstance().obj2json(bj);

	}

	@RequestMapping(value = "/editBike", method = { RequestMethod.POST })
	@ResponseBody
	public String editBike(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String bikeId = request.getParameter("bikeID");
		String statu = request.getParameter("statu");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String type = request.getParameter("type");
		if (bikeId == null || statu == null || latitude == null || longitude == null || type == null) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求数据不能为空！");
			return JsonUtil.getInstance().obj2json(bj);
		}
		String result = bikeService.editBike(bikeId, statu, latitude, longitude, type);
		bj.setRetcode("0000");
		bj.setObj(result);
		return JsonUtil.getInstance().obj2json(bj);
	}

	@RequestMapping(value = "/deleteBike", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteBike(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String bikeId = request.getParameter("bikeID");
		String result = bikeService.deleteBike(bikeId);
		bj.setRetcode("0000");
		bj.setObj(result);
		bj.setErrorMsg("删除成功！");
		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 根据mobikeid获取mobike运行记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getMobikeTripById", method = { RequestMethod.POST })
	@ResponseBody
	public String getMobikeTripById(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String id = request.getParameter("bikeID");
		if (id == null || id.equals("")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
		} else {
			List<Test> list = bikeService.getMobikeTripById(id);
			if (list.size() == 0) {
				bj.setErrorMsg("车辆不存在轨迹！");
				bj.setRetcode("0001");
			} else {
				bj.setErrorMsg("获取成功！");
				bj.setRetcode("0000");
				bj.setObj(list);
			}
		}
		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 根据tripid获取mobike运行记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getMobikeTripByTripId", method = { RequestMethod.POST })
	@ResponseBody
	public String getMobikeTripByTripId(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		String id = request.getParameter("tripid");
		if (id == null || id.equals("")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("请求参数错误！");
		} else {
			int i = Integer.parseInt(id);
			List<Test> list = bikeService.getMobikeTripByTripId(i);
			if (list.size() == 0) {
				bj.setErrorMsg("系统出错！");
				bj.setRetcode("0001");
			} else {
				bj.setErrorMsg("获取成功！");
				bj.setRetcode("0000");
				bj.setObj(list);
			}
		}
		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 获取mobike单车的品种分布
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getMobikeTypeNumber", method = { RequestMethod.POST })
	@ResponseBody
	public String getMobikeTypeNumber(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		List<MobikeTypeJson> result = bikeService.getMobikeTypeNumber();
		if(result.size()==0){
			bj.setErrorMsg("无数据！");
			bj.setRetcode("000!");
		}else{
			bj.setErrorMsg("获取成功！");
			bj.setObj(result);
			bj.setRetcode("0000");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	/**
	 * 获取mobike单车的骑行次数统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getMobikeRideTimes", method = { RequestMethod.POST })
	@ResponseBody
	public String getMobikeRideTimes(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		List<MobikeTypeJson> result = bikeService.getMobikeRideTimes();
		if(result.size()==0){
			bj.setErrorMsg("无数据！");
			bj.setRetcode("000!");
		}else{
			bj.setErrorMsg("获取成功！");
			bj.setObj(result);
			bj.setRetcode("0000");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/getAllMobikeTripDistance", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllMobikeTrip(HttpServletRequest request, HttpServletResponse response) {
		BaseJson bj = new BaseJson();
		List<MobikeDistanceJson> result = bikeService.getAllMobikeTrip();
		if(result.size()==0){
			bj.setErrorMsg("无数据！");
			bj.setRetcode("000!");
		}else{
			bj.setErrorMsg("获取成功！");
			bj.setObj(result);
			bj.setRetcode("0000");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
}
