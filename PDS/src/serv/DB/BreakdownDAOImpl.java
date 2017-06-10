package serv.DB;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.model.log_breakdown;
import serv.model.Bike;
import serv.model.Breakdown;
import serv.model.BreakdownList;

import static javax.management.Query.and;

public class BreakdownDAOImpl implements BreakdownDAO {

	private Connection connection;
	private Statement ordre;


	/**
	 * Class contructor
	 * @param daoFactory an instance of DAOFactory
	 */
	public BreakdownDAOImpl(Connection c) {
		this.connection = c;
	}

	/**
	 * Find a Breakdown into the database
	 * @param id The Breakdown's id
	 */
	public Breakdown find( int id ) throws DAOException {

		Breakdown b = null;
		try {
			ordre = connection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		String sql = "select * from BREAKDOWNS where ID_Breakdown = "+id;
		
		try {
			ResultSet rs = ordre.executeQuery(sql);
			while(rs.next()){
				int id_breakdown = rs.getInt(1);
				int id_piece = rs.getInt(2) ;
				String name_breakdown = rs.getString(3);
				int type_breakdown = rs.getInt(4);
				int priority = rs.getInt(5);
				int duration = rs.getInt(6);
				String justification = rs.getString(7);
				b = new Breakdown(id_breakdown, id_piece, name_breakdown, type_breakdown, priority, duration,justification);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}


		try {
			ordre.close();
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return b;

	}


	public BreakdownList findAll() throws DAOException {

		Breakdown b;
		BreakdownList list = null;
		ArrayList<Breakdown> a_b = new ArrayList<Breakdown>();
		try {
			ordre = connection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		String sql = "select * from BREAKDOWNS";

		try {
			ResultSet rs = ordre.executeQuery(sql);
			while(rs.next()){
				int id_breakdown = rs.getInt(1);
				int id_piece = rs.getInt(2) ;
				String name_breakdown = rs.getString(3);
				int type_breakdown = rs.getInt(4);
				int priority = rs.getInt(5);
				int duration = rs.getInt(6);
				String justification = rs.getString(7);
				b = new Breakdown(id_breakdown, id_piece, name_breakdown, type_breakdown, priority, duration, justification);
				a_b.add(b);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}


		try {
			ordre.close();
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		list = new BreakdownList(a_b);
		return list;

	}



	public void insert(log_breakdown lb) throws DAOException {
		String comment=lb.getComment();
		int id_breakdown = lb.getId_breakdown();
		int id_employee=lb.getId_employee();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		int id_bike = lb.getId_bike();
		String sql="";
		if (id_bike==0){
			int id_car= lb.getId_car();
			sql = "INSERT INTO LOGS_BREAKDOWNS (ID_CAR,ID_EMPLOYEE,ID_BREAKDOWN,DATE_ENTRY,COMMENT) VALUES("+id_car+","+id_employee+","+id_breakdown+",'"+date+"','"+comment+"')";

		}
		else {
			sql = "INSERT INTO LOGS_BREAKDOWNS (ID_BIKE,ID_EMPLOYEE,ID_BREAKDOWN,DATE_ENTRY,COMMENT) VALUES("+id_bike+","+id_employee+","+id_breakdown+",'"+date+"','"+comment+"')";

			
		}

		try {
			ordre = connection.createStatement();

		} catch (SQLException ex) {
			Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	
		try {
			ordre.executeUpdate(sql);

		} catch (SQLException ex) {
			Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			ordre.close();
		} catch (SQLException ex) {
			Logger.getLogger(VehiculeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public BreakdownList findAll(int id){
		BreakdownList list = null;
        ArrayList<Breakdown> bd = new ArrayList<Breakdown>();
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from BREAKDOWNS bd join LOGS_BREAKDOWNS lb on bd.ID_BREAKDOWN=lb.ID_BREAKDOWN where lb.ID_CAR="+id+" and lb.DATE_OCCURED IS NULL";
      
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
            
            Breakdown p = new Breakdown(identifiant,id_piece,name_bd,type,prio,duration,justification);
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
        list = new BreakdownList(bd);
        System.out.println("Liste :"+list);
        return list;
	}




}
