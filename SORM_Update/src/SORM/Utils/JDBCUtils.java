package SORM.Utils;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

/**
 * 封装了JDBC查询常用的操作
 * @author TIan
 *
 */
public class JDBCUtils {

	public static void handleParams(PreparedStatement ps,Object[] params){
		//给sql传参
		if(params!=null){
			for(int i=0;i<params.length;i++){
				
				try {
					ps.setObject(1+i, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
}
