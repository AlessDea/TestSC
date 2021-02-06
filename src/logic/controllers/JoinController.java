package logic.controllers;

import java.util.ArrayList;
import java.util.List;
import logic.bean.HotelBean;
import logic.bean.PublicTravelBean;
import logic.dao.PublicTravelDao;
import logic.exceptions.DuplicateRequestException;
import logic.exceptions.SystemException;
import logic.model.JoinNotification;
import logic.model.User;
import logic.model.PublicTravel;

public class JoinController {
	
	public List<PublicTravelBean> allTravels(String username) throws SystemException {
		List<PublicTravel> travels;
		List<PublicTravelBean> travelsBean = new ArrayList<>();
		
		travels = PublicTravelDao.retreiveJoinableGrTravels(username);
		
		for(PublicTravel vg : travels) {
			
			HotelBean hotelBean = new HotelBean();
			hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
            hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
            hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
            hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
            hotelBean.setPrice(vg.getHotelInfo().getPrice());
            hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));
            
            PublicTravelBean viaggioGruppoBean = new PublicTravelBean();
            viaggioGruppoBean.setCreator(vg.getCreator());
            viaggioGruppoBean.setDestination(vg.getDestination());
            viaggioGruppoBean.setDescription(vg.getDescription());
            viaggioGruppoBean.setStartDate(vg.getStartDate());
            viaggioGruppoBean.setEndDate(vg.getEndDate());
            viaggioGruppoBean.setHotelInfo(hotelBean);
            viaggioGruppoBean.setAvailableSeats(String.valueOf(vg.getAvailableSeats()));
            viaggioGruppoBean.setNumMaxUt(String.valueOf(vg.getNumMaxUt()));
            viaggioGruppoBean.setIdTravel(String.valueOf(vg.getIdTravel()));
            viaggioGruppoBean.setTravelName(vg.getTravelName());
            
            travelsBean.add(viaggioGruppoBean);
		}
        
		return travelsBean;
	}

	public List<PublicTravelBean> filterTravels(String username, boolean sea, boolean mountain, boolean art, boolean young, String continent) throws SystemException {
		
		List<PublicTravelBean> travelsBean = new ArrayList<>();
		int location = 2;
		
		/* Se mare selezionato */
		if(sea) {
			location = 0;
		}
		/* Se montagna selezionata */
		else if(mountain) {
			location = 1;
		}
		/* Converti art/young */
		int artVal = (Boolean.TRUE.equals(art)) ? 1 : 0;
	    int youngVal = (Boolean.TRUE.equals(young)) ? 1 : 0;
		
		List<PublicTravel> travels = PublicTravelDao.retreiveSpecialJoinableGrTravels(username, location, artVal, youngVal, continent);

		for(PublicTravel vg : travels) {
			
			HotelBean hotelBean = new HotelBean();
            hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
            hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
            hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
            hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
            hotelBean.setPrice(vg.getHotelInfo().getPrice());
            hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));
            
            PublicTravelBean viaggioGruppoBean = new PublicTravelBean();
            viaggioGruppoBean.setCreator(vg.getCreator());
            viaggioGruppoBean.setDestination(vg.getDestination());
            viaggioGruppoBean.setDescription(vg.getDescription());
            viaggioGruppoBean.setStartDate(vg.getStartDate());
            viaggioGruppoBean.setEndDate(vg.getEndDate());
            viaggioGruppoBean.setHotelInfo(hotelBean);
            viaggioGruppoBean.setAvailableSeats(String.valueOf(vg.getAvailableSeats()));
            viaggioGruppoBean.setNumMaxUt(String.valueOf(vg.getNumMaxUt()));
            viaggioGruppoBean.setIdTravel(String.valueOf(vg.getIdTravel()));
            viaggioGruppoBean.setTravelName(vg.getTravelName());
            
            travelsBean.add(viaggioGruppoBean);
		}
		
		return travelsBean;
	}
	
	public void sendRequest(PublicTravelBean viaggioGruppoBean, String username) throws SystemException, DuplicateRequestException {
		
		int idTravel = Integer.parseInt(viaggioGruppoBean.getIdTravel());
		String travelName = viaggioGruppoBean.getTravelName();
		User sender = new User();
		sender.setUsername(username);
		PublicTravel viaggioGruppo = new PublicTravel();
		viaggioGruppo.setIdTravel(idTravel);
		JoinNotification joinNotification = new JoinNotification();
		joinNotification.setSender(sender);
		joinNotification.setTravel(viaggioGruppo);
		joinNotification.setMsg(sender.getUsername() + " ha richiesto di unirsi al viaggio: " + travelName);

		/* Controllo che non esista già la mia richiesta */
		PublicTravelDao.checkRequests(idTravel, username);
		
		PublicTravelDao.joinVGruppo(joinNotification);
	}
	
}
