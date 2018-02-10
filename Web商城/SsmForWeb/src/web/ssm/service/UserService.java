package web.ssm.service;

import java.util.Date;
import java.util.List;

import web.ssm.po.user.User;
import web.ssm.po.user.Useraddress;
import web.ssm.po.user.Usermsg;

public interface UserService {
	
	public User getUser(String name,String password);
	
	public void insertUser(String name,String password,Date time);
	
	public Usermsg getMsg(User user);
	
	public void setMsg(User user,String name,int age,String day,String idCard,String yourslftext);

	List<Useraddress> getAdd(User user);

	void setAdd(User user, String name, String phonenumber, String ip);
	
	public void adduseradd(User user, String name, String phonenumber, String ip);

}
