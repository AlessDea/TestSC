package logic.model;

public class FollowNotification {
	
	private int id;
	private String msg;
	private String date;
	private User sender;
	private User receiver;
	
	public FollowNotification() {}

	public FollowNotification(User sender, User receiver, String msg) {
		this.sender = sender;
		this.receiver = receiver;
		this.msg = msg;
	}
	
	public void setMsg(String u) {
		this.msg = u;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setDate(String d) {
		this.date = d;
	}
	
	public String getDate() {
		return this.date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

