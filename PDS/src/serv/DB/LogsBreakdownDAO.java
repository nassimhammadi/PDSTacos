package serv.DB;

import serv.model.ListPieces;
import serv.model.LogsBreakdown;

public interface LogsBreakdownDAO {
		
		/**a
		 * Insert a Bike into the database
		 * @param id the Bike id
		 * @throws DAOException
		 */
	    void insert( ) throws DAOException;
	    
	    /**
	     * Find a Bike into the database
	     * @param id The Bike id
	     * @throws DAOException
	     */
	    LogsBreakdown findCar( int id_car, int id_bd ) throws DAOException;
	    
	    
	    
	    /**
	     * Update a Bike into the database
	     * @param id The Bike id
	     * @throws DAOException
	     */
	    void update ( int id, int id_bd, String comment, ListPieces lp ) throws DAOException;
	    
	    void updateFinish ( int id) throws DAOException;
	    
	    /**
	     * Delete a vehicle from the database
	     * @param id The vehicle id
	     * @throws DAOException
	     */
	    void delete ( int id ) throws DAOException;
	

}
