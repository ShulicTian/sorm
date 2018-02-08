package sh.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import sh.dao.ReceiveDao;
import sh.daoImpl.ReceiveDaoImpl;
import sh.po.Message;
import sh.po.ReceiveInfo;
import sh.po.User;
import sh.service.ReceiveService;
@Transactional
public class ReceiveServiceImpl implements ReceiveService {
	
	private ReceiveDao rd=new ReceiveDaoImpl();

	@Override
	public List<ReceiveInfo> getHaveMsg(User u) {
			
		return rd.getHaveMsg(u);
	}
	
	@Override
	public List<Message> getDelMsg(User u){
		
		
		return rd.getDelMsg(u);
	}

}
