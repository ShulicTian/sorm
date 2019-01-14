package testssm;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import web.ssm.po.user.User;

public class test {
	
	private static ApplicationContext ac = null;
	
	@BeforeClass
	public static void beforeclass() {
		
		ac = new  ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
	}
	

	@Test
	public void test() {
		
		
	}
	
	@AfterClass
	public static void afterclass() {
		
		
	}
	

}
