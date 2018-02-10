package sh.service;

import java.util.Date;
import java.util.List;

import sh.po.User;

public interface UserService {
	
	public void register(String uname,String pwd,String ip,Date joinTime);
	
	public User login(String uname,String pwd);

	public List<User> getAll();

}
