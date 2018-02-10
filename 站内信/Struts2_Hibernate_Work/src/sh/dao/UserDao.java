package sh.dao;

import java.util.List;

import sh.po.User;

public interface UserDao {

	public void add(User u);
	
	public User getByNP(String uname,String pwd);

	public List<User> getUsers();
	
	public User getUser(int id);

}
