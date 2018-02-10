package web.ssm.po.order;

import java.util.Date;
import java.util.List;

import web.ssm.po.ordermsg.Ordermsg;
import web.ssm.po.user.User;

public class Order {
	
	private int id;
	private String orderNum;
	private Date createTime;
	private String remarks;
	private List<Ordermsg> ordermsg;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Ordermsg> getOrdermsgs() {
		return ordermsg;
	}

	public void setOrdermsgs(List<Ordermsg> ordermsgs) {
		this.ordermsg = ordermsgs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
