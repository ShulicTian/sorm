package sh.serviceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import sh.dao.MessageDao;
import sh.dao.UserDao;
import sh.daoImpl.MessageDaoImpl;
import sh.daoImpl.UserDaoImpl;
import sh.po.Message;
import sh.po.ReceiveInfo;
import sh.service.MessageService;

@Transactional
public class MessageServiceImpl implements MessageService {

	private UserDao ud=new UserDaoImpl();
	private MessageDao md=new MessageDaoImpl();
	
	@Override
	public void addMS(String title,String content,int sendId,String userids,Date sendTime) {
		
		Message m = new Message();
		m.setTitle(title);
		m.setContent(content);
		m.setSendTime(sendTime);
		m.setSender(ud.getUser(sendId));

		Set<ReceiveInfo> receiveInfos = new HashSet<ReceiveInfo>();
		for(String str:userids.split(";")){
			ReceiveInfo ri = new ReceiveInfo();
			
			ri.setReceiver(ud.getUser(Integer.parseInt(str)));
			ri.setMessage(m);
			receiveInfos.add(ri);
			
		}
		m.setReceiveInfos(receiveInfos);
		md.saveM(m);
	}

	@Override
	public List<Message> getMsg(int userId) {
		return md.getMsg(userId);
	}

}
