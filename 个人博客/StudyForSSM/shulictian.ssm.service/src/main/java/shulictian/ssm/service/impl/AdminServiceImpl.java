package shulictian.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shulictian.ssm.mapper.AdminMapper;
import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.User;
import shulictian.ssm.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper am = null;

	@Override
	public String goGetAdmin(String code,User user) {
		String c = am.goGetAdmin(code);
		if(c!=null && c!="") {
			am.updateCodeStatus(c);
			am.updateUserType(user.getId());
			user.setType(1);
			return "yes";
		}
		return "no";
	}
	
	@Override
	public List<Topic> getAuditAdmin(){
		return am.getAdTopByState(TopicStateEnum.AUTHSTR.getCode());
	}
	
	@Override
	public List<Topic> getDeleteAdmin(){
		return am.getAdTopByState(TopicStateEnum.DELETE.getCode());
	}
	
	@Override
	public void pass(Map<String,Object> map){
		map.put("state", TopicStateEnum.PASS.getCode());
		am.upTopStateByState(map);
	}

	@Override
	public void noPass(Map<String,Object> map) {
		map.put("state", TopicStateEnum.NOTPASS.getCode());
		am.upTopStateByState(map);
	}
	
	@Override
	public void recoverAdmin(Map<String,Object> map) {
		map.put("state", TopicStateEnum.REMOVE.getCode());
		am.upTopStateByState(map);
	}
	
	@Override
	public void deleteAdmin(Map<String,Object> map) {
		map.put("state", TopicStateEnum.DELETE.getCode());
		am.upTopStateByState(map);
	}
	
	@Override
	public List<User> getUserAdmin(){
		return am.getUserAdmin();
	}
	
	@Override
	public List<User> goSuperAdmin(){
		return am.goSuperAdmin();
	}
	
	@Override
	public List<User> getPunish(){
		return am.punishUsers();
	}
	
	@Override
	public void revocation(int id) {
		am.revocation(id);
	}
	
	@Override
	public void punish(int id) {
		am.punish(id);
	}
	
	@Override
	public void unPunish(int id) {
		am.revocation(id);
	}

}
