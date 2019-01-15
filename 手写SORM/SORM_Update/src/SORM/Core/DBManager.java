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
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @author TIan
 *
 */
public class DBManager {
	private static Configuration conf;  //配置信息
	private static DBConnPool pool;  //连接池对象
	
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
	 * 获得Connection对象
	 * @return
	 */
	public static Connection getConn(){
		if(pool==null){
			pool = new DBConnPool();
		}
		return pool.getConnection();
	}
	
	/**
	 * 创建新的Connection对象
	 * @return
	 */
	public static Connection createConn(){
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(
					conf.getUrl(),
					conf.getUser(),
					conf.getPwd() ); //直接连接，后期增加连接池处理，提高效率
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
	 * 返回Configuration对象
	 * @return
	 */
	public static Configuration getConf(){
		return conf;
	}
	
}
