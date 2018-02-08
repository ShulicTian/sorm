package sh.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class H_Util {
	
	public static Session getSession(){

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.getCurrentSession();
		
		return s;
		
	}

}
