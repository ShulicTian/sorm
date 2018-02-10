package web.ssm.mapper;

import web.ssm.po.user.Usermsg;

public interface UsermsgMapper {
	
	public Usermsg getMsg(int id);

	public void setMsg(Usermsg msg);
	
	public void createMsg(Usermsg msg);
}