package shulictian.ssm.service;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.Topic;

public interface TopicService {

	public void saveDraft(Topic topic);

	String getTopic(int id);

	// String getUserTopic(int id);

	String getTopicByState(Map<String, Object> map);

	String getTops();

	String getType(int type);

	// List<Topic> getRems(Map<String, Object> map);
	// List<Topic> getUnPass(Map<String, Object> map);

	String soTop(String sele);

	void upTopStateByState(Map<String, Object> map);

//	void removeTop(Map<String, Object> map);
//
//	void recoverTop(Map<String, Object> map);

	List<Topic> getUserTopNew(int id);

	List<Topic> getUserTopHot(int id);

	List<Topic> getTypeNew(int type);

	List<Topic> getRelatedTop(int type);

	String getAllTopHot();

	String findAllTopNew();

	String findTopByGenre(Map<String, Object> map);

	/*
	 * List<TopicCustom> getUserTopicZero(int id);
	 * 
	 * List<TopicCustom> getUserTopicOne(int id);
	 * 
	 * List<TopicCustom> getUserTopicTow(int id);
	 */

	int saveTopic(Topic topic);

	// String judAtt(Map<String, Object> map);

	String homeFind(Map<String, Object> map);

//	int getAllCount();

}
