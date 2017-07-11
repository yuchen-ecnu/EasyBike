package com.pb.services.accusation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Bike;
import com.pb.services.common.CommService;

@Service(value = "TripService")
public class AccusationService extends CommService{
	public String commitAccusation(String userid,String bikeid,String addinfo,String accusation_msg){
		List<Bike> bikes = baseDAO.findByHQL("FROM Bike b WHERE b.bikeId = ? AND b.statues = 'lock'", new Object[]{bikeid});
		if(bikes.size()==0){
			return "0001";
		}
		else{
			baseDAO.executeSQL("INSERT INTO accusation VALUES (NULL,?,?,NOW(),?,?);",new Object[]{bikeid,accusation_msg,Integer.parseInt(userid),addinfo});
			return "0000";
		}
	}

}
