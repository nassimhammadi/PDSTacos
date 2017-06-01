package serv.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import serv.model.Breakdowns;
import serv.model.ListBreakdowns;
import serv.model.priorizedList;
import serv.model.priorizedListObject;

public class BreakdownsDAOImpl implements BreakdownsDAO {

	 private Connection connection;
	    private Statement ordre;
	    /**
	     * Class contructor
	     * @param daoFactory an instance of DAOFactory
	     */
	 public BreakdownsDAOImpl(Connection c) {
	        this.connection = c;
	 }
	@Override
	public void insert(Breakdowns bd) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Breakdowns find(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListBreakdowns findAllCar(int id) throws DAOException {
		ListBreakdowns list = null;
        ArrayList<Breakdowns> bd = new ArrayList<Breakdowns>();
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BreakdownsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from BREAKDOWNS bd join LOGS_BREAKDOWNS lb on bd.ID_BREAKDOWN=lb.ID_BREAKDOWN where lb.ID_CAR="+id+"";
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
            int identifiant = rs.getInt(1);
            int id_piece = rs.getInt(2);
            String name_bd = rs.getString(3);
            int type = rs.getInt(4);
            int prio = rs.getInt(5);
            int duration = rs.getInt(6);
            String justification = rs.getString(7);
            
            Breakdowns p = new Breakdowns(identifiant,id_piece,name_bd,type,prio,duration,justification);
            bd.add(p);
            System.out.println("P : "+ p);
           }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        list = new ListBreakdowns(bd);
        System.out.println("Liste :"+list);
        return list;
        
	}

	@Override
	public void update(Breakdowns bd) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
