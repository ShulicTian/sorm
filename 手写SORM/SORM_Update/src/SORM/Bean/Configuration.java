package SORM.Bean;
/**
 * ����������Ϣ
 * @author TIan
 *
 */
public class Configuration {
	private String Driver;	//������
	private	String Url;	//JDBCUR
	private	String User; //���ݿ��û���
	private	String Pwd;	//�û�������
	private String UsingDB;	//����ʹ���ĸ����ݿ�
	private	String SrcPath;	//��ĿԴ��·��
	private String PersistoPackage;	//ɨ������java��İ�
	private String queryClass;  //��Ŀʹ�õĲ�ѯ���·��
	private int poolMinSize;  //���ӳ���С��������
	private int poolMaxSize;  //���ӳ�����������
	
	public Configuration() {
		super();
	}
	public Configuration(String driver, String url, String user, String pwd, String usingDB, String srcPath,
			String persistoPackage,String queryclass,int poolminsize,int poolmaxsize) {
		super();
		this.Driver = driver;
		this.Url = url;
		this.User = user;
		this.Pwd = pwd;
		this.UsingDB = usingDB;
		this.SrcPath = srcPath;
		this.PersistoPackage = persistoPackage;
		this.queryClass = queryclass;
		this.poolMinSize = poolminsize;
		this.poolMaxSize = poolmaxsize;
	}
	
	public int getPoolMinSize() {
		return poolMinSize;
	}
	public void setPoolMinSize(int poolMinSize) {
		this.poolMinSize = poolMinSize;
	}
	public int getPoolMaxSize() {
		return poolMaxSize;
	}
	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}
	public String getQueryClass() {
		return queryClass;
	}
	public void setQueryClass(String queryClass) {
		this.queryClass = queryClass;
	}
	public String getDriver() {
		return Driver;
	}
	public void setDriver(String driver) {
		Driver = driver;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getUsingDB() {
		return UsingDB;
	}
	public void setUsingDB(String usingDB) {
		UsingDB = usingDB;
	}
	public String getSrcPath() {
		return SrcPath;
	}
	public void setSrcPath(String srcPath) {
		SrcPath = srcPath;
	}
	public String getPersistoPackage() {
		return PersistoPackage;
	}
	public void setPersistoPackage(String persistoPackage) {
		PersistoPackage = persistoPackage;
	}
	
	
}
