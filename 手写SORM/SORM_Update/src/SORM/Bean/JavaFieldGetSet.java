package SORM.Bean;
/**
 * ��װ��java���Ժ� get set ������Դ����
 * @author TIan
 *
 */
public class JavaFieldGetSet {
	private String fieldInfo; //���Ե�Դ����Ϣ  �� private int userId;
	private String getInfo;	//get������Դ����Ϣ �� public int getUserId(){}
	private String setInfo; //set������Դ����Ϣ �� public void setUserId(int id){this.id=id;}
	
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
