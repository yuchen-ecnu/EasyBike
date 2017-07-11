package com.pb.controller.accusation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.json.BaseJson;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/accusaion")
public class AccusationController extends BaseController{
	
	@RequestMapping(value = "/commitAccusation", method = { RequestMethod.POST })
	@ResponseBody
	public String commitAccusation (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String bikeid = request.getParameter("bikeid");
		String accusation_msg = request.getParameter("accusation_msg");
		String addinfo = request.getParameter("addinfo");
		String userid = request.getParameter("userid");
		String result = accusationService.commitAccusation(userid, bikeid, addinfo, accusation_msg);
		if(result.equals("0001")) {
			bj.setRetcode("0001");
			bj.setErrorMsg("车辆不存哦，请检查一下车牌号！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("提交成功，感谢您的配合！");
		}
		 
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
}
