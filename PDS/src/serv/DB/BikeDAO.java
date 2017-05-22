package serv.DB;

import serv.model.Bike;

public interface BikeDAO {
		
		/**a
		 * Insert a Bike into the database
		 * @param id the Bike id
		 * @throws DAOException
		 */
	    void insert( Bike b) throws DAOException;
	    
	    /**
	     * Find a Bike into the database
	     * @param id The Bike id
	     * @throws DAOException
	     */
	    Bike find( int id ) throws DAOException;
	    
	    
	    
	    /**
	     * Update a Bike into the database
	     * @param id The Bike id
	     * @throws DAOException
	     */
	    void update ( Bike b ) throws DAOException;
	    
	    /**
	     * Delete a vehicle from the database
	     * @param id The vehicle id
	     * @throws DAOException
	     */
	    void delete ( int id ) throws DAOException;
	

}
