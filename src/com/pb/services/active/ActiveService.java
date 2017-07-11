package com.pb.services.active;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Active;
import com.pb.entity.Collection;
import com.pb.json.ActiveJson;
import com.pb.json.CollectingInfo;
import com.pb.services.common.CommService;

@Service(value = "activeService")
public class ActiveService  extends CommService {

	public Active getActiveById(int active_id) {
		// TODO Auto-generated method stub
		List<Active> list = baseDAO.findByHQL("from Active a where a.id=?", new Object[]{active_id});
		if(list.isEmpty()){
			System.out.println("In method getActiveById, id does not exist.");
			return null;
		}
		return list.get(0);
	}

	public List<Active> getAllActive(int pno, int PAGE_SIZE) {
		// TODO Auto-generated method stub
		List<Active> list = baseDAO.findByPage("from Active a",(pno-1)*PAGE_SIZE,PAGE_SIZE);
		return list;
	}
	
	public ActiveJson A2AJ(Active active){
		ActiveJson aj = new ActiveJson();
		aj.setAddress(active.getAddress());
		aj.setContent(active.getContent());
		aj.setHostname(active.getHost().getHostName());
		aj.setId(active.getId());
		aj.setLike(active.getLike());
		aj.setLogo(active.getLogo());
		aj.setTag(active.getTag());
		aj.setTimeBegin(active.getTimeBegin());
		aj.setTimeEnd(active.getTimeEnd());
		aj.setTitle(active.getTitle());
		aj.setViewed(active.getViewed());
		aj.setHostContact(active.getHost().getContact());
		aj.setHostInfo(active.getHost().getIntro());
		return aj;
	}

	public List<CollectingInfo> collecting(String TCID) {
		// TODO Auto-generated method stub
		List<Collection> clist = baseDAO.findByHQL("from Collection c where c.tcid = ?", new Object[]{TCID});
		List<CollectingInfo> list = new ArrayList<CollectingInfo>();
		for(Collection c : clist){
			list.add(C2CI(c));
		}
		return list;
	}
	
	public CollectingInfo C2CI(Collection c){
		CollectingInfo Info = new CollectingInfo();
		Info.setActiveId(c.getActive().getId()+"");
		Info.setPoster(c.getActive().getLogo());
		Info.setActiveName(c.getActive().getTitle());
		Info.setHeader(c.getActive().getHost().getHeader());
		Info.setHostName(c.getActive().getHost().getHostName());
		Info.setLocation(c.getActive().getAddress());
		Info.setTimeEnd(c.getActive().getTimeEnd());
		Info.setTimeStart(c.getActive().getTimeBegin());
		return Info;
	}
}
