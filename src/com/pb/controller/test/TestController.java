package com.pb.controller.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Active;
import com.pb.json.BaseJson;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	@RequestMapping(value = "/getCustomers", method = { RequestMethod.POST })
	@ResponseBody
	public String getCustomers(HttpServletRequest request,
			HttpServletResponse response){
		//System.out.println(request.getSession().getAttribute("HTTPCLIENT").toString());
		int pno = Integer.parseInt(request.getParameter("pno"));
		int PAGE_SIZE = Integer.parseInt(request.getParameter("PAGE_SIZE"));
		
		BaseJson bj = new BaseJson();
		List<Active> list = testService.getUsers(pno,PAGE_SIZE);
		System.out.println(list);
		bj.setObj(list);
		bj.setRetcode("0000");
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
}
