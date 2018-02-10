package shulictian.ssm.mapper;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.ProMsg;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserCustom;
import shulictian.ssm.po.UserMessage;

public interface UserMapper {
	
	public void saveUser(User user);

	public void createState(int id);
	
	public User getUser(User user);
	
	public UserCustom findUser(int id);
	
	public List<String> getAllUserName();
	
	public List<User> findFans(int id);

	public List<User> findAtt(int id);
	
	public void updateState(Map<String,Object> map);
	
	public void updateUsermsg(UserMessage userMsg);
	
	public UserMessage findUsermsg(int userId);
	
	public void createAdd(int userId);
	
	public void createUsermsg(int userId);
	
	public void upUser(User user);
	
	public void upPwd(Map<String,Object> map);
	
	public List<ProMsg> findPro(int userId);
	
	public void addPro(ProMsg pm);
	
	public void upPro(ProMsg pm);
	
	public String findGit(int userId);
	
	public void upGit(Map<String,Object> map);
	

}
