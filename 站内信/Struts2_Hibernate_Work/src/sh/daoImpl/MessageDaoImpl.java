package sh.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sh.dao.MessageDao;
import sh.po.Message;
import sh.util.H_Util;

public class MessageDaoImpl implements MessageDao {


	@Override
	public void saveM(Message m) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		s.save(m);
		
		s.getTransaction().commit();

		
	}

	@Override
	public boolean del(int id) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		Message m = s.get(Message.class, id);
		m.setDel(1);
		s.getTransaction().commit();
		return true;
	}
	
	@Override
	public List<Message> getMsg(int userId) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		Query q = s.createQuery("from Message m where m.sender.id=? and m.del=0");
		q.setInteger(0, userId);
		
		List<Message> list=q.list();
		
		s.getTransaction().commit();
		return list;
	}

}
