package sh.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	private int id;
	private String uname;
	private String pwd;
	private String ip;
	private Date joinTime;
	
	private Set<Message> message = new HashSet<Message>();
	private Set<ReceiveInfo> receiveInfos = new HashSet<ReceiveInfo>();

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	@OneToMany(mappedBy="sender")
	public Set<Message> getMessage() {
		return message;
	}

	public void setMessage(Set<Message> message) {
		this.message = message;
	}

	@OneToMany(mappedBy="receiver")
	public Set<ReceiveInfo> getReceiveInfos() {
		return receiveInfos;
	}

	public void setReceiveInfos(Set<ReceiveInfo> receiveInfos) {
		this.receiveInfos = receiveInfos;
	}

}
