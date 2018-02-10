package shulictian.ssm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shulictian.ssm.mapper.UserMapper;
import shulictian.ssm.po.ProMsg;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserCustom;
import shulictian.ssm.po.UserMessage;
import shulictian.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper um = null;

	/**
	 * 账号注册
	 */
	@Override
	public void saveUser(User user) {
		user.setRegTime(new Date());
		um.saveUser(user);
		um.createState(user.getId());
		um.createAdd(user.getId());
		um.createUsermsg(user.getId());
	}
	
	/**
	 * 登入
	 */
	@Override
	public User getUser(String userName, String password) {
		
		User user = new User();
		user.setPassWord(password);
		user.setUserName(userName);
		user=um.getUser(user);
		
		return user;
	}
	
	/**
	 * 更新User
	 */
	@Override
	public void upUser(User user) {
		um.upUser(user);
	}
	
	/**
	 * 更改密码
	 */
	@Override
	public void upPwd(Integer id,String newPwd) {
		Map<String,Object> map = new HashMap<>();
		um.upPwd(map);
	}
	
	/**
	 * 更新State
	 */
	@Override
	public void updateState(Map<String,Object> map) {
		um.updateState(map);
	}
	
	/**
	 * 根据id查找UserCustom
	 */
	@Override
	public UserCustom findUser(int id) {
		
		return um.findUser(id);
	}

	/**
	 * 查找所有用户名
	 */
	@Override
	public List<String> getAllUserName() {
		
		return um.getAllUserName();
	}
	
	/**
	 * 查找粉丝
	 */
	@Override
	public List<User> findFans(int id) {
		
		return um.findFans(id);
	}
	
	/**
	 * 查找已关注
	 */
	@Override
	public List<User> findAtt(int id) {
		
		return um.findAtt(id);
	}
	
	/**
	 * 更新用户Msg
	 */
	@Override
	public void upMsg(UserMessage userMsg) {
		
		um.updateUsermsg(userMsg);
	}
	
	/**
	 * 获取用户Msg
	 */
	@Override
	public UserMessage getUsermsg(int id) {
		
		return um.findUsermsg(id);
	}
	
	/**
	 * 获取用户Pro
	 */
	@Override
	public List<ProMsg> getPro(int id) {
		return um.findPro(id);
	}
	
	/**
	 * 添加用户Pro
	 */
	@Override
	public void addPro(ProMsg pm) {
		um.addPro(pm);
	}
	
	/**
	 * 更新用户Pro
	 */
	@Override
	public void upPro(ProMsg pm) {
		um.upPro(pm);
	}
	
	/**
	 * 获取用户Git主页地址
	 */
	@Override
	public String getGit(int id) {
		return um.findGit(id);
	}
	
	/**
	 * 更新用户Git主页地址
	 */
	@Override
	public void upGit(Map<String,Object> map) {
		um.upGit(map);
	}
	
}
