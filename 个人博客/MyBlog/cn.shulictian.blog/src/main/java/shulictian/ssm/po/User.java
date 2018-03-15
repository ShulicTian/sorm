package shulictian.ssm.po;

import java.util.Date;
import java.util.List;

public class User {

	private Integer id;
	private String userName;
	private String passWord;
	private Date regTime;
	private int type;
	private String nickName;
	private UserMessage userMsg;
	private List<UserProMsg> userProMsg;
	private List<Topic> topics;

	public List<UserProMsg> getProMsg() {
		return userProMsg;
	}

	public void setProMsg(List<UserProMsg> userProMsg) {
		this.userProMsg = userProMsg;
	}

	public UserMessage getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(UserMessage userMsg) {
		this.userMsg = userMsg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

}
