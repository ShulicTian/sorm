package SORM.Utils;
/**
 * 封装了字符串常用的操作
 * @author TIan
 *
 */
public class StringUtils {
	/**
	 * 将目标字符串首字母变为大写
	 * @param str
	 * @return
	 */
	public static String firestChar2UpperCase(String str){
		str = str.toUpperCase().substring(0,1)+str.substring(1);
		return str;
	}
}
