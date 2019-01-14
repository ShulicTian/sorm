package web.ssm.po.item;

import web.ssm.po.ordermsg.Ordermsg;
import web.ssm.po.user.User;

public class Item {
	
	private int id;
	private String name;
	private double much;
	private String photo;
	private Ordermsg ordermsg;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ordermsg getOrdermsg() {
		return ordermsg;
	}

	public void setOrdermsg(Ordermsg ordermsg) {
		this.ordermsg = ordermsg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMuch() {
		return much;
	}

	public void setMuch(double much) {
		this.much = much;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
