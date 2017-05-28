package serv.DB;

import serv.model.priorizedList;
import serv.model.Breakdown;

public interface BreakdownDAO {
    Breakdown find( int id ) throws DAOException;
    
    priorizedList findAll() throws DAOException;

}
