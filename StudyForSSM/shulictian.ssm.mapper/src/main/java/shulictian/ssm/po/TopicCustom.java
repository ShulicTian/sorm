package shulictian.ssm.po;

import java.util.List;

public class TopicCustom extends Topic {

	private Integer readNum;
	private Integer commentNum;
	private List<Comments> comments;
	private int state;
	private UserCustom userCus;

	public UserCustom getUserCus() {
		return userCus;
	}

	public void setUserCus(UserCustom userCus) {
		this.userCus = userCus;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

}
