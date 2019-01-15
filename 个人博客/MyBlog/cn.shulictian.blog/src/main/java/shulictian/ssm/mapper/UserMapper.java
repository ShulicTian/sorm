package shulictian.ssm.mapper;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.UserProMsg;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserCustom;
import shulictian.ssm.po.UserMessage;

public interface UserMapper {

	public void saveUser(User user);

	public void createState(int id);

	public User getUser(User user);

	public UserCustom findUser(int id);

	public List<String> getAllUserName();

	public List<Integer> findTopicIds(int id);

	public List<User> findFans(int id);

	public List<User> findAtt(int id);

	public void updateState(Map<String, Object> map);

	public void updateUsermsg(UserMessage userMsg);

	public UserMessage findUsermsg(int userId);

	public void createAdd(int userId);

	public void createUsermsg(int userId);

	public void upUser(Map<String, Object> map);

	// public void upPwd();

	public List<UserProMsg> findPro(int userId);

	public void addPro(UserProMsg pm);

	public void upPro(UserProMsg pm);

	public String findGit(int userId);

	public void upGit(Map<String, Object> map);

	public void attention(Map<String, Object> map);

	public void cancelAtt(Map<String, Object> map);

	public String getSdf(int userId);

	public void upSdf(Map<String, Object> map);

	public int judAtt(Map<String, Object> map);

}
