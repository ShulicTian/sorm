package sh.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sh.dao.ReceiveDao;
import sh.po.Message;
import sh.po.ReceiveInfo;
import sh.po.User;
import sh.util.H_Util;

public class ReceiveDaoImpl implements ReceiveDao {

	@Override
	public List<ReceiveInfo> getHaveMsg(User u) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		Query q = s.createQuery("from ReceiveInfo r where r.receiver.id=? and r.message.del=0");
		q.setInteger(0,u.getId());
		List<ReceiveInfo> list= q.list();
		
		s.getTransaction().commit();
		
		return list;
	}
	
	@Override
	public List<Message> getDelMsg(User u) {
		
		Session s = H_Util.getSession();
		List<Message> list= new ArrayList<Message>();
		
		s.beginTransaction();
		
		Query q = s.createQuery("from Message m where m.sender.id=? and m.del=1");
		q.setInteger(0,u.getId());
		list.addAll(q.list());
		
		Query q2 = s.createQuery("from ReceiveInfo r where r.receiver.id=? and r.delStata=1");
		q2.setInteger(0, u.getId());
		List<ReceiveInfo> l= q2.list();
		
		for(ReceiveInfo r : l){
			
			list.add(r.getMessage());
		}
		
		
		s.getTransaction().commit();
		
		return list;
	}
	
	
	
	@Override
	public boolean del(int id) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		ReceiveInfo m = s.get(ReceiveInfo.class, id);
		m.setDelStata(1);
		s.getTransaction().commit();
		return true;
	}
	
	@Override
	public boolean read(int id) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		ReceiveInfo m = s.get(ReceiveInfo.class, id);
		m.setReadStata(1);
		s.getTransaction().commit();
		return true;
	}

	@Override
	public boolean regain(int id) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
			
			Message m = s.get(Message.class, id);

			m.setDel(0);
			
		s.getTransaction().commit();
			
		return true;
	}

	@Override
	public boolean remove(int id) {
		
	Session s = H_Util.getSession();
		
		s.beginTransaction();

		Message m = s.get(Message.class, id);
		
		m.setDel(2);
		
		s.getTransaction().commit();
		
		return true;
	}
	
}
