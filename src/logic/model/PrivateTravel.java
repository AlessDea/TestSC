package logic.model;

public class PrivateTravel {
	
	private Integer idTravel;
	private String creator;
	private String destination;
	private String startDate;
	private String endDate;
	private String description;
	private Integer numMaxUt;
	private String travelName;
	private Hotel hotelInfo;
	
	public PrivateTravel() {
		idTravel = 0;
		creator = "";
		destination = "";
		startDate = "";
		endDate = "";
		description = "";
		numMaxUt = 0;
		travelName = "";
		hotelInfo = null;
	}
	
	public Integer getIdTravel() {
		return idTravel;
	}

	public void setIdTravel(Integer idTravel) {
		this.idTravel = idTravel;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	
	public Integer getNumMaxUt() {
		return numMaxUt;
	}

	public void setNumMaxUt(Integer numMaxUt) {
		this.numMaxUt = numMaxUt;
	}
	
	public Hotel getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(Hotel hotelInfo) {
		this.hotelInfo = hotelInfo;
	}
	
}
