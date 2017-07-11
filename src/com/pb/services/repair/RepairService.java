package com.pb.services.repair;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Bike;
import com.pb.json.RepairInfo;
import com.pb.services.common.CommService;

@Service(value = "RepairService")
public class RepairService extends CommService {
	public String commitAccusation(String userid,String bikeid,String addinfo,String accusation_msg){
		List<Bike> bikes = baseDAO.findByHQL("FROM Bike b WHERE b.bikeId = ? AND b.statues = 'lock'", new Object[]{bikeid});
		if(bikes.size()==0){
			return "0001";
		}
		else{
			baseDAO.executeSQL("INSERT INTO repair VALUES (NULL,?,?,NOW(),NULL,?,?);",new Object[]{bikeid,accusation_msg,userid,addinfo});
			baseDAO.executeSQL("UPDATE bike SET statues = 'repairing' WHERE bike_id = ?;",new Object[]{bikeid});
			return "0000";
		}
	}
	
	public List<RepairInfo> getRepairInfo(){
		List<RepairInfo> result = baseDAO.findBySQLForVO("SELECT repair_id AS repairId,B.bike_id AS bikeId,B.type AS type,repair_information AS repairInformation,"+
									" report_time AS reportTime,repair_time AS repairTime,report_user AS userId,name AS name,addinfo AS addinfo, "+
									" P.latitude AS latitude,P.longitude AS longitude "+ 
									"FROM `repair` AS R,bike AS B,`user`,position AS P "+
									"WHERE report_user = user_id AND R.bike_id = B.bike_id AND B.position_id = P.position_id AND R.repair_time is null;", RepairInfo.class, new Object[] {});
		return result;
	}
	
	public List<RepairInfo> getRepairInfoByPage(String PNO,String PSIZE){
		int page = Integer.parseInt(PNO);
		int size = Integer.parseInt(PSIZE);
		int start = (page-1)*size;
		List<RepairInfo> result = baseDAO.findBySQLForVO("SELECT repair_id AS repairId,B.bike_id AS bikeId,B.type AS type,repair_information AS repairInformation,"+
									" report_time AS reportTime,repair_time AS repairTime,report_user AS userId,name AS name,addinfo AS addinfo, "+
									" P.latitude AS latitude,P.longitude AS longitude "+ 
									"FROM `repair` AS R,bike AS B,`user`,position AS P "+
									"WHERE report_user = user_id AND R.bike_id = B.bike_id AND B.position_id = P.position_id AND R.repair_time is null limit ?,?;", RepairInfo.class, new Object[] {start, size});
		return result;
	}
	
	public String updateRepairInfo(int id,int bid){
		
		baseDAO.executeSQL("UPDATE repair SET repair_time = NOW() WHERE repair_id = ?;",new Object[]{id});
		baseDAO.executeSQL("UPDATE bike SET statues ='lock' WHERE bike_id = ?;",new Object[]{bid});
		return "0000";
	}
    public String updateRepairInfoBad(int id,int bid){
		
		baseDAO.executeSQL("UPDATE repair SET repair_time = NOW() WHERE repair_id = ?;",new Object[]{id});
		baseDAO.executeSQL("UPDATE bike SET statues ='broked' WHERE bike_id = ?;",new Object[]{bid});
		return "0000";
	}

}
