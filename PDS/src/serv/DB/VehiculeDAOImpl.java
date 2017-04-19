/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv.DB;


import serv.model.ListVehicle;
import serv.model.Vehicule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /**
     * Instance of Connection to the database
     */
    private Connection connection;
    private Statement ordre;
    /**
     * Class contructor
     * @param daoFactory an instance of DAOFactory
     */
    public VehiculeDAOImpl(Connection c) {
        this.connection = c;
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
        String sql = "select * from VEHICLES where ID_VEHICLE = "+id;
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            String license = rs.getString(2) ;
            String year = rs.getString(3);
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

    
    @SuppressWarnings("null")
	public ListVehicle findAll(  ) throws DAOException {
        
       
        ListVehicle list = null;
        ArrayList<Vehicule> a_v = new ArrayList<Vehicule>();
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from VEHICLES";
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            String license = rs.getString(2) ;
            String year = rs.getString(3);
            int type = rs.getInt(4);
            Boolean is_electric = rs.getBoolean(5);
            Boolean is_present = rs.getBoolean(6);
            String brand = rs.getString(7);
            String model = rs.getString(8);
            
            Vehicule v = new Vehicule(identifiant, license, year, type, is_electric, is_present, brand, model);
            a_v.add(v);
           }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new ListVehicle(a_v);
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
        String sql = "INSERT INTO VEHICLES (LICENSE_NUMBER,TYPE_VEHICLE,YEAR_VEHICLE,IS_ELECTRIC,IS_PRESENT,BRAND,MODEL) VALUES('"+license+"',"+year+","+type+","+is_electric+","+is_present+",'"+brand+"','"+model+"')";
       
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
        String sql = "UPDATE VEHICLES SET LICENSE_NUMBER= '"+license+"', TYPE_VEHICLE="+type+",YEAR_VEHICLE="+year+",IS_ELECTRIC="+is_electric+", IS_PRESENT ="+is_present+", BRAND='"+brand+"', MODEL='"+model+"' where ID_VEHICLE = "+id; 
      
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
        String sql = "DELETE from VEHICLES where ID_VEHICLE = "+id;
      
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

