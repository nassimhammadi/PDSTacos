/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv.DB;

import java.util.ArrayList;

import serv.model.*;
/**
 * Interface VehiculeDAO
 * @author hammadin hollardl
 */
public interface VehiculeDAO {
	
	/**
	 * Insert a vehicle into the database
	 * @param id the vehicle id
	 * @throws DAOException
	 */
    void insert( Vehicule v) throws DAOException;
    
    /**
     * Find a vehicle into the database
     * @param id The vehicle id
     * @throws DAOException
     */
    Vehicule find( int id ) throws DAOException;
    
    ListVehicle findAll() throws DAOException;
    
    /**
     * Update a vehicle into the database
     * @param id The vehicle id
     * @throws DAOException
     */
    void update ( Vehicule v ) throws DAOException;
    
    /**
     * Delete a vehicle from the database
     * @param id The vehicle id
     * @throws DAOException
     */
    void delete ( int id ) throws DAOException;
}
