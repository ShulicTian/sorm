package shulictian.ssm.po;

public enum UserTypeEnum {
	
	COMMON(0),ADMIN(1),SUPERADMIN(2),PUNISH(3);
	
	private int code;
	
	private UserTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
