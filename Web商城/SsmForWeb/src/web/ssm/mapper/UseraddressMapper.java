package web.ssm.mapper;

import java.util.List;

import web.ssm.po.user.Useraddress;

public interface UseraddressMapper {
	
	public List<Useraddress> getAdd(int id);
	
	public void Add(Useraddress useradd);
	
	public void setAdd(Useraddress add);
}