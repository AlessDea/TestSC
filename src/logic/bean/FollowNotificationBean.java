package logic.bean;

public class FollowNotificationBean {

	private int id;
	private String msg;
	private String date;
	private String sender;
	
	public FollowNotificationBean() {}
	
	public FollowNotificationBean(int id, String sender, String msg, String date) {
		this.id = id;
		this.msg = msg;
		this.date = date;
		this.sender = sender;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
}
