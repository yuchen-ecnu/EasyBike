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
@RequestMapping("/testunit")
public class TestUnit extends BaseController {
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	@ResponseBody
	public String getCustomers(HttpServletRequest request,
			HttpServletResponse response) {
		int pno = Integer.parseInt(request.getParameter("pno"));
		int PAGE_SIZE = Integer.parseInt(request.getParameter("PAGE_SIZE"));

		BaseJson bj = new BaseJson();
		bj.setObj(request.getSession().getId());
		bj.setRetcode("0000");
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
}
