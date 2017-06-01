package serv.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import serv.model.Bike;
import serv.model.Car;
import serv.model.ListBike;
import serv.model.ListCar;

public class BikeDAOImpl {
   
   /**
    * Instance of Connection to the database
    */
   private Connection connection;
   private Statement ordre;
   /**
    * Class contructor
    * @param daoFactory an instance of DAOFactory
    */
   public BikeDAOImpl(Connection c) {
       this.connection = c;
   }
   
   
   public int calculDuration(int id){
	    try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        String sql ="SELECT SUM(DURATION) FROM LOGS_BREAKDOWNS, BREAKDOWNS, BIKE "
        		+ "WHERE ID_BIKE="+id+" AND BIKE.ID_BIKE=LOGS_BREAKDOWNS.ID_BIKE "
        		+ "AND LOGS_BREAKDOWNS.ID_BREAKDOWN=BREAKDOWNS.ID_BREAKDOWN";
        int sum=0;
        try {
            ResultSet rs = ordre.executeQuery(sql);
            while(rs.next()){
              sum = rs.getInt(1);
            }
         } catch (SQLException ex) {
             Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
		return sum;
   	
   }
   
   
   
   /**
    * Find a BIKE into the database
    * @param id The BIKE's id
    */
   public Bike find( int id ) throws DAOException {
       
       Bike b = null;
       try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(BikeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       String sql = "select * from Bike where ID_Bike = "+id;
     
       try {
          ResultSet rs = ordre.executeQuery(sql);
          while(rs.next()){
           int identifiant = rs.getInt(1);
           String year = rs.getString(2);
           Boolean is_electric = rs.getBoolean(3);
           Boolean is_present = rs.getBoolean(4);
           String brand = rs.getString(5);
           String model = rs.getString(6);
           Date dateEntry = rs.getDate(7);
           
           b = new Bike(identifiant, year, is_electric, is_present, brand, model,dateEntry.toString());
          }
       } catch (SQLException ex) {
           Logger.getLogger(BikeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       try {
           ordre.close();
       } catch (SQLException ex) {
           Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return b;
       
   }

   
   @SuppressWarnings("null")
	
   
   
   /**
    * Insert a BIKE into the database
    * @param id The BIKE's id
    */
   public void insert(Bike b) throws DAOException {
      
       String year = b.getYear();
       Boolean is_electric = b.getIs_electric();
       Boolean is_present = b.getIs_present();
       String brand = b.getBrand();
       String model = b.getModel();
       java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
       
       try {
            ordre = connection.createStatement();
            
       } catch (SQLException ex) {
           Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       String sql = "INSERT INTO Bike (YEAR_VEHICLE,IS_ELECTRIC,IS_PRESENT,BRAND,MODEL,DATE_ENTRY) VALUES("+year+","+is_electric+","+is_present+",'"+brand+"','"+model+"','"+date+"')";
      
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
   
   /**
    * Update a BIKE into the database
    * @param id The BIKE's id
    */
   public void update(Bike b) throws DAOException {
       int id = b.getId();
       String year = b.getYear();
      
       Boolean is_electric = b.getIs_electric();
       Boolean is_present = b.getIs_present();
       String brand = b.getBrand();
       String model = b.getModel();
       
       try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       String sql = "UPDATE Bike SET YEAR_VEHICLE="+year+",IS_ELECTRIC="+is_electric+", IS_PRESENT ="+is_present+", BRAND='"+brand+"', MODEL='"+model+"' where ID_BIKE = "+id; 
     
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

   /**
    * Delete a BIKE from the database
    * @param id The BIKE's id
    */
   public void delete(int id) throws DAOException {
       
       
       try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       String sql = "DELETE from Bike where ID_BIKE = "+id;
     
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
   
   
   public ListBike findAll(  ) throws DAOException {
       
       
       ListBike list = null;
       ArrayList<Bike> a_c = new ArrayList<Bike>();
       try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(BikeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       String sql = "select * from Bike";
       
       try {
          ResultSet rs = ordre.executeQuery(sql);
          while(rs.next()){
              int identifiant = rs.getInt(1);
              String year = rs.getString(2);
              Boolean is_electric = rs.getBoolean(3);
              Boolean is_present = rs.getBoolean(4);
              String brand = rs.getString(5);
              String model = rs.getString(6);
              Date dateEntry = rs.getDate(7);
              int duration = calculDuration(identifiant);
              Bike b = new Bike(identifiant, year, is_electric, is_present, brand, model,dateEntry.toString(),duration);
              a_c.add(b);
          }
       } catch (SQLException ex) {
           Logger.getLogger(BikeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       try {
           ordre.close();
       } catch (SQLException ex) {
           Logger.getLogger(BikeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       list = new ListBike(a_c);
       return list;
       
   }
   
   
   
}
