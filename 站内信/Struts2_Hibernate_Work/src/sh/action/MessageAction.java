package sh.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import sh.po.Message;
import sh.po.User;
import sh.service.MessageService;
import sh.serviceImpl.MessageServiceImpl;

public class MessageAction {

	private String title;
	private String content;
	private int sendId;
	private String userids;
	private MessageService ms=new MessageServiceImpl();
	private List<Message> list;

	private String msg;

	public String listMsg() {
		User u = (User) ActionContext.getContext().getSession().get("user");

		list = ms.getMsg(u.getId());

		return "sendMsg";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String add() {

		ms.addMS(title, content, sendId, userids, new Date());
		msg = "·¢ËÍ³É¹¦£¡";
		return "ok";
	}

	public int getSendId() {
		return sendId;
	}

	public void setSendId(int sendId) {
		this.sendId = sendId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserids() {
		return userids;
	}

	public void setUserids(String userids) {
		this.userids = userids;
	}

	public MessageService getMs() {
		return ms;
	}

	public void setMs(MessageService ms) {
		this.ms = ms;
	}

	public List<Message> getList() {
		return list;
	}

	public void setList(List<Message> list) {
		this.list = list;
	}

}
