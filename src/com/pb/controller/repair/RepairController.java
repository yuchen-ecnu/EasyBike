package com.pb.controller.repair;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.json.BaseJson;
import com.pb.json.RepairInfo;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/repair")
public class RepairController extends BaseController{
	
	@RequestMapping(value = "/commitRepair", method = { RequestMethod.POST })
	@ResponseBody
	public String commitRepair (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String bikeid = request.getParameter("bikeid");
		String accusation_msg = request.getParameter("accusation_msg");
		String addinfo = request.getParameter("addinfo");
		String userid = request.getParameter("userid");
		String result = repairService.commitAccusation(userid, bikeid, addinfo, accusation_msg);
		if(result.equals("0001")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("车辆不存在！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("提交成功，感谢您的配合！");
		}
		 
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/getRepairInfo", method = { RequestMethod.POST })
	@ResponseBody
	public String getRepairInfo (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String PNO = request.getParameter("PNO");
		String PSIZE = request.getParameter("PSIZE");
		List<RepairInfo> result = repairService.getRepairInfo();
		if(result.size()==0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("无待维修车辆！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("获取数据成功！");
		}
		 
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/getRepairInfoByPage", method = { RequestMethod.POST })
	@ResponseBody
	public String getRepairInfoByPage (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String PNO = request.getParameter("PNO");
		String PSIZE = request.getParameter("PSIZE");
		List<RepairInfo> result = repairService.getRepairInfoByPage(PNO,PSIZE);
		if(result.size()==0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("无待维修车辆！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("获取数据成功！");
		}
		 
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/updateRepairInfo", method = { RequestMethod.POST })
	@ResponseBody
	public String updateRepairInfo (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		int id  = Integer.parseInt(request.getParameter("repaidId"));
		int bid  = Integer.parseInt(request.getParameter("bikeId"));
		String result = repairService.updateRepairInfo(id,bid);
		bj.setRetcode("0000");
		bj.setObj(result);
		bj.setErrorMsg("修改数据成功！");
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	@RequestMapping(value = "/updateRepairInfoBad", method = { RequestMethod.POST })
	@ResponseBody
	public String updateRepairInfoBad (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		int id  = Integer.parseInt(request.getParameter("repaidId"));
		int bid  = Integer.parseInt(request.getParameter("bikeId"));
		String result = repairService.updateRepairInfoBad(id,bid);
		bj.setRetcode("0000");
		bj.setObj(result);
		bj.setErrorMsg("修改数据成功！");
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
}
