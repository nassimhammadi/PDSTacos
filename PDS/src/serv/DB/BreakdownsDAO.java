package serv.DB;

import serv.model.Breakdowns;
import serv.model.ListBreakdowns;
import serv.model.priorizedList;
import serv.model.priorizedListObject;

public interface BreakdownsDAO {
	
	void insert( Breakdowns bd) throws DAOException;
    
    /**
     * Find a vehicle into the database
     * @param id The vehicle id
     * @throws DAOException
     */
    Breakdowns find( int id ) throws DAOException;
    
    ListBreakdowns findAllCar(int id) throws DAOException;
    
    /**
     * Update a vehicle into the database
     * @param id The vehicle id
     * @throws DAOException
     */
    void update ( Breakdowns bd ) throws DAOException;
    
    /**
     * Delete a vehicle from the database
     * @param id The vehicle id
     * @throws DAOException
     */
    void delete ( int id ) throws DAOException;
}
