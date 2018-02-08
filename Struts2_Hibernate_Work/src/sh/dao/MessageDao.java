package sh.dao;

import java.util.List;

import sh.po.Message;


public interface MessageDao {
	
	public void saveM(Message m);

	boolean del(int id);
	
	public List<Message> getMsg(int userId);

}
