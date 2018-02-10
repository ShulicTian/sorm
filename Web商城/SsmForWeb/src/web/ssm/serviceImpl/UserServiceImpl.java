package web.ssm.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.ssm.mapper.UserMapper;
import web.ssm.mapper.UseraddressMapper;
import web.ssm.mapper.UsermsgMapper;
import web.ssm.po.user.User;
import web.ssm.po.user.UserQueryVO;
import web.ssm.po.user.Useraddress;
import web.ssm.po.user.Usermsg;
import web.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper um;
	@Autowired
	private UsermsgMapper mm;
	@Autowired
	private UseraddressMapper am;

	/**
	 * 登入
	 */
	@Override
	public User getUser(String name, String password) {
		
		UserQueryVO uqv = new UserQueryVO();
		uqv.setName(name);
		uqv.setPassword(password);
		return um.getUser(uqv);
	}
	
	/**
	 * 注册
	 */

	@Override
	public void insertUser(String name,String password,Date time) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setRegtime(time);
		um.saveUser(user);
		
	}

	/**
	 * 获取个人资料
	 */
	
	@Override
	public Usermsg getMsg(User user) {
		if(user.getUsermsg().getId()==null) {
			Usermsg msg = new Usermsg();
			msg.setAge(null);
			msg.setName("");
			msg.setIdcard("");
			msg.setDay(null);
			msg.setYouslftext("");
			msg.setUser(user);
			mm.createMsg(msg);
			user.setUsermsg(msg);
			um.updateUser(user);
			return msg;
		}
		return mm.getMsg(user.getUsermsg().getId());
	}
	
	/**
	 * 修改个人资料
	 */

	@Override
	public void setMsg(User user,String name,int age,String day,String idCard,String yourslftext) {
		Usermsg msg = new Usermsg();
		msg.setAge(age);
		msg.setName(name);
		msg.setIdcard(idCard);
		msg.setDay(day);
		msg.setYouslftext(yourslftext);
		msg.setUser(user);
		mm.setMsg(msg);
	}
	
	/**
	 * 获取收货地址
	 */
	
	@Override
	public List<Useraddress> getAdd(User user) {
		return am.getAdd(user.getId());
	}

	/**
	 * 添加收货地址
	 */
	
	@Override
	public void setAdd(User user, String name, String phonenumber, String ip) {
		Useraddress add = new Useraddress();
		add.setName(name);
		add.setPhonenumber(phonenumber);
		add.setIp(ip);
		add.setUser(user);
		am.setAdd(add);
	}

	@Override
	public void adduseradd(User user, String name, String phonenumber, String ip) {
		Useraddress add = new Useraddress();
		add.setName(name);
		add.setPhonenumber(phonenumber);
		add.setIp(ip);
		add.setUser(user);
		am.Add(add);
		
	}

}
