package sh.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import sh.dao.UserDao;
import sh.daoImpl.UserDaoImpl;
import sh.po.User;
import sh.service.UserService;
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao ud=new UserDaoImpl();
	
	@Override
	public void register(String uname, String pwd, String ip, Date joinTime) {

		User u = new User();
		u.setUname(uname);
		u.setPwd(pwd);
		u.setIp(ip);
		u.setJoinTime(joinTime);
		
		ud.add(u);
	}

	@Override
	public User login(String uname, String pwd) {

		return ud.getByNP(uname, pwd);
	}

	@Override
	public List<User> getAll(){
		
		return ud.getUsers();
	}

}
