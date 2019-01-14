package web.ssm.po.user;

public class UserQueryVO {

	private String name;
	private String password;
	private UserCustom uc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserCustom getUc() {
		return uc;
	}

	public void setUc(UserCustom uc) {
		this.uc = uc;
	}

}
