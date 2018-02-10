package shulictian.ssm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shulictian.ssm.mapper.TopicMapper;
import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicCustom;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicMapper tm = null;

	@Override
	public void saveTopic(Topic topic,Map<String,Object> map) {
		topic.setSendTime(new Date());
		tm.saveTopic(topic);
		map.put("id", topic.getId());
		map.put("state", TopicStateEnum.AUTHSTR.getCode());
		tm.saveState(map);
	}
	
	@Override
	public void saveDraft(Topic topic,Map<String,Object> map) {
		tm.saveDraft(topic);
		map.put("id", topic.getId());
		map.put("state", TopicStateEnum.DRAFT.getCode());
		tm.saveState(map);
	}
	
	@Override
	public void updateState(Map<String,Object> map) {
		
//		tm.updateState(map);
	}
	
	@Override
	public List<TopicCustom> getUserTopic(int id) {
		return tm.getUserTopic(id);
	}
	
	@Override
	public TopicCustom getTopic(int id) {
		return tm.getUserTopicById(id);
	}
	
	@Override
	public List<Topic> getAudit(Map<String,Object> map) {
		return tm.getTopByState(map);
	}
	
	@Override
	public List<Topic> getRems(Map<String,Object> map) {
		return tm.getTopByState(map);
	}
	
	@Override
	public List<Topic> getUnPass(Map<String,Object> map) {
		return tm.getTopByState(map);
	}
	
	@Override
	public List<TopicCustom> getTops() {
		return tm.getTopsicByAudit();
	}
	
	@Override
	public void deleteTop(Map<String,Object> map) {
		map.put("state", TopicStateEnum.DELETE.getCode());
		tm.upTopStateByState(map);
	}
	
	@Override
	public void removeTop(Map<String,Object> map) {
		map.put("state", TopicStateEnum.REMOVE.getCode());
		tm.upTopStateByState(map);
	}
	
	@Override
	public void recoverTop(Map<String,Object> map) {
		map.put("state", TopicStateEnum.PASS.getCode());
		tm.upTopStateByState(map);
	}
	
	
	@Override
	public List<TopicCustom> soTop(String sele) {
		sele="%"+sele+"%";
		return tm.soTop(sele);
	} 
	
	@Override
	public List<TopicCustom> getType(int type) {
		
		return tm.getType(type);
	}

	@Override
	public List<Topic> getUserTopNew(int id) {
		return tm.findNewTop(id);
	}

	@Override
	public List<Topic> getUserTopHot(int id) {
		return tm.findHotTop(id);
	}

	@Override
	public List<Topic> getTypeNew(int type) {
		return tm.findNewTypeTop(type);
	}

	@Override
	public List<Topic> getRelatedTop(int type) {
		return tm.findRelatedTop(type);
	}

	@Override
	public List<Topic> getAllTopHot() {
		return tm.findAllTopHot();
	}
	
	@Override
	public List<Topic> findAllTopNew() {
		return tm.findAllTopNew();
	}
	
	/**
	 * 根据genre查询自己的帖子
	 */
	@Override
	public List<TopicCustom> findTopByGenre(Map<String,Object> map) {
		return tm.findTopByGenre(map);
	}

}
