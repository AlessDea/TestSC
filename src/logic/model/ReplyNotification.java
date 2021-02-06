package logic.model;

public class ReplyNotification {
	
	private int id;
	private User receiver;
	private String msg;
	private String date;
	private User sender;
	
	public ReplyNotification() {
		this.id = 0;
		this.receiver = null;
		this.sender = null;
		this.msg = "";
		this.date = "";
	}
	
	public ReplyNotification(User sender, User receiver, String msg) {
		this.sender = sender;
		this.receiver = receiver;
		this.msg = msg;
	}
		
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
		
}
