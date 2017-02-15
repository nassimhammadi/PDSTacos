/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.serv.DB;


import main.java.serv.model.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.and;
import static javax.management.Query.and;


/**
 * Class which represent a implementation of VehiculeDAO
 * @author hammadin hollardl
 */
public class VehiculeDAOImpl implements VehiculeDAO {
    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
	/**
	 * Instance of DAOFactory
	 */
    private final ConnectionPool connectionPool;
    
    /**
     * Instance of Connection to the database
     */
    private Connection connection;
    private Statement ordre;
    /**
     * Class contructor
     * @param daoFactory an instance of DAOFactory
     */
    public VehiculeDAOImpl(ConnectionPool connect) {
        this.connectionPool = connect;
        this.connection = connectionPool.getConnectionPool();
    }
    
    /**
     * Find a vehicle into the database
     * @param id The vehicle's id
     */
    public Vehicule find( int id ) throws DAOException {
        
        Vehicule v = null;
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from vehicles where id = "+id;
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            String license = rs.getString(2) ;
            Timestamp year = rs.getTimestamp(3);
            int type = rs.getInt(4);
            Boolean is_electric = rs.getBoolean(5);
            Boolean is_present = rs.getBoolean(6);
            String brand = rs.getString(7);
            String model = rs.getString(8);
            
            v = new Vehicule(identifiant, license, year, type, is_electric, is_present, brand, model);
           }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
        
    }
    
    
    /**
     * Insert a vehicle into the database
     * @param id The vehicle's id
     */
    public void insert(Vehicule v) throws DAOException {
        int identifiant = v.getId();
        String license = v.getLicense_number() ;
        Timestamp year = v.getYear();
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
        String sql = "INSERT INTO vehicles  VALUES("+identifiant+","+license+","+year+","+type+","+is_electric+","+is_present+","+brand+","+brand+")";
       
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
    public void update(Vehicule v) throws DAOException {
        int id = v.getId();
        //String license = v.getLicense_number() ;
        //Timestamp year = v.getYear();
        //int type = v.getType();
        //Boolean is_electric = v.getIs_electric();
        Boolean is_present = v.getIs_present();
        //String brand = v.getBrand();
        //String model = v.getModel();
        
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE vehicles SET   is_present ="+is_present+" where id = "+id; 
      
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
        String sql = "DELETE from vehicles where id = "+id;
      
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

