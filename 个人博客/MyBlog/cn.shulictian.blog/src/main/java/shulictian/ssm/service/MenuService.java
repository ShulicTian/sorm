package shulictian.ssm.service;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.UserMessage;
import shulictian.ssm.po.UserProMsg;

public interface MenuService {

	void attention(Map<String, Object> map);

	void cancelAtt(Map<String, Object> map);

	String findFans(int id);

	String findAtt(int id);

	void upMsg(UserMessage userMsg);

	UserMessage getUsermsg(int id);

	List<UserProMsg> getPro(int id);

	void addPro(UserProMsg pm);

	void upPro(UserProMsg pm);

	String getGit(int id);

	void upGit(Map<String, Object> map);

	String getSdf(int userId);

	void upSdf(Map<String, Object> map);

	String judAtt(Map<String, Object> map);

}
