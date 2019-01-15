package SORM.Bean;
/**
 * 管理配置信息
 * @author TIan
 *
 */
public class Configuration {
	private String Driver;	//驱动类
	private	String Url;	//JDBCUR
	private	String User; //数据库用户名
	private	String Pwd;	//用户名密码
	private String UsingDB;	//正在使用哪个数据库
	private	String SrcPath;	//项目源码路径
	private String PersistoPackage;	//扫描生成java类的包
	private String queryClass;  //项目使用的查询类的路径
	private int poolMinSize;  //连接池最小的连接数
	private int poolMaxSize;  //连接池最大的连接数
	
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
