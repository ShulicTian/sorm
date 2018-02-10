package shulictian.ssm.po;

public enum TopicStateEnum {
	
	AUTHSTR("待审核",0),PASS("审核通过",1),NOTPASS("审核未通过",3),REMOVE("删除到垃圾箱",2),DELETE("彻底删除帖子",4),REPORT("举报帖子",5),DRAFT("草稿",6);
	
	private String name;
	private int code;

	private TopicStateEnum(String name, int code) {
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
