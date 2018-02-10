package sh.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import sh.po.User;
import sh.service.UserService;
import sh.serviceImpl.UserServiceImpl;

public class UserAction extends ActionSupport {

	private String uname;
	private String pwd;
	private UserService us=new UserServiceImpl();

	private String msg;

	private List<User> list;
	
	public String listAllUser() {

		list = us.getAll();
		
		return NONE;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public String reg() {

		String ip = ServletActionContext.getRequest().getRemoteAddr();
		us.register(uname, pwd, ip, new Date());

		msg = "注册成功！！！";

		return "ok";

	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String login() {
		System.out.println("Action...."+uname);
		User u = us.login(uname, pwd);

		if (u == null) {

			msg = "用户名或密码错误！！！";

			return "login";

		} else {

			ActionContext.getContext().getSession().put("user", u);

			return "index";

		}

	}

}
