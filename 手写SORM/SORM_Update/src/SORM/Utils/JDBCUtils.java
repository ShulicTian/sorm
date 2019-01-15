package SORM.Utils;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

/**
 * ��װ��JDBC��ѯ���õĲ���
 * @author TIan
 *
 */
public class JDBCUtils {

	public static void handleParams(PreparedStatement ps,Object[] params){
		//��sql����
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
