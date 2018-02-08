package sh.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;

import sh.dao.UserDao;
import sh.po.User;
import sh.util.H_Util;

public class UserDaoImpl implements UserDao {
	
	HibernateTemplate ht;
	
	
	public HibernateTemplate getHt() {
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	@Override
	public void add(User u) {
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		s.save(u);
		
		s.getTransaction().commit();
		
	}

	@Override
	public User getByNP(String uname, String pwd) {
		
		User u=null;
		/*Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		Query q = s.createQuery("from User u where u.uname=? and u.pwd=?");*/
		
//		q.setString(0, uname);
//		q.setString(1, pwd);
		String sql="from User u where u.uname=? and u.pwd=?";
		u=(User) ht.find(sql, uname,pwd);
		System.out.println(u);
		/*u = (User) q.uniqueResult();
		
		s.getTransaction().commit();*/
		
		return u;
	}
	
	@Override
	public List<User> getUsers(){
		
		List<User> l=null;
		
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		Query q = s.createQuery("from User");
		
		l = q.list();
		
		s.getTransaction().commit();
		
		return l;
		
	}

	@Override
	public User getUser(int id) {
	
		Session s = H_Util.getSession();
		
		s.beginTransaction();
		
		User u = s.get(User.class, id);
		
		s.getTransaction().commit();
		return u;
	}

}
