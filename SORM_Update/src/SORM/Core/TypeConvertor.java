package SORM.Core;
/**
 * 负责java数据类型和数据库数据类型的相互转换
 * @author TIan
 *
 */
public interface TypeConvertor {
	/**
	 * 将数据库的数据类型转换成java的数据类型
	 * @param columnType
	 * @return java的数据类型
	 */
	public String databaseType2JavaType(String columnType);
	/**
	 * 将java数据类型转换成数据库数据类型
	 * @param javaDataType
	 * @return
	 */
	public String javaType2DatabaseType(String javaDataType);
	
}
