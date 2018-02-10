package shulictian.ssm.service;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicCustom;

public interface TopicService{
	
	public void saveTopic(Topic topic,Map<String,Object> map);
	
	public void saveDraft(Topic topic,Map<String,Object> map);

	TopicCustom getTopic(int id);

	List<TopicCustom> getUserTopic(int id);

	List<Topic> getAudit(Map<String,Object> map);

	List<TopicCustom> getTops();

	List<TopicCustom> getType(int type);

	List<Topic> getRems(Map<String,Object> map);

	List<TopicCustom> soTop(String sele);

	List<Topic> getUnPass(Map<String,Object> map);

	void deleteTop(Map<String,Object> map);
	
	void removeTop(Map<String,Object> map);

	void recoverTop(Map<String,Object> map);

	void updateState(Map<String, Object> map);
	
	List<Topic> getUserTopNew(int id);
	
	List<Topic> getUserTopHot(int id);
	
	List<Topic> getTypeNew(int type);
	
	List<Topic> getRelatedTop(int type);
	
	List<Topic> getAllTopHot();

	List<Topic> findAllTopNew();

	List<TopicCustom> findTopByGenre(Map<String, Object> map);

}
