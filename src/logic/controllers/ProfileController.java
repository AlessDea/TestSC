package logic.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import logic.bean.HotelBean;
import logic.bean.PrivateTravelBean;
import logic.bean.PublicTravelBean;
import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.dao.PrivateTravelDao;
import logic.dao.PublicTravelDao;
import logic.exceptions.DefaultPhotoException;
import logic.exceptions.DuplicateUsernameException;
import logic.exceptions.EmailException;
import logic.exceptions.PasswordException;
import logic.exceptions.SystemException;
import logic.exceptions.UsernameException;
import logic.model.PrivateTravel;
import logic.model.PublicTravel;
import logic.model.User;

public class ProfileController {
	
	private static final String DB_IMAGES = "DBImages";
	private static final String IMAGES = "Images";
	private static final String WEB_CONTENT = "WebContent";
	private static final String USR_DIR = "user.dir";
	
	public List<String> retrieveTravelPhotos(String idViaggio) throws SystemException {
		List<String> filenames = PrivateTravelDao.retrieveTravelPhoto(Integer.valueOf(idViaggio));
		List<String> savePaths = new ArrayList<>();
		String currentDirectoryProject = System.getProperty(USR_DIR);
    	String savePath = "";
		for(String filename : filenames) {
			savePath = currentDirectoryProject + File.separator + WEB_CONTENT + File.separator + IMAGES + File.separator + DB_IMAGES + File.separator +  filename;
			savePaths.add(savePath);
		}
		return savePaths;	
	}
	
	public List<String> retrieveTravelGroupPhotos(String idViaggioGruppo) throws SystemException {
		List<String> filenames = PublicTravelDao.retrieveTravelGroupPhoto(Integer.valueOf(idViaggioGruppo));
		List<String> savePaths = new ArrayList<>();
		String currentDirectoryProject = System.getProperty(USR_DIR);
    	String savePath = "";
		for(String filename : filenames) {
			savePath = currentDirectoryProject + File.separator + WEB_CONTENT + File.separator + IMAGES + File.separator + DB_IMAGES + File.separator +  filename;
			savePaths.add(savePath);
		}
		return savePaths;	
	}
	
	public List<PrivateTravelBean> loadMyOldT(String username) throws SystemException {
		List<PrivateTravel> travels;
		List<PrivateTravelBean> travelsBean = new ArrayList<>();
		
		travels = PrivateTravelDao.retrieveTravels(username);
	
		for(PrivateTravel vg : travels) {
			HotelBean hotelBean = new HotelBean();
            hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
            hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
            hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
            hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
            hotelBean.setPrice(vg.getHotelInfo().getPrice());
            hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));
            
            PrivateTravelBean vgBean = new PrivateTravelBean();
            vgBean.setCreator(vg.getCreator());
            vgBean.setDestination(vg.getDestination());
            vgBean.setDescription(vg.getDescription());
            vgBean.setStartDate(vg.getStartDate());
            vgBean.setEndDate(vg.getEndDate());
            vgBean.setHotelInfo(hotelBean);
            vgBean.setTravelName(vg.getTravelName());
            vgBean.setIdTravel(String.valueOf(vg.getIdTravel()));
            vgBean.setNumMaxUt(String.valueOf(vg.getNumMaxUt()));
            
