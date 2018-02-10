package shulictian.ssm.service;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.ProMsg;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserCustom;
import shulictian.ssm.po.UserMessage;

public interface UserService{
	
	public void saveUser(User user);
	
	public User getUser(String userName,String password);
	
	public List<String> getAllUserName();

	UserCustom findUser(int id);

	List<User> findFans(int id);

	List<User> findAtt(int id);

	void updateState(Map<String,Object> map);

	void upMsg(UserMessage userMsg);

	UserMessage getUsermsg(int id);

	void upUser(User user);

	void upPwd(Integer id, String newPwd);

	List<ProMsg> getPro(int id);

	String getGit(int id);

	void upPro(ProMsg pm);

	void upGit(Map<String, Object> map);

	void addPro(ProMsg pm);



}
