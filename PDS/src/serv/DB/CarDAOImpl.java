/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv.DB;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import serv.model.Car;
import serv.model.ListCar;

import static javax.management.Query.and;


/**
 * Class which represent a implementation of CarDAO
 * @author hammadin hollardl
 */
public class CarDAOImpl implements CarDAO {
    /* Impl�mentation de la m�thode trouver() d�finie dans l'interface UtilisateurDao */
	/**
	 * Instance of DAOFactory
	 */
    
    /**
     * Instance of Connection to the database
     */
    private Connection connection;
    private Statement ordre;
    /**
     * Class contructor
     * @param daoFactory an instance of DAOFactory
     */
    public CarDAOImpl(Connection c) {
        this.connection = c;
    }
    
    
    public int calculDuration(String license){
    	Car c=findByLicense(license);
        try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
         String sql ="SELECT SUM(DURATION) FROM LOGS_BREAKDOWNS, BREAKDOWNS, CAR "
         		+ "WHERE LICENSE_NUMBER='"+license+"' AND CAR.ID_CAR=LOGS_BREAKDOWNS.ID_CAR "
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
    
    
    
public Car findByLicense( String license ) throws DAOException {
        
        Car c = null;
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Car where LICENSE_NUMBER = '"+license+"'";
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            String year = rs.getString(3);
            Boolean is_electric = rs.getBoolean(4);
            Boolean is_present = rs.getBoolean(5);
            String brand = rs.getString(6);
            String model = rs.getString(7);
            Date dateEntry = rs.getDate(8);
            
            c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString());
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
        
    }
    /**
     * Find a CAR into the database
     * @param id The CAR's id
     */
    public Car find( int id ) throws DAOException {
        
        Car c = null;
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Car where ID_CAR = "+id;
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            String license = rs.getString(2) ;
            String year = rs.getString(3);
            Boolean is_electric = rs.getBoolean(4);
            Boolean is_present = rs.getBoolean(5);
            String brand = rs.getString(6);
            String model = rs.getString(7);
            Date dateEntry = rs.getDate(8);
            
            c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString());
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
        
    }

    
    @SuppressWarnings("null")
	
    
    
    /**
     * Insert a CAR into the database
     * @param id The CAR's id
     */
    public void insert(Car c) throws DAOException {
       
        String license = c.getLicense_number() ;
        String year = c.getYear();
        Boolean is_electric = c.getIs_electric();
        Boolean is_present = c.getIs_present();
        String brand = c.getBrand();
        String model = c.getModel();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        try {
             ordre = connection.createStatement();
             
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO CAR (LICENSE_NUMBER,YEAR_VEHICLE,IS_ELECTRIC,IS_PRESENT,BRAND,MODEL,DATE_ENTRY) VALUES('"+license+"',"+year+","+is_electric+","+is_present+",'"+brand+"','"+model+"','"+date+"')";
       
        try {
            ordre.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    /**
     * Update a CAR into the database
     * @param id The CAR's id
     */
    public void update(Car c) throws DAOException {
        int id = c.getId();
        String license = c.getLicense_number() ;
        String year = c.getYear();
       
        Boolean is_electric = c.getIs_electric();
        Boolean is_present = c.getIs_present();
        String brand = c.getBrand();
        String model = c.getModel();
        
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE CAR SET LICENSE_NUMBER= '"+license+"',YEAR_VEHICLE="+year+",IS_ELECTRIC="+is_electric+", IS_PRESENT ="+is_present+", BRAND='"+brand+"', MODEL='"+model+"' where ID_CAR = "+id; 
      
        try {
            ordre.executeUpdate(sql);
          
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Delete a CAR from the database
     * @param id The CAR's id
     */
    public void delete(int id) throws DAOException {
        
        
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "DELETE from CAR where ID_CAR = "+id;
      
        try {
            ordre.executeUpdate(sql);
          
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

	public ListCar findAll(  ) throws DAOException {
        
	       
        ListCar list = null;
        ArrayList<Car> a_c = new ArrayList<Car>();
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Car";
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
               int identifiant = rs.getInt(1);
               String license = rs.getString(2) ;
               String year = rs.getString(3);
               Boolean is_electric = rs.getBoolean(4);
               Boolean is_present = rs.getBoolean(5);
               String brand = rs.getString(6);
               String model = rs.getString(7);
               Date dateEntry = rs.getDate(8);
               int duration=calculDuration(license);
               Car c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString(), duration);
               a_c.add(c);
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new ListCar(a_c);
        return list;
        
    }
	
	public ListCar findAllOccured(  ) throws DAOException {
        
	       
        ListCar list = null;
        ArrayList<Car> a_c = new ArrayList<Car>();
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Car where id_car = ( select id_car from logs_breakdowns where isnull(date_occured))";
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
               int identifiant = rs.getInt(1);
               String license = rs.getString(2) ;
               String year = rs.getString(3);
               Boolean is_electric = rs.getBoolean(4);
               Boolean is_present = rs.getBoolean(5);
               String brand = rs.getString(6);
               String model = rs.getString(7);
               Date dateEntry = rs.getDate(8);
               int duration=calculDuration(license);
               Car c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString(), duration);
               a_c.add(c);
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new ListCar(a_c);
        return list;
        
    }
	
	public ListCar findAllFinished(  ) throws DAOException {
        
	       
        ListCar list = null;
        ArrayList<Car> a_c = new ArrayList<Car>();
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Car c join logs_breakdowns lb on c.id_car = lb.id_car where lb.DATE_REPARED IS NOT NULL GROUP BY c.id_car";
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
               int identifiant = rs.getInt(1);
               String license = rs.getString(2) ;
               String year = rs.getString(3);
               Boolean is_electric = rs.getBoolean(4);
               Boolean is_present = rs.getBoolean(5);
               String brand = rs.getString(6);
               String model = rs.getString(7);
               Date dateEntry = rs.getDate(8);
               int duration=calculDuration(license);
               Car c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString(), duration);
               a_c.add(c);
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new ListCar(a_c);
        return list;
        
    }
public Car findAndDeletePrio( int id, int id_prio ) throws DAOException {
        
        Car c = null;
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Car where ID_CAR = "+id;
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            String license = rs.getString(2) ;
            String year = rs.getString(3);
            Boolean is_electric = rs.getBoolean(4);
            Boolean is_present = rs.getBoolean(5);
            String brand = rs.getString(6);
            String model = rs.getString(7);
            Date dateEntry = rs.getDate(8);
            
            c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString());
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       String sql2 = "DELETE from priorizedlist where ID_PL = "+id_prio;
     
       try {
         ordre.executeUpdate(sql2);
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       try {
           ordre.close();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return c;
        
    }
    
    
    
}

