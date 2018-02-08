package sh.service;

import java.util.Date;
import java.util.List;

import sh.po.Message;

public interface MessageService {
	
	public void addMS(String title,String content,int sendId,String userids,Date sendTime);

	public List<Message> getMsg(int userId);
	
}
