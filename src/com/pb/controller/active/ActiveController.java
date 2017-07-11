package com.pb.controller.active;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Active;
import com.pb.json.ActiveJson;
import com.pb.json.BaseJson;
import com.pb.json.CollectingInfo;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/active")
public class ActiveController extends BaseController {

	@RequestMapping(value = "/activeInfo", method = { RequestMethod.POST })
	@ResponseBody
	public String activeInfo(HttpServletRequest request,
			HttpServletResponse response){
		int active_id = Integer.parseInt(request.getParameter("id"));
		
		BaseJson bj = new BaseJson();
		Active active = activeService.getActiveById(active_id);
		System.out.println(active);
		bj.setObj(activeService.A2AJ(active));
		bj.setRetcode("0000");
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/ActiveList", method = { RequestMethod.POST })
	@ResponseBody
	public String ActiveList(HttpServletRequest request,
			HttpServletResponse response){
		int pno = Integer.parseInt(request.getParameter("pno"));
		int PAGE_SIZE = Integer.parseInt(request.getParameter("PAGE_SIZE"));
		
		BaseJson bj = new BaseJson();
		List<Active> list = activeService.getAllActive(pno,PAGE_SIZE);
		List<ActiveJson> ajList = new ArrayList<ActiveJson>();
		for(Active active:list){
			ajList.add(activeService.A2AJ(active));
		}
		bj.setObj(ajList);
		bj.setRetcode("0000");
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/getCollection", method = { RequestMethod.POST })
	@ResponseBody
	public String getCollection(HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String TCID = request.getParameter("TCID");
		List<CollectingInfo> list = activeService.collecting(TCID);
		bj.setObj(list);
		bj.setRetcode("0000");
		for(int i =0;i<10000;i++);
		return JsonUtil.getInstance().obj2json(bj);
	}
}
