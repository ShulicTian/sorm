package sh.dao;

import java.util.List;

import sh.po.Message;
import sh.po.ReceiveInfo;
import sh.po.User;

public interface ReceiveDao {
	
	List<ReceiveInfo> getHaveMsg(User u);

	boolean del(int id);

	boolean read(int id);

	List<Message> getDelMsg(User u);

	public boolean regain(int id);

	public boolean remove(int id);
}
