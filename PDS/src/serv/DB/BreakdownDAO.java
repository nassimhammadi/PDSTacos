package serv.DB;

import serv.model.priorizedList;
import serv.model.Breakdown;
import serv.model.BreakdownList;
import serv.model.ListBreakdowns;

public interface BreakdownDAO {
    Breakdown find( int id ) throws DAOException;
    
    BreakdownList findAll(int id) throws DAOException;
    
    BreakdownList findAll() throws DAOException;
    
}
