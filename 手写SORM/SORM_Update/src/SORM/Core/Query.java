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
	 * 用模板方法模式将JDBC操作封装成模板
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
			//给sql传参
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
	 * 直接执行一个DML语句
	 * @return 返回受影响的行数
	 */
	public int executeDML(String sql,Object[] params){
		Connection conn = DBManager.getConn();
		int count = 0;
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			//给sql传参
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
	 * 将一个对象存储到数据库中
	 * @param obj
	 */
	public void insert(Object obj){
		//obj-->表中
		Class c = obj.getClass();
		List<Object> params = new ArrayList<Object>();  //存储sql的参数对象
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);
		StringBuilder sql = new StringBuilder("insert into "+tableinfo.getTname()+" (");
		int num = 0; //计算不为空的属性
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
	 * 删除clazz表示类对应的表中记录（指定ID）
	 * @param clazz
	 * @param id
	 */
	public void delete(Class clazz,Object id){
		//Emp.class,id-->delete from emp where id=2
		//通过class对象找TableInfo
		TableInfo tableinfo = TableContext.poClassTableMap.get(clazz);
		//获得主键
		ColumnInfo column = tableinfo.getOnlyPriKey();
		String sql = "delete from "+tableinfo.getTname()+" where "+column.getName()+"=? ";
		executeDML(sql, new Object[]{id});
		
	}
	/**
	 * 删除对象在数据库中对应的记录
	 * @param obj
	 */
	public void delete(Object obj){
		Class c = obj.getClass();
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);
		ColumnInfo column = tableinfo.getOnlyPriKey();
		
		//通过反射机制，调用属性对应的get方法或set方法
		Object priKeyValue = ReflectUtils.invokeGet(column.getName(), obj);
		delete(c,priKeyValue);
	}
	/**
	 * 更新对象对应的记录，并且只更新指定的字段的值
	 * @param obj
	 * @param fieldNames
	 * @return 返回受影响的行数
	 */
	public int update(Object obj,String[] fieldNames){
		//obj{"name","pwd"}-->update 表明 set name=?,pwd=? where id=?
		Class c = obj.getClass();
		List<Object> params = new ArrayList<Object>();  //存储sql的参数对象
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);  //获取表信息
		ColumnInfo priKey = tableinfo.getOnlyPriKey();  //获得唯一的主键
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
	 * 查询返回多行记录，并将每行记录封装带clazz指定的类的对象中
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return 返回查询到的结果
	 */
	public List queryRows(final String sql,final Class clazz,final Object[] params){
		
		return (List) dmlTemplate(sql, params, clazz, new CallBack(){

			@Override
			public Object doExecute(Connection conn, java.sql.PreparedStatement ps, ResultSet rs) {
				List list = null;  //存放查询结果的容器
				try {
					ResultSetMetaData meta = rs.getMetaData();  //返回列信息
					//多行
					while(rs.next()){
						if(list==null){
							list = new ArrayList();
						}
						Object rowObj = clazz.newInstance();   //调用javabean的无参构造器
						//多列
						for(int i=0;i<meta.getColumnCount();i++){
							String columnName = meta.getColumnLabel(i+1);
							Object columnValue = rs.getObject(i+1);
							
							//调用rowObj对象的setName方法，将columnValue的值设置进去
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
	 * 查询返回一行记录，并将每行记录封装带clazz指定的类的对象中
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return 返回查询到的结果
	 */
	public Object queryOneRow(String sql,Class clazz,Object[] params){
		List list = queryRows(sql, clazz, params);
		
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
	/**
	 * 根据主键的值直接查找对应的对象
	 * @return
	 */
	public Object queryById(Class clazz,Object id){
		TableInfo tableinfo = TableContext.poClassTableMap.get(clazz);
		ColumnInfo onlyPriKey = tableinfo.getOnlyPriKey();
		String sql = "select * from "+tableinfo.getTname()+" where "+onlyPriKey.getName()+"=?";
		return queryOneRow(sql, clazz, new Object[]{id});
	}
	/**
	 * 查询返回一个值（一行一列），并将该值返回
	 * @param sql
	 * @param params
	 * @return 返回查询到的结果
	 */
	public Object queryValue(String sql,Object[] params){
		
		return dmlTemplate(sql, params, null, new CallBack(){

			@Override
			public Object doExecute(Connection conn, java.sql.PreparedStatement ps, ResultSet rs) {
				Object value = null;
				//多行
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
	 * 查询返回一个数字（一行一列），并将该值返回
	 * @param sql
	 * @param params
	 * @return 返回查询到的数字
	 */
	public Number queryNumber(String sql,Object[] params){
		return (Number) queryValue(sql, params);
	}
	/**
	 * 分页查询
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
