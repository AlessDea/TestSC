package logic.bean;

import logic.exceptions.DescriptionSyntaxException;
import logic.exceptions.DestinationSyntaxException;
import logic.exceptions.NumTravSyntaxException;
import logic.exceptions.TravelNameSyntaxException;

public class PrivateTravelBean {
	
	private String idTravel;
	private String creator;
	private String destination;
	private String startDate;
	private String endDate;
	private String numMaxUt;
	private String description;
	private String travelName;
	private HotelBean hotelInfo;
	
	public PrivateTravelBean() {
		idTravel = "";
		creator = "";
		destination = "";
		startDate = "";
		endDate = "";
		description = "";
		travelName = "";
		hotelInfo = null;
	}
	
	public String getIdTravel() {
		return idTravel;
	}

	public void setIdTravel(String idTravel) {
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
	
	public void setAndValidateDescription(String description) throws DescriptionSyntaxException {
		description = this.validateDescription(description);
		this.description = description;
	}
	
	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	
	public HotelBean getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(HotelBean hotelInfo) {
		this.hotelInfo = hotelInfo;
	}
	
	public String getNumMaxUt() {
		return numMaxUt;
	}

	public void setNumMaxUt(String numMaxUt) {
		this.numMaxUt = numMaxUt;
	}
	
	public void setAndValidateDestination(String destination) throws DestinationSyntaxException {
		destination = this.validateDestination(destination);
		this.destination = destination;
	}
	
	public void setAndValidateTravelName(String travelName) throws TravelNameSyntaxException {
		travelName = this.validateTravelName(travelName);
		this.travelName = travelName;
	}
	
	public void setAndValidateNumTravellers(String numTrav) throws NumTravSyntaxException {
		this.validateNumTrav(numTrav);
		this.numMaxUt = numTrav;
	}
	
	private String validateDescription(String description) throws DescriptionSyntaxException {
		if(description.length() <= 10 || description.length() >= 400)
			throw new DescriptionSyntaxException("Description syntax error: minimum 10 characters and maximum 400 characters");
		description = this.checkApostrophe(description);
		return description;
	}
	
	private String validateDestination(String destination) throws DestinationSyntaxException {
		
		if(destination.length() == 0) 
			throw new DestinationSyntaxException("Please, insert destination to continue...");
		
		if(!(destination.length() <= 45 && this.validateFormatDestination(destination))) 
			throw new DestinationSyntaxException("Destination name syntax error:\n - insert only characters, \' or blank");
		
		destination = this.checkApostrophe(destination);
		return destination;
	}
	
	public void validateNumTrav(String numTrav) throws NumTravSyntaxException {
		
		if(numTrav.length() == 0)
			throw new NumTravSyntaxException("Please, insert number of travellers to continue...");
		
		for(int i =0; i<numTrav.length(); i++){
            char c = numTrav.charAt(i);
            if(!Character.isDigit(c))
            	throw new NumTravSyntaxException("Number of travellers syntax error:\n - insert only digits!");

        }
		
	}
	
	//metodo che verifica il nome del viaggio
	private String validateTravelName(String travelName) throws TravelNameSyntaxException {
		if(travelName.length() == 0) 
			throw new TravelNameSyntaxException("Please, insert travel name to continue...");
		
		if(!(travelName.length() <= 45 && this.validateStringWithApostrophe(travelName))) 
			throw new TravelNameSyntaxException("Travel name syntax error:\n - insert only characters, digits, \' or blank");
		
		travelName = this.checkApostrophe(travelName);
		return travelName;
	}
	
	private boolean validateFormatDestination(String str) {
		str = str.toLowerCase();
	    char[] charArray = str.toCharArray();
	    for (int i = 0; i < charArray.length; i++) {
		    char ch = charArray[i];
		    if (!((ch >= 'a' && ch <= 'z') || ch == 39 || ch == ' ' )) {
			    return false;
		    }
	    }
	    return true;
	}
	
	private boolean validateStringWithApostrophe(String str) {
	   str = str.toLowerCase();
	   char[] charArray = str.toCharArray();
	   for (int i = 0; i < charArray.length; i++) {
		   char ch = charArray[i];
		   if (!((ch >= 'a' && ch <= 'z') || ch == 39 || ch == ' ' || (ch >= 48 && ch <= 57 ))) {
			   return false;
		   }
	   }
	   return true;
	}
	
	private String checkApostrophe(String str) {
		String result = "";
		for(int i =0; i<str.length(); i++){
            char c = str.charAt(i);
            result = result.concat(String.valueOf(c));
            if(c == 39) {
               result = result.concat(String.valueOf(c));
            }
        }
		return result;
	}
	
}
