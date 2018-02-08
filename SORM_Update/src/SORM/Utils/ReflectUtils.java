package SORM.Utils;

import java.lang.reflect.Method;

/**
 * 封装了反射常用的操作
 * @author TIan
 *
 */
public class ReflectUtils {
	/**
	 * 调用obj对象对应属性fieldName的get方法
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object invokeGet(String fieldName,Object obj){
		try {
			Class c = obj.getClass();
			Method m = c.getDeclaredMethod("get"+StringUtils.firestChar2UpperCase(fieldName), null);
			return m.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void invokeSet(Object obj,String columnName,Object columnValue){
		//调用rowObj对象的setName方法，将columnValue的值设置进去
		try {
			if(columnValue!=null){
				Method m = obj.getClass().getDeclaredMethod("set"+StringUtils.firestChar2UpperCase(columnName)
				, columnValue.getClass());
				m.invoke(obj, columnValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
