package shulictian.ssm.mapper;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.User;

public interface AdminMapper {
	
	public String goGetAdmin(String code);
	
	public void updateCodeStatus(String code);
	
	public void updateUserType(int id);
	
	public List<Topic> getAdTopByState(int state);
	
	public void upTopStateByState(Map<String,Object> map);
	
	public List<User> getUserAdmin();
	
	public List<User> goSuperAdmin();
	
	public List<User> punishUsers();
	
	public void revocation(int id);
	
	public void punish(int id);
}
