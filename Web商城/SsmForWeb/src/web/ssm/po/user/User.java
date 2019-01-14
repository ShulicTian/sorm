package web.ssm.po.user;

import java.util.Date;
import java.util.List;

import web.ssm.po.order.Order;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Date regtime;

    private Usermsg usermsg;

    private List<Useraddress> useraddress;
    
    private List<Order> orders;

    public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

	public Usermsg getUsermsg() {
		return usermsg;
	}

	public void setUsermsg(Usermsg usermsg) {
		this.usermsg = usermsg;
	}

	public List<Useraddress> getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(List<Useraddress> useraddress) {
		this.useraddress = useraddress;
	}

}