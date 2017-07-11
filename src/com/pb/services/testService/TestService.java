package com.pb.services.testService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Active;
import com.pb.services.common.CommService;

@Service(value = "testService")
public class TestService extends CommService {
	
	public List<Active> getUsers(int pno,int PAGE_SIZE){
		List<Active> result = baseDAO.findByPage("from Active a", (pno-1)*PAGE_SIZE, PAGE_SIZE);
		return result;
	}
}
