package sh.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="receiveinfo")
public class ReceiveInfo {
	
	private int id;
	private User receiver;
	private Message message;
	private int readStata;
	private int delStata;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@ManyToOne
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public int getReadStata() {
		return readStata;
	}

	public void setReadStata(int readStata) {
		this.readStata = readStata;
	}

	public int getDelStata() {
		return delStata;
	}

	public void setDelStata(int delStata) {
		this.delStata = delStata;
	}

}
