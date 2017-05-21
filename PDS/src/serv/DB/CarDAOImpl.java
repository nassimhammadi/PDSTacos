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

import static javax.management.Query.and;


/**
 * Class which represent a implementation of VehiculeDAO
 * @author hammadin hollardl
 */
public class CarDAOImpl implements CarDAO {
    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
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
    
    /**
     * Find a vehicle into the database
     * @param id The vehicle's id
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
            
            c = new Car(identifiant, license, year, is_electric, is_present, brand, model,dateEntry);
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
        
    }

    
    @SuppressWarnings("null")
	
    
    
    /**
     * Insert a vehicle into the database
     * @param id The vehicle's id
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
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO CAR (LICENSE_NUMBER,YEAR_VEHICLE,IS_ELECTRIC,IS_PRESENT,BRAND,MODEL,DATE_ENTRY) VALUES('"+license+"',"+year+","+is_electric+","+is_present+",'"+brand+"','"+model+"','"+date+"')";
       
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
     * Update a vehicle into the database
     * @param id The vehicle's id
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
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE CAR SET LICENSE_NUMBER= '"+license+"',YEAR_VEHICLE="+year+",IS_ELECTRIC="+is_electric+", IS_PRESENT ="+is_present+", BRAND='"+brand+"', MODEL='"+model+"' where ID_VEHICLE = "+id; 
      
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
     * Delete a vehicle from the database
     * @param id The vehicle's id
     */
    public void delete(int id) throws DAOException {
        
        
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "DELETE from CAR where ID_VEHICLE = "+id;
      
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
    
    
}

