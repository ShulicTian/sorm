package SORM.Utils;
/**
 * ��װ���ַ������õĲ���
 * @author TIan
 *
 */
public class StringUtils {
	/**
	 * ��Ŀ���ַ�������ĸ��Ϊ��д
	 * @param str
	 * @return
	 */
	public static String firestChar2UpperCase(String str){
		str = str.toUpperCase().substring(0,1)+str.substring(1);
		return str;
	}
}
