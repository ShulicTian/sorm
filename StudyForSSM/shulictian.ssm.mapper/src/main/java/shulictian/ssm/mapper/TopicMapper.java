package shulictian.ssm.mapper;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicCustom;

public interface TopicMapper {
	
	public void saveTopic(Topic topic);
	
	public void saveDraft(Topic topic);
	
	public List<TopicCustom> getUserTopic(int id);
	
	public TopicCustom getUserTopicById(int id);
	
	public List<TopicCustom> getTopsicByAudit();
	
	public List<TopicCustom> soTop(String sele);
	
	public void upTopStateByState(Map<String,Object> map);
	
	public List<TopicCustom> getType(int type);
	
	public void saveState(Map<String,Object> map);
	
	public void updateState(Map<String,Object> map);
	
	public List<Topic> findNewTop(int id);
	
	public List<Topic> findHotTop(int id);
	
	public List<Topic> findNewTypeTop(int type);
	
	public List<Topic> findRelatedTop(int type);
	
	public List<Topic> findAllTopHot();

	public List<Topic> findAllTopNew();
	
	public List<TopicCustom> findTopByGenre(Map<String,Object> map);
	
	public List<Topic> getTopByState(Map<String,Object> map);
}
