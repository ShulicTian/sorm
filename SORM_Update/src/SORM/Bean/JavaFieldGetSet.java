package SORM.Bean;
/**
 * 封装了java属性和 get set 方法的源代码
 * @author TIan
 *
 */
public class JavaFieldGetSet {
	private String fieldInfo; //属性的源码信息  如 private int userId;
	private String getInfo;	//get方法的源码信息 如 public int getUserId(){}
	private String setInfo; //set方法的源码信息 如 public void setUserId(int id){this.id=id;}
	
	@Override
	public String toString(){
		System.out.println(fieldInfo);
		System.out.println(getInfo);
		System.out.println(setInfo);
		return super.toString();
	}
	
	public JavaFieldGetSet() {
		super();
	}
	
	public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
		super();
		this.fieldInfo = fieldInfo;
		this.getInfo = getInfo;
		this.setInfo = setInfo;
	
	}
	public String getFieldInfo() {
		return fieldInfo;
	}
	public void setFieldInfo(String fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	public String getGetInfo() {
		return getInfo;
	}
	public void setGetInfo(String getInfo) {
		this.getInfo = getInfo;
	}
	public String getSetInfo() {
		return setInfo;
	}
	public void setSetInfo(String setInfo) {
		this.setInfo = setInfo;
	}
	
}
