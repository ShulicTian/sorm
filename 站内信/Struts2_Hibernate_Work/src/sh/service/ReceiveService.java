package sh.service;

import java.util.List;

import sh.po.Message;
import sh.po.ReceiveInfo;
import sh.po.User;

public interface ReceiveService {
	
	List<ReceiveInfo> getHaveMsg(User u);

	List<Message> getDelMsg(User u);
	
}
