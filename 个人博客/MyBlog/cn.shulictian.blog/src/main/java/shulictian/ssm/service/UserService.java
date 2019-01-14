package shulictian.ssm.service;

import java.util.Map;

import shulictian.ssm.po.User;
import shulictian.ssm.po.UserCustom;

public interface UserService {

	void saveUser(User user);

	String getUser(User user);

	void upUser(Map<String, Object> map);

	void updateState(Map<String, Object> map);

	UserCustom findUser(int id);

//	List<String> getAllUserName();

}
