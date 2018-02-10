package sh.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {
	
	private int id;
	private String title;
	private String content;
	private User sender;
	private Date sendTime;
	
	private Set<ReceiveInfo> receiveInfos = new HashSet<ReceiveInfo>();
	
	private int del;

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@ManyToOne
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@OneToMany(mappedBy="message",cascade=CascadeType.ALL)
	public Set<ReceiveInfo> getReceiveInfos() {
		return receiveInfos;
	}

	public void setReceiveInfos(Set<ReceiveInfo> receiveInfos) {
		this.receiveInfos = receiveInfos;
	}

}
