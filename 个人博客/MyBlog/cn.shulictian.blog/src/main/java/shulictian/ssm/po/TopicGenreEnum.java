package shulictian.ssm.po;

public enum TopicGenreEnum {
	
	ORIGINAL(0),TRANSMIT(1),TRANSLATION(2);
	
	private int code;
	
	private TopicGenreEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
