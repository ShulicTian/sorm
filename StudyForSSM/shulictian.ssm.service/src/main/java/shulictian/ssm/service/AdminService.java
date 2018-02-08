package shulictian.ssm.service;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.User;

public interface AdminService{
	
	public String goGetAdmin(String code, User user);

	List<User> getUserAdmin();

	List<User> goSuperAdmin();

	void revocation(int id);
	
	void punish(int id);

	List<Topic> getAuditAdmin();

	List<Topic> getDeleteAdmin();

	void pass(Map<String, Object> map);

	void noPass(Map<String, Object> map);

	void recoverAdmin(Map<String, Object> map);

	void deleteAdmin(Map<String, Object> map);

	void unPunish(int id);

	List<User> getPunish();
	
	
	
}
