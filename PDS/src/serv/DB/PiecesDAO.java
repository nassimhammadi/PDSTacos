package serv.DB;

import serv.model.ListPieces;
import serv.model.Pieces;

public interface PiecesDAO {
		
		/**a
		 * Insert a Bike into the database
		 * @param id the Bike id
		 * @throws DAOException
		 */
	    void insert( Pieces b) throws DAOException;
	    
	    /**
	     * Find a Bike into the database
	     * @param id The Bike id
	     * @throws DAOException
	     */
	    Pieces find( int id ) throws DAOException;
	    
	    ListPieces findList(int id) throws DAOException;
	    
	    /**
	     * Update a Bike into the database
	     * @param id The Bike id
	     * @throws DAOException
	     */
	    void update ( Pieces b ) throws DAOException;
	    
	    /**
	     * Delete a vehicle from the database
	     * @param id The vehicle id
	     * @throws DAOException
	     */
	    void delete ( int id ) throws DAOException;
	

}
