package logic.dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.exceptions.BookTravelException;
import logic.exceptions.DeleteTravelException;
import logic.exceptions.SaveTravelException;
import logic.exceptions.SystemException;
import logic.model.Hotel;
import logic.model.PrivateTravel;
import logic.queries.CRUDQueries;
import logic.queries.SimpleQueries;
import logic.util.DBConnector;

public class PrivateTravelDao {
	
	private static final String SYSTEM_ERROR = "System error!";
	private static final String DESTINAZIONE = "Destinazione";
	private static final String HOTEL_LINK = "HotelLink";
	private static final String NOME_VIAGGIO = "NomeViaggio";
	private static final String BREAKFAST = "Breakfast";
	private static final String CREATOR = "Creatore";
	private static final String DATA_END = "DataFineV";
	private static final String DATA_V = "DataV";
	private static final String DESCRIPTION = "Descrizione";
	private static final String HOTEL_NAME = "HotelName";
	private static final String NUM_MAX_UT = "NumTrav";
	private static final String NUM_ROOMS = "NumRooms";
	private static final String PRICE = "Price";
	private static final String STARS = "Stars";
	
	private PrivateTravelDao() {
		
	}
	
	public static void addTravelPhoto(int idViaggio, File file, String filename) throws SystemException {
        
    	Statement stmt = null;
    	    	
    	try {    	
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.addTravelPhoto(DBConnector.getDBConnectorInstance().getConnection(), idViaggio, file, filename);  
        } catch (SQLException e) {
			throw new SystemException(SYSTEM_ERROR);
		} finally {
        	try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
            	Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }    	    	
    }
	
