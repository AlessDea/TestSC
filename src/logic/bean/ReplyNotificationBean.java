package logic.bean;

public class ReplyNotificationBean {
	
	private int id;
	private String msg;
	private String date;
	private String sender;
	
	public ReplyNotificationBean() {
		
	}
	
	public ReplyNotificationBean(int id, String sender, String msg, String date) {
		this.msg = msg;
		this.date = date;
		this.id = id;
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
