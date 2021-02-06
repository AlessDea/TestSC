package logic.model;

public class JoinNotification {
	
	private int id;
	private User sender;
	private User receiver;
	private PublicTravel travel;
	private String date;
	private String msg;

	public JoinNotification() {}
	
	public JoinNotification(String username, String travelName, int idTravel, String date) {
		this.receiver.setUsername(username);
		this.travel.setIdTravel(idTravel);
		this.travel.setTravelName(travelName);
		this.date = date;
	}	
	
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public void setTravel(PublicTravel viaggioGruppo) {
		this.travel = viaggioGruppo;
	}
	
	public PublicTravel getTravel() {
		return this.travel;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
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
	
}