	public static List<String> retrieveTravelPhoto(int idViaggio) throws SystemException {
        
    	Statement stmt = null;
    	ResultSet rs;
    	
    	List<String> filenames = new ArrayList<>();
    	
    	try {
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = SimpleQueries.retrieveTravelPhoto(stmt, idViaggio);
            
            if (!rs.first()) { // rs empty
            	return filenames;
            }
            
            rs.first();
            
            String filename = "";
            do {
            	filename = rs.getString("Filename");
            	filenames.add(filename);
            } while(rs.next());
            
            return filenames;
        } catch (SQLException e) {
			throw new SystemException(SYSTEM_ERROR);
		} finally {
        	try {
                if (stmt != null)
                    stmt.close();           
            } catch (SQLException se) {
            	Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }
    }
	
    public static void saveTravel(PrivateTravel v) throws SaveTravelException, SystemException  {
    	
    	Statement stmt = null;

    	try {    		
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.saveTravel(stmt, v);
   
        } catch (SQLException e) {
        	throw new SaveTravelException("Error in saving the travel... retry!");
		} finally {
        	try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
            	Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }   	
    }
    
    public static void bookTravel(int idV) throws BookTravelException, SystemException {
    	
    	Statement stmt = null;
    	
    	try {
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.bookTravel(stmt, idV);
    	} catch (SQLException se) {
    		throw new BookTravelException("Book travel error"); 
        } finally {
        	try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
            	Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }
    }
    
    public static void bookAndSaveTravel(PrivateTravel v) throws BookTravelException, SystemException {
    	
    	Statement stmt = null;

    	try {
    		   		
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.bookAndSaveTravel(stmt, v);
   
        } catch (SQLException e) {
        	e.printStackTrace();
        	throw new BookTravelException("Book travel error"); 
        } finally {
        	try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
            	Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }    	
    }
    
    public static void deleteTravel(int idV) throws DeleteTravelException, SystemException {
    	
    	Statement stmt = null;
               
        try {              	 
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            CRUDQueries.deleteTravel(stmt, idV);
        } catch (SQLException se) {
        	throw new DeleteTravelException("Delete travel error");
        } finally {
       	 	try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }
       
    }
    
    public static List<PrivateTravel> retrieveTravels(String username) throws SystemException {
    	
    	 Statement stmt = null;
         List<PrivateTravel> listOfTravells = new ArrayList<>();
                  
         try {
             // creazione ed esecuzione della query
             stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = SimpleQueries.selectAllTravelsDone(stmt, username);

             if (!rs.first()){ // rs empty
             	return listOfTravells;
             }
             
             // riposizionamento del cursore
             rs.first();
             do{
            	 Hotel hotel = new Hotel();                
            	 hotel.setBreakfast(rs.getString(BREAKFAST));
                 hotel.setHotelLink(rs.getString(HOTEL_LINK));
                 hotel.setHotelName(rs.getString(HOTEL_NAME));
                 hotel.setNumRooms(rs.getInt(NUM_ROOMS));
                 hotel.setPrice(rs.getString(PRICE));
                 hotel.setStars(rs.getInt(STARS));
                 
                 PrivateTravel vg = new PrivateTravel();
                 vg.setCreator(rs.getString(CREATOR));
                 vg.setDestination(rs.getString(DESTINAZIONE));
                 vg.setDescription(rs.getString(DESCRIPTION));
                 vg.setStartDate(rs.getString(DATA_V));
                 vg.setEndDate(rs.getString(DATA_END));
                 vg.setHotelInfo(hotel);
                 vg.setTravelName(rs.getString(NOME_VIAGGIO));
                 vg.setIdTravel(rs.getInt("idV"));
                 vg.setNumMaxUt(rs.getInt(NUM_MAX_UT));
                 
                 listOfTravells.add(vg);

             } while(rs.next());
             
             // STEP 5.1: Clean-up dell'ambiente
             rs.close();
             return listOfTravells;
         } catch (SQLException e) {
			throw new SystemException(SYSTEM_ERROR);
		} finally {
        	 try {
                 if (stmt != null)
                     stmt.close();
             } catch (SQLException se) {
                 Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	 logger.log(Level.WARNING, SYSTEM_ERROR);
             }
        }
    }
    
    public static List<PrivateTravel> retrieveMySavedTravels(String username) throws SystemException {
    	
   	 	Statement stmt = null;
        
        List<PrivateTravel> listOfTravells = new ArrayList<>();
                
        try {
       	 
        	DBConnector.getDBConnectorInstance().getConnection();
       	 
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = SimpleQueries.selectUpcomingTravels(stmt, username);

            if (!rs.first()){ // rs empty
            	return listOfTravells;
            }
            
            // riposizionamento del cursore
            rs.first();
            do {                
                Hotel hotel = new Hotel();
                hotel.setBreakfast(rs.getString(BREAKFAST));
                hotel.setHotelLink(rs.getString(HOTEL_LINK));
                hotel.setHotelName(rs.getString(HOTEL_NAME));
                hotel.setNumRooms(rs.getInt(NUM_ROOMS));
                hotel.setPrice(rs.getString(PRICE));
                hotel.setStars(rs.getInt(STARS));
                
                PrivateTravel vg = new PrivateTravel();
                vg.setCreator(rs.getString(CREATOR));
                vg.setDestination(rs.getString(DESTINAZIONE));
                vg.setDescription(rs.getString(DESCRIPTION));
                vg.setStartDate(rs.getString(DATA_V));
                vg.setEndDate(rs.getString(DATA_END));
                vg.setHotelInfo(hotel);
                vg.setTravelName(rs.getString(NOME_VIAGGIO));
                vg.setIdTravel(rs.getInt("idV"));
                // NUMERO DI VIAGGIATORI???? 
                
                listOfTravells.add(vg);

            } while(rs.next());
            
            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
            return listOfTravells;
        } catch (SQLException e) {
			throw new SystemException(SYSTEM_ERROR);
		} finally {
       	 	try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }
    }
    
    public static List<PrivateTravel> retrieveNextBookedTravels(String username) throws SystemException {
    	
   	 	Statement stmt = null;
        
        List<PrivateTravel> listOfTravells = new ArrayList<>();
        
        try {
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = SimpleQueries.selectUpcomingBookedTravels(stmt, username);

            if (!rs.first()){ // rs empty
            	return listOfTravells;
            }
            
            // riposizionamento del cursore
            rs.first();
            do {                
                Hotel hotel = new Hotel();
                hotel.setBreakfast(rs.getString(BREAKFAST));
                hotel.setHotelLink(rs.getString(HOTEL_LINK));
                hotel.setHotelName(rs.getString(HOTEL_NAME));
                hotel.setNumRooms(rs.getInt(NUM_ROOMS));
                hotel.setPrice(rs.getString(PRICE));
                hotel.setStars(rs.getInt(STARS));
                
                PrivateTravel vg = new PrivateTravel();
                vg.setCreator(rs.getString(CREATOR));
                vg.setDestination(rs.getString(DESTINAZIONE));
                vg.setDescription(rs.getString(DESCRIPTION));
                vg.setStartDate(rs.getString(DATA_V));
                vg.setEndDate(rs.getString(DATA_END));
                vg.setHotelInfo(hotel);
                vg.setTravelName(rs.getString(NOME_VIAGGIO));
                vg.setIdTravel(rs.getInt("idV"));
                vg.setNumMaxUt(rs.getInt(NUM_MAX_UT));
                
                listOfTravells.add(vg);

            } while(rs.next());
            
            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
            return listOfTravells;
         } catch (SQLException e) {
			throw new SystemException(SYSTEM_ERROR);
		 } finally {
       	 try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }
    }
    
    public static List<PrivateTravel> retrieveFollowerTravelsPrivate(String usrn) throws SystemException {
		
		Statement stmt = null;
        List<PrivateTravel> travels = new ArrayList<>();
        
        try {      	 
            // creazione ed esecuzione della query
            stmt = DBConnector.getDBConnectorInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = SimpleQueries.selectAllTravelsDone(stmt, usrn);
            
            if (!rs.first()){ // rs empty
            	return travels;
            }
            
            // riposizionamento del cursore
            rs.first();
            
            do {
                
                Hotel hotel = new Hotel();
                hotel.setHotelLink(rs.getString(HOTEL_LINK));
                
                PrivateTravel vg = new PrivateTravel();
                vg.setHotelInfo(hotel);
                vg.setIdTravel(rs.getInt("idV"));
                vg.setDestination(rs.getString(DESTINAZIONE));
                vg.setTravelName(rs.getString(NOME_VIAGGIO));
                
                travels.add(vg);

            } while(rs.next());
            
            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
            return travels;
        } catch (SQLException e) {
			throw new SystemException(SYSTEM_ERROR);
		} finally {
       	 	try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                Logger logger = Logger.getLogger(PrivateTravelDao.class.getName());
            	logger.log(Level.WARNING, SYSTEM_ERROR);
            }
        }
	}
    
}
