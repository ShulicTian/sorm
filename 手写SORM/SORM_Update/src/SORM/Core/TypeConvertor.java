package SORM.Core;
/**
 * ����java�������ͺ����ݿ��������͵��໥ת��
 * @author TIan
 *
 */
public interface TypeConvertor {
	/**
	 * �����ݿ����������ת����java����������
	 * @param columnType
	 * @return java����������
	 */
	public String databaseType2JavaType(String columnType);
	/**
	 * ��java��������ת�������ݿ���������
	 * @param javaDataType
	 * @return
	 */
	public String javaType2DatabaseType(String javaDataType);
	
}
