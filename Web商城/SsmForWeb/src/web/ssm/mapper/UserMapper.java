package web.ssm.mapper;

import web.ssm.po.user.User;
import web.ssm.po.user.UserQueryVO;

public interface UserMapper {
	
	public User getUser(UserQueryVO uqv);
	
	public void saveUser(User user);
	
	public void updateUser(User user);
}