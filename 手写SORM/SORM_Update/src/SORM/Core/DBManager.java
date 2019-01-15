package SORM.Core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import SORM.Bean.Configuration;
import SORM.pool.DBConnPool;

/**
 * ����������Ϣ��ά�����Ӷ���Ĺ����������ӳع��ܣ�
 * @author TIan
 *
 */
public class DBManager {
	private static Configuration conf;  //������Ϣ
	private static DBConnPool pool;  //���ӳض���
	
	static{
		Properties pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		conf = new Configuration();
		conf.setDriver(pros.getProperty("Driver"));
		conf.setUrl(pros.getProperty("Url"));
		conf.setUser(pros.getProperty("User"));
		conf.setPwd(pros.getProperty("Pwd"));
		conf.setSrcPath(pros.getProperty("SrcPath"));
		conf.setUsingDB(pros.getProperty("UsingDB"));
		conf.setPersistoPackage(pros.getProperty("PersistoPackage"));
		conf.setQueryClass(pros.getProperty("queryClass"));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));

	}
	/**
	 * ���Connection����
	 * @return
	 */
	public static Connection getConn(){
		if(pool==null){
			pool = new DBConnPool();
		}
		return pool.getConnection();
	}
	
	/**
	 * �����µ�Connection����
	 * @return
	 */
	public static Connection createConn(){
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(
					conf.getUrl(),
					conf.getUser(),
					conf.getPwd() ); //ֱ�����ӣ������������ӳش������Ч��
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		} 
	}
	
	public static void close(ResultSet rs,Statement ps,Connection conn){
	
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			try {
//				if(conn!=null){
//				conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			pool.close(conn);
	}
	
	public static void close(Statement ps,Connection conn){
		try {
			if(ps!=null){
			ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try {
//			if(conn!=null){
//			conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		pool.close(conn);
	}
	
	public static void close(Connection conn){
		
//		try {
//			if(conn!=null){
//			conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		pool.close(conn);
	}
	/**
	 * ����Configuration����
	 * @return
	 */
	public static Configuration getConf(){
		return conf;
	}
	
}
