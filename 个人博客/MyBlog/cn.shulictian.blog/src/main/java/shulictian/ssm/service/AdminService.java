package shulictian.ssm.service;

import java.util.Map;

import shulictian.ssm.po.User;

public interface AdminService {

	public String goGetAdmin(String code, User user);

	String getUserByType(int type);

//	String goSuperAdmin();

//	void revocation(int id);

//	void punish(int id);

	String getAuditAdmin();

	String getDeleteAdmin();

/*	void pass(Map<String, Object> map);

	void noPass(Map<String, Object> map);

	void recoverAdmin(Map<String, Object> map);

	void deleteAdmin(Map<String, Object> map);*/

//	void unPunish(int id);

	void upTopStateByState(Map<String, Object> map);

	void upUserTypeById(Map<String, Object> map);

//	String getPunish();

}
