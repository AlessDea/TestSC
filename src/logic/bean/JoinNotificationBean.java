package logic.bean;

public class JoinNotificationBean {
	
	private int id;
	private PublicTravelBean viaggioGruppoBean;
	private String msg;
	private String date;
	private String sender;
	
	public JoinNotificationBean() {}

	public JoinNotificationBean(int id, String sender, PublicTravelBean viaggioGruppoBean, String msg, String date) {
		this.viaggioGruppoBean = viaggioGruppoBean;
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

	public PublicTravelBean getViaggioGruppoBean() {
		return viaggioGruppoBean;
	}

	public void setViaggioGruppoBean(PublicTravelBean viaggioGruppoBean) {
		this.viaggioGruppoBean = viaggioGruppoBean;
	}
	
}
