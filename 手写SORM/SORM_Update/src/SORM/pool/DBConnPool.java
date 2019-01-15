package SORM.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SORM.Core.DBManager;
/**
 * 连接池的类
 * @author TIan
 *
 */
public class DBConnPool {
	private List<Connection> pool;//池
	
	private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();  //最大连接数
	private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();   //最小连接数
	
	/**
	 * 初始化连接池，使池中的链接数达到最小值
	 */
	public void initPool(){
		if(pool==null){
			pool = new ArrayList<Connection>();
		}
		while(pool.size()<DBConnPool.POOL_MIN_SIZE){
			pool.add(DBManager.createConn());
			System.out.println("初始化池，池中连接数："+pool.size());
		}
	}
	/**
	 * 从连接池中取出一个连接
	 * @return
	 */
	public synchronized Connection getConnection(){
		int last_index = pool.size()-1;
		Connection conn = pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}
	/**
	 * 将连接放回池中
	 * @param conn
	 */
	public synchronized void close(Connection conn){
		if(pool.size()>=POOL_MAX_SIZE){
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			pool.add(conn);
		}
	}
	
	public DBConnPool() {
		initPool();
	}
	
	
}
