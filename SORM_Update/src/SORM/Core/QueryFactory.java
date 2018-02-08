package SORM.Core;
/**
 * 创建Query工厂类
 * @author TIan
 *
 */
public class QueryFactory {
	
	private static Query prototypeObj;
	
	static{
		try {
			Class c = Class.forName(DBManager.getConf().getQueryClass());
			prototypeObj = (Query) c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private QueryFactory(){//私有构造器
	}
	
	public static Query createQuery(){
		try {
			return (Query) prototypeObj.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		
//		try {
//			return (Query) c.newInstance();
//		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return new MysqlQuery();
	}
	
}
