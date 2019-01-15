package SORM.Core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import SORM.Bean.ColumnInfo;
import SORM.Bean.TableInfo;
import SORM.Utils.JDBCUtils;
import SORM.Utils.ReflectUtils;

@SuppressWarnings("rawtypes")
public abstract class Query implements Cloneable {
	
	/**
	 * ��ģ�巽��ģʽ��JDBC������װ��ģ��
	 * @param sql
	 * @param params
	 * @param clazz
	 * @param back
	 * @return
	 */
	public Object dmlTemplate(String sql,Object[] params,Class clazz,CallBack back){
		Connection conn = DBManager.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			//��sql����
			JDBCUtils.handleParams(ps, params);
			System.out.println(ps);
			rs = ps.executeQuery();
			
			return (List) back.doExecute(conn, ps, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			DBManager.close(ps, conn);
		}
		
	}
	/**
	 * ֱ��ִ��һ��DML���
	 * @return ������Ӱ�������
	 */
	public int executeDML(String sql,Object[] params){
		Connection conn = DBManager.getConn();
		int count = 0;
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			//��sql����
			JDBCUtils.handleParams(ps, params);

			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(ps, conn);
		}
		
		return count;
	}
	/**
	 * ��һ������洢�����ݿ���
	 * @param obj
	 */
	public void insert(Object obj){
		//obj-->����
		Class c = obj.getClass();
		List<Object> params = new ArrayList<Object>();  //�洢sql�Ĳ�������
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);
		StringBuilder sql = new StringBuilder("insert into "+tableinfo.getTname()+" (");
		int num = 0; //���㲻Ϊ�յ�����
		Field[] fs = c.getDeclaredFields();
		for(Field f:fs){
			String fieldName = f.getName();
			Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);
			
			if(fieldValue!=null){
				num++;
				sql.append(fieldName+",");
				params.add(fieldValue);
			}
		}
		sql.setCharAt(sql.length()-1, ')');
		sql.append(" values(");
		for(int i=0;i<num;i++){
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		
		executeDML(sql.toString(), params.toArray());
	}
	/**
	 * ɾ��clazz��ʾ���Ӧ�ı��м�¼��ָ��ID��
	 * @param clazz
	 * @param id
	 */
	public void delete(Class clazz,Object id){
		//Emp.class,id-->delete from emp where id=2
		//ͨ��class������TableInfo
		TableInfo tableinfo = TableContext.poClassTableMap.get(clazz);
		//�������
		ColumnInfo column = tableinfo.getOnlyPriKey();
		String sql = "delete from "+tableinfo.getTname()+" where "+column.getName()+"=? ";
		executeDML(sql, new Object[]{id});
		
	}
	/**
	 * ɾ�����������ݿ��ж�Ӧ�ļ�¼
	 * @param obj
	 */
	public void delete(Object obj){
		Class c = obj.getClass();
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);
		ColumnInfo column = tableinfo.getOnlyPriKey();
		
		//ͨ��������ƣ��������Զ�Ӧ��get������set����
		Object priKeyValue = ReflectUtils.invokeGet(column.getName(), obj);
		delete(c,priKeyValue);
	}
	/**
	 * ���¶����Ӧ�ļ�¼������ֻ����ָ�����ֶε�ֵ
	 * @param obj
	 * @param fieldNames
	 * @return ������Ӱ�������
	 */
	public int update(Object obj,String[] fieldNames){
		//obj{"name","pwd"}-->update ���� set name=?,pwd=? where id=?
		Class c = obj.getClass();
		List<Object> params = new ArrayList<Object>();  //�洢sql�Ĳ�������
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);  //��ȡ����Ϣ
		ColumnInfo priKey = tableinfo.getOnlyPriKey();  //���Ψһ������
		StringBuilder sql = new StringBuilder("update "+tableinfo.getTname()+" set ");
		
		for(String fname:fieldNames){
			Object fvalue = ReflectUtils.invokeGet(fname, obj);
			params.add(fvalue);
			sql.append(fname+"=?,");
		}
		sql.setCharAt(sql.length()-1,' ');
		sql.append(" where ");
		sql.append(priKey.getName()+"=? ");
		params.add(ReflectUtils.invokeGet(priKey.getName(), obj));
		return executeDML(sql.toString(), params.toArray());
	}
	/**
	 * ��ѯ���ض��м�¼������ÿ�м�¼��װ��clazzָ������Ķ�����
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return ���ز�ѯ���Ľ��
	 */
	public List queryRows(final String sql,final Class clazz,final Object[] params){
		
		return (List) dmlTemplate(sql, params, clazz, new CallBack(){

			@Override
			public Object doExecute(Connection conn, java.sql.PreparedStatement ps, ResultSet rs) {
				List list = null;  //��Ų�ѯ���������
				try {
					ResultSetMetaData meta = rs.getMetaData();  //��������Ϣ
					//����
					while(rs.next()){
						if(list==null){
							list = new ArrayList();
						}
						Object rowObj = clazz.newInstance();   //����javabean���޲ι�����
						//����
						for(int i=0;i<meta.getColumnCount();i++){
							String columnName = meta.getColumnLabel(i+1);
							Object columnValue = rs.getObject(i+1);
							
							//����rowObj�����setName��������columnValue��ֵ���ý�ȥ
							ReflectUtils.invokeSet(rowObj, columnName, columnValue);
						}
						list.add(rowObj);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}});
		
	}
	/**
	 * ��ѯ����һ�м�¼������ÿ�м�¼��װ��clazzָ������Ķ�����
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return ���ز�ѯ���Ľ��
	 */
	public Object queryOneRow(String sql,Class clazz,Object[] params){
		List list = queryRows(sql, clazz, params);
		
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
	/**
	 * ����������ֱֵ�Ӳ��Ҷ�Ӧ�Ķ���
	 * @return
	 */
	public Object queryById(Class clazz,Object id){
		TableInfo tableinfo = TableContext.poClassTableMap.get(clazz);
		ColumnInfo onlyPriKey = tableinfo.getOnlyPriKey();
		String sql = "select * from "+tableinfo.getTname()+" where "+onlyPriKey.getName()+"=?";
		return queryOneRow(sql, clazz, new Object[]{id});
	}
	/**
	 * ��ѯ����һ��ֵ��һ��һ�У���������ֵ����
	 * @param sql
	 * @param params
	 * @return ���ز�ѯ���Ľ��
	 */
	public Object queryValue(String sql,Object[] params){
		
		return dmlTemplate(sql, params, null, new CallBack(){

			@Override
			public Object doExecute(Connection conn, java.sql.PreparedStatement ps, ResultSet rs) {
				Object value = null;
				//����
				try {
					while(rs.next()){
						value = rs.getObject(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return value;
			}});
		
	}
	/**
	 * ��ѯ����һ�����֣�һ��һ�У���������ֵ����
	 * @param sql
	 * @param params
	 * @return ���ز�ѯ��������
	 */
	public Number queryNumber(String sql,Object[] params){
		return (Number) queryValue(sql, params);
	}
	/**
	 * ��ҳ��ѯ
	 * @param pageNum
	 * @param size
	 * @return
	 */
	public abstract Object queryPagenate(int pageNum,int size);
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
