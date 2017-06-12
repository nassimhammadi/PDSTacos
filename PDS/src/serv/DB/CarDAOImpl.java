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
    /* Implï¿½mentation de la mï¿½thode trouver() dï¿½finie dans l'interface UtilisateurDao */
	/**
	 * Instance of DAOFactory
	 */
    
    /**
     * Instance of Connection to the database
     */
    private Connection connection;
    private Statement ordre;
    private Statement ordre2;
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
         		+ "AND LOGS_BREAKDOWNS.ID_BREAKDOWN=BREAKDOWNS.ID_BREAKDOWN AND DATE_REPARED IS NULL";
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
    
    public int lookForAvailableLocation(){
    	int place=0;
    	
    	try {
            ordre = connection.createStatement();
            ordre2 = connection.createStatement();
             
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
      for (int i=1; i<50; i++){
       String sql = "SELECT * FROM CAR WHERE LOCATION="+i;
       
       
       String sql2 = "SELECT * FROM BIKE WHERE LOCATION="+i;
      
       try {
           ResultSet rs = ordre.executeQuery(sql);

           ResultSet rs2 = ordre2.executeQuery(sql2);
           // Si la requête sur car et sur bike n'a pas de résultat
           if (!rs.next() && !rs2.next()){
        	   place=i;
        	   return place;
           }
           
           
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }   
      }
      try {
          ordre.close();
          ordre2.close();
      } catch (SQLException ex) {
          Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    	return place;
    	
    }
    
    
    public void insert(Car c) throws DAOException {
       
        String license = c.getLicense_number() ;
        String year = c.getYear();
        Boolean is_electric = c.getIs_electric();
        Boolean is_present = c.getIs_present();
        String brand = c.getBrand();
        String model = c.getModel();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        int location=lookForAvailableLocation();
        if (location==0){
        	System.out.println("Le depot est plein");
        	return;
        }
        try {
             ordre = connection.createStatement();
             
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO CAR (LICENSE_NUMBER,YEAR_VEHICLE,IS_ELECTRIC,IS_PRESENT,BRAND,MODEL,DATE_ENTRY, LOCATION) VALUES('"+license+"',"+year+","+is_electric+","+is_present+",'"+brand+"','"+model+"','"+date+"','"+location+"')";
       
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
        
        int location=lookForAvailableLocation();
        if (location==0){
        	System.out.println("Le depot est plein");
        	return;
        }
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE CAR SET LICENSE_NUMBER= '"+license+"',YEAR_VEHICLE="+year+",IS_ELECTRIC="+is_electric+", IS_PRESENT ="+is_present+", BRAND='"+brand+"', MODEL='"+model+"', LOCATION='"+location+"' where ID_CAR = "+id; 
      
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
        String sql = "select * from car where id_car IN (SELECT id_car FROM logs_breakdowns where date_repared IS NULL AND id_bike IS NULL)";
            
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
               int location=rs.getInt(9);
               Car c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString(), duration, location);
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
        String sql = "select * from Car c join LOGS_BREAKDOWNS lb on c.id_car=lb.id_car where lb.DATE_OCCURED IS NOT NULL and isnull(lb.date_repared) group by c.ID_CAR";
      
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
        
	    ArrayList<Integer> a_i = new ArrayList<Integer>();
        ListCar list = null;
        ArrayList<Car> a_c = new ArrayList<Car>();
        int count1 = 0;
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql1 = "select Distinct id_car from logs_breakdowns   ";
        try {
            ResultSet rs = ordre.executeQuery(sql1);
            while(rs.next()){
                
                int d=rs.getInt(1);
                a_i.add(d);
            }
         } catch (SQLException ex) {
             Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        for(Integer i: a_i){
        	int inte = i;
        	String sql2 = "select COUNT(*) from logs_breakdowns  where id_car="+inte;
        	 try {
                 ResultSet rs4 = ordre.executeQuery(sql2);
                 while(rs4.next()){
                     int count2 = rs4.getInt(1);
                     String sql3 = "select COUNT(*) from logs_breakdowns where date_repared is not null and id_car="+inte;
                     try {
                         ResultSet rs3 = ordre.executeQuery(sql3);
                         while(rs3.next()){
                             
                             count1=rs3.getInt(1);
                             
                         }
                      } catch (SQLException ex) {
                          Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     if(count2==count1){
                    	 String sql = "select * from Car c where id_car ="+inte;
                    	 try {
                             ResultSet rs2 = ordre.executeQuery(sql);
                             while(rs2.next()){
                                 int identifiant = rs2.getInt(1);
                                 String license = rs2.getString(2) ;
                                 String year = rs2.getString(3);
                                 Boolean is_electric = rs2.getBoolean(4);
                                 Boolean is_present = rs2.getBoolean(5);
                                 String brand = rs2.getString(6);
                                 String model = rs2.getString(7);
                                 Date dateEntry = rs2.getDate(8);
                                 int duration=calculDuration(license);
                                 Car c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry.toString(), duration);
                                 a_c.add(c);
                             }
                          } catch (SQLException ex) {
                              Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                          }
                     }
                 }
              } catch (SQLException ex) {
                  Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
              }
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


public void updateByLicense(Car c)  {
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
    String sql = "UPDATE CAR SET YEAR_VEHICLE="+year+",IS_ELECTRIC="+is_electric+", IS_PRESENT ="+is_present+", BRAND='"+brand+"', MODEL='"+model+"' where LICENSE_NUMBER = "+license; 
  
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
    
    
    
}

