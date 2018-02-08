package sh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import sh.po.Message;
import sh.po.ReceiveInfo;
import sh.po.User;
import sh.service.ReceiveService;
import sh.serviceImpl.ReceiveServiceImpl;

public class GetMessageAction {
	
	private ReceiveService rs=new ReceiveServiceImpl();
	private List<Message> listmsg;
	private List<ReceiveInfo> list;
	
	
	public List<ReceiveInfo> getList() {
		return list;
	}

	public void setList(List<ReceiveInfo> list) {
		this.list = list;
	}

	public List<Message> getListmsg() {
		return listmsg;
	}

	public void setListmsg(List<Message> listmsg) {
		this.listmsg = listmsg;
	}

	public String getDelStataMsg(){
		User u = (User) ActionContext.getContext().getSession().get("user");
		listmsg = rs.getDelMsg(u);
		
		return "delete";
	}

	public String getHaveMsg(){
		User u = (User) ActionContext.getContext().getSession().get("user");
		list=rs.getHaveMsg(u);
		return "haveMsg";
	}

	public ReceiveService getRs() {
		return rs;
	}

	public void setRs(ReceiveService rs) {
		this.rs = rs;
	}


}
