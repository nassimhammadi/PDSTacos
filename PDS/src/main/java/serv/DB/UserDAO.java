/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv.DB;

import serv.model.*;

/**
 *
 * @author nassimhammadi
 */
public interface UserDAO {

    /**
     * Insert a user into the database
     * @param id the user id
     * @throws DAOException
    */
    void insert( User u) throws DAOException;
    
    /**
     * Find a user into the database
     * @param id The user id
     * @throws DAOException
     */
    User find( int id ) throws DAOException;
    
    /**
     * Update a user into the database
     * @param id The user id
     * @throws DAOException
     */
    void update ( User u ) throws DAOException;
    
    /**
     * Delete a user from the database
     * @param id The user id
     * @throws DAOException
     */
    void delete ( int id ) throws DAOException;
}


