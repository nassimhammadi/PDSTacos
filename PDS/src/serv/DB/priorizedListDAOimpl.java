package serv.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import serv.model.ListVehicle;
import serv.model.Vehicule;
import serv.model.priorizedList;
import serv.model.priorizedListObject;

public class priorizedListDAOimpl implements priorizedListDAO {

	 private Connection connection;
	    private Statement ordre;
	    /**
	     * Class contructor
	     * @param daoFactory an instance of DAOFactory
	     */
	 public priorizedListDAOimpl(Connection c) {
	        this.connection = c;
	 }
	@Override
	public void insert(priorizedListObject prio) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public priorizedListObject find(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public priorizedList findAll() throws DAOException {
		priorizedList list = null;
        ArrayList<priorizedListObject> a_p = new ArrayList<priorizedListObject>();
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(priorizedListDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from priorizedList";
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            int id_car = rs.getInt(2);
            int id_bike = rs.getInt(3);
            String model = rs.getString(4);
            int prio = rs.getInt(5);
            Date entry = rs.getDate(6);
            
            priorizedListObject p = new priorizedListObject(identifiant, id_car,id_bike,model,prio,entry);
            a_p.add(p);
           }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new priorizedList(a_p);
        return list;
        
    }
    
    
    /**
     * Insert a vehicle into the database
     * @param id The vehicle's id
     */
    public void insert(Vehicule v) throws DAOException {
       
        String license = v.getLicense_number() ;
        String year = v.getYear();
        int type = v.getType();
        Boolean is_electric = v.getIs_electric();
        Boolean is_present = v.getIs_present();
        String brand = v.getBrand();
        String model = v.getModel();
        
        try {
             ordre = connection.createStatement();
             
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO VEHICLES (LICENSE_NUMBER,TYPE_VEHICLE,YEAR_VEHICLE,IS_ELECTRIC,IS_PRESENT,BRAND,MODEL) VALUES('"+license+"',"+type+","+year+","+is_electric+","+is_present+",'"+brand+"','"+model+"')";
       
        try {
            ordre.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@Override
	public void update(priorizedListObject v) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	/** 
	 *
	 * @author Hatim
	 */
	
public void  sort_List(ArrayList<priorizedListObject> priorizedList) { // PRIORIZED LIST SORT
		
	
    // SORT BY PRIORITY
	
	int longueur=0;
	for (priorizedListObject o : priorizedList){
		longueur++;
	}
		
	
       boolean inversion=true;    
       do
           {
           inversion=false;

           for(int i=0;i<longueur-1;i++)
               {
               if( priorizedList.get(i).getId_prio() > priorizedList.get(i+1).getId_prio())
                   {
               	Collections.swap(priorizedList, i, i+1);
                   inversion=true;
                   }
               }
           longueur--;
            }
       while(inversion);
       
       // SORT BY DATE IF PRIORITY IS THE SAME
       
       longueur=0;
   	for (priorizedListObject o : priorizedList){
   		longueur++;
   	}
         
       do
           {
           inversion=false;

           for(int i=0;i<longueur-1;i++)
               {
               if( priorizedList.get(i).getId_prio() == priorizedList.get(i+1).getId_prio() && priorizedList.get(i).getDate_occured().compareTo(priorizedList.get(i+1).getDate_occured()) > 0  )
                   {
               	Collections.swap(priorizedList, i, i+1);
                   inversion=true;
                   }
               }
           longueur--;
            }
       while(inversion);
       
       
       // PUT ELEMENT ON TOP IF DATE OLDER THAN 3 WEEKS
       
       longueur=0;
   	for (priorizedListObject o : priorizedList){
   		longueur++;
   	}
       
       for(int i=0;i<longueur-1;i++){
    	java.util.Date date = new java.util.Date();
       	int noOfDays = 21; //i.e two weeks
       	Calendar calendar = Calendar.getInstance();
       	calendar.setTime(date);            
       	calendar.add(Calendar.DAY_OF_YEAR, -noOfDays);
       	Date dateSystem =  (Date) calendar.getTime();
       	if( priorizedList.get(i).getDate_occured().compareTo(dateSystem) < 0  ){
       		
       	}
       }
       
	}

}
