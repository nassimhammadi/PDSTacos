package serv.DB;
import java.util.ArrayList;


import serv.model.*;

public interface priorizedListDAO {

	    void insert( priorizedListObject prio) throws DAOException;
	    
	    /**
	     * Find a vehicle into the database
	     * @param id The vehicle id
	     * @throws DAOException
	     */
	    priorizedListObject find( int id ) throws DAOException;
	    
	    
	    
	    priorizedList findAll() throws DAOException;
	    
	    /**
	     * Update a vehicle into the database
	     * @param id The vehicle id
	     * @throws DAOException
	     */
	    void update ( priorizedListObject v ) throws DAOException;
	    
	    /**
	     * Delete a vehicle from the database
	     * @param id The vehicle id
	     * @throws DAOException
	     */
	    void delete ( int id ) throws DAOException;
	}