            travelsBean.add(vgBean);
		}

		return travelsBean;
	}
	
	public List<PublicTravelBean> loadMyOldTGR(String username) throws SystemException {
		List<PublicTravel> travels;
		List<PublicTravelBean> travelsBean = new ArrayList<>();
		
		travels = PublicTravelDao.retrieveGroupTravels(username);
		for(PublicTravel vg : travels) {
			HotelBean hotelBean = new HotelBean();
			hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
			hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
			hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
			hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
			hotelBean.setPrice(vg.getHotelInfo().getPrice());
			hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));
            
            PublicTravelBean vgrBean = new PublicTravelBean();
            vgrBean.setCreator(vg.getCreator());
            vgrBean.setDestination(vg.getDestination());
            vgrBean.setDescription(vg.getDescription());
            vgrBean.setStartDate(vg.getStartDate());
            vgrBean.setEndDate(vg.getEndDate());
            vgrBean.setHotelInfo(hotelBean);
            vgrBean.setAvailableSeats(String.valueOf(vg.getAvailableSeats()));
            vgrBean.setNumMaxUt(String.valueOf(vg.getNumMaxUt()));
            vgrBean.setIdTravel(String.valueOf(vg.getIdTravel()));
            vgrBean.setTravelName(vg.getTravelName());
			travelsBean.add(vgrBean);
		}
		
		return travelsBean;
	}
	
	public List<PrivateTravelBean> loadMyUpcomingT(String username) throws SystemException {
		List<PrivateTravel> travels;
		List<PrivateTravelBean> travelsBean = new ArrayList<>();
		
		travels = PrivateTravelDao.retrieveNextBookedTravels(username);
		
		for(PrivateTravel vg : travels) {
			HotelBean hotelBean = new HotelBean();
            hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
            hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
            hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
            hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
            hotelBean.setPrice(vg.getHotelInfo().getPrice());
            hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));
            
            PrivateTravelBean vgBean = new PrivateTravelBean();
            vgBean.setCreator(vg.getCreator());
            vgBean.setDestination(vg.getDestination());
            vgBean.setDescription(vg.getDescription());
            vgBean.setStartDate(vg.getStartDate());
            vgBean.setEndDate(vg.getEndDate());
            vgBean.setHotelInfo(hotelBean);
            vgBean.setTravelName(vg.getTravelName());
            vgBean.setIdTravel(String.valueOf(vg.getIdTravel()));
            vgBean.setNumMaxUt(String.valueOf(vg.getNumMaxUt()));
            
            travelsBean.add(vgBean);
		}
		
		return travelsBean;
	}
	
	public List<PublicTravelBean> loadMyUpcomingTGR(String username) throws SystemException {
		
		List<PublicTravel> travels;
		List<PublicTravelBean> travelsBean = new ArrayList<>();
		travels = PublicTravelDao.retreiveMyNextBookedGrTravells(username);
		for(PublicTravel vg : travels) {
			HotelBean hotelBean = new HotelBean();
			hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
			hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
			hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
			hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
			hotelBean.setPrice(vg.getHotelInfo().getPrice());
			hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));
            
            PublicTravelBean vgrBean = new PublicTravelBean();
            vgrBean.setCreator(vg.getCreator());
            vgrBean.setDestination(vg.getDestination());
            vgrBean.setDescription(vg.getDescription());
            vgrBean.setStartDate(vg.getStartDate());
            vgrBean.setEndDate(vg.getEndDate());
            vgrBean.setHotelInfo(hotelBean);
            vgrBean.setAvailableSeats(String.valueOf(vg.getAvailableSeats()));
            vgrBean.setNumMaxUt(String.valueOf(vg.getNumMaxUt()));
            vgrBean.setIdTravel(String.valueOf(vg.getIdTravel()));
            vgrBean.setTravelName(vg.getTravelName());
			travelsBean.add(vgrBean);
		}
		
		return travelsBean;
	}
	
	public String retrieveUserPhoto(String username) throws DefaultPhotoException, SystemException {
		String filename = "";
		filename = UserDao.retrieveUserPhoto(username);
		String currentDirectoryProject = System.getProperty(USR_DIR);
		if(filename.equals("")) 
			return filename;
		else
			return currentDirectoryProject + File.separator + WEB_CONTENT + File.separator + IMAGES + File.separator + DB_IMAGES + File.separator +  filename;
	}
	
	public void changeEmail(String username, String email) throws EmailException, SystemException {
		UserDao.modifEmail(username, email);
	}
	
	public void changePassword(String username, String newPassword) throws PasswordException, SystemException {
		UserDao.modifPsw(username, newPassword);
	}
	
	// metodo per inserire il nuovo username nei viaggi private
	private void updateTravelInfo(String newUsr, String usr) throws SystemException {
		UserDao.modifyTravelInfo(newUsr, usr);
	}
	
	// metodo per inserire il nuovo username nei viaggi public
	private void updateTravelGrInfo(String newUsr, String usr) throws SystemException{
		UserDao.modifyTravelGRInfo(newUsr, usr);
	}
	
	public void changeUsername(String username, String newUsername) throws SystemException, UsernameException, DuplicateUsernameException {
		try {
			UserDao.usernameChecker(newUsername);
			UserDao.modifUsrn(username, newUsername);
			this.updateTravelInfo(newUsername, username);
			this.updateTravelGrInfo(newUsername, username);
		} catch (DuplicateUsernameException due) {
			throw due;
		} catch (UsernameException ue) {
			throw ue;
		} catch (SystemException sye) {
			throw sye;
		}	
	}
	
	public void changePhoto(String username, File file) throws DefaultPhotoException, SystemException {
		String currentDirectoryProject = System.getProperty("user.dir");
    	String filename = username.concat("_").concat(file.getName());
    	String savePath = currentDirectoryProject + File.separator + "WebContent" + File.separator + "Images" + File.separator + "DBImages" + File.separator +  filename;
    	UserDao.modifyPhoto(username, file, filename);
    	File photoUser = new File(savePath);
    	try {
			Files.copy(file.toPath(), photoUser.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException("Unexpected error occured, retry!");
		}
	}
	
public List<UserBean> retrieveFollowers(String username) throws SystemException {
		
		List<User> followers;
		List<UserBean> followersBean = new ArrayList<>();
		String filename = "";
		String currentDirectoryProject = System.getProperty("user.dir");
		
		followers = UserDao.retrieveFollowersByUsername(username);
		for(User follower : followers) {
			UserBean userBean = new UserBean();
            userBean.setUsername(follower.getUsername());
            userBean.setEmail(follower.getEmail());
            
            filename = follower.getPhoto();
            if(filename.equals("")) 
				userBean.setPhoto(filename);
			else {
				filename = currentDirectoryProject.concat(File.separator).concat("WebContent").concat(File.separator).concat("Images").concat(File.separator).concat("DBImages").concat(File.separator).concat(filename);
				userBean.setPhoto(filename);
			}

            followersBean.add(userBean);
		}
		return followersBean;
	}
	
	public List<PrivateTravelBean> retrieveFollowerPrivateTravels(String username) throws SystemException {
		List<PrivateTravel> travels = PrivateTravelDao.retrieveFollowerTravelsPrivate(username);
		List<PrivateTravelBean> travelsBean = new ArrayList<>();
		
		for(PrivateTravel vg : travels) {
			travelsBean.add(viaggioToViaggioBean(vg));			
		}
		
		return travelsBean;
	}
	
	public List<PublicTravelBean> retrieveFollowerPublicTravels(String username) throws SystemException {
		List<PublicTravelBean> travelsBean = new ArrayList<>();
		List<PublicTravel> travels = PublicTravelDao.retrieveFollowerTravelsPublic(username);
		
		for(PublicTravel vgr : travels) {
			travelsBean.add(viaggioGruppoToViaggioGruppoBean(vgr));			
		}
		
		return travelsBean;
	}
	
	private PrivateTravelBean viaggioToViaggioBean(PrivateTravel vg) {
		HotelBean hotelBean = new HotelBean();
		hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
		hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
		hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
		hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
		hotelBean.setPrice(vg.getHotelInfo().getPrice());
		hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));
		
		PrivateTravelBean vgBean = new PrivateTravelBean();
		vgBean.setCreator(vg.getCreator());
		vgBean.setDescription(vg.getDescription());
		vgBean.setDestination(vg.getDestination());
		vgBean.setStartDate(vg.getStartDate());
		vgBean.setEndDate(vg.getEndDate());
		vgBean.setHotelInfo(hotelBean);
		vgBean.setTravelName(vg.getTravelName());
		vgBean.setIdTravel(String.valueOf(vg.getIdTravel()));
		
		return vgBean;
	}
	
	private PublicTravelBean viaggioGruppoToViaggioGruppoBean(PublicTravel vgr) {
		HotelBean hotelBean = new HotelBean();
		hotelBean.setBreakfast(vgr.getHotelInfo().getBreakfast());
		hotelBean.setHotelLink(vgr.getHotelInfo().getHotelLink());
		hotelBean.setHotelName(vgr.getHotelInfo().getHotelName());
		hotelBean.setNumRooms(String.valueOf(vgr.getHotelInfo().getNumRooms()));
		hotelBean.setPrice(vgr.getHotelInfo().getPrice());
		hotelBean.setStars(String.valueOf(vgr.getHotelInfo().getStars()));
		
		PublicTravelBean vgrBean = new PublicTravelBean();
		vgrBean.setCreator(vgr.getCreator());
		vgrBean.setDescription(vgr.getDescription());
		vgrBean.setDestination(vgr.getDestination());
		vgrBean.setStartDate(vgr.getStartDate());
		vgrBean.setEndDate(vgr.getEndDate());
		vgrBean.setHotelInfo(hotelBean);
		vgrBean.setTravelName(vgr.getTravelName());
		vgrBean.setNumMaxUt(String.valueOf(vgr.getNumMaxUt()));
		vgrBean.setIdTravel(String.valueOf(vgr.getIdTravel()));
		
		return vgrBean;
	}
	
}
