/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv.DB;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import serv.model.Car;
import serv.model.ListCar;
import serv.model.ListPieces;
import serv.model.LogsBreakdown;
import serv.model.Pieces;

import static javax.management.Query.and;


/**
 * Class which represent a implementation of CarDAO
 * @author hammadin hollardl
 */
public class LogsBreakdownDAOImpl implements LogsBreakdownDAO {
    /* Impl�mentation de la m�thode trouver() d�finie dans l'interface UtilisateurDao */
	/**
	 * Instance of DAOFactory
	 */
    
    /**
     * Instance of Connection to the database
     */
    private Connection connection;
    private Statement ordre;
    /**
     * Class contructor
     * @param daoFactory an instance of DAOFactory
     */
    public LogsBreakdownDAOImpl(Connection c) {
        this.connection = c;
    }
    
    
    
    

    public LogsBreakdown findCar( int id, int id_bd ) throws DAOException {
        
    	LogsBreakdown c = null;
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from LOGS_BREAKDOWNS where ID_CAR = "+id+" and id_breakdown= "+id_bd;
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
        	int id_bd_log = rs.getInt(1);
        	int id_car = rs.getInt(2);
            int id_bike = 0;
            int id_employee = rs.getInt(4);
            int id_b = rs.getInt(5);
            Date date_e = rs.getDate(6);
        	Date date_o = rs.getDate(7);
            Date date_r = rs.getDate(8);
            String comment = rs.getString(9);
            
            c = new LogsBreakdown(id_bd_log, id_car, id_bike, id_employee, id_b, date_e, date_o, date_r, comment);
            
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
        
    }

public LogsBreakdown findBike( int id, int id_bd ) throws DAOException {
        
    	LogsBreakdown c = null;
        try {
             ordre = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from LOGS_BREAKDOWNS where ID_BIKE = "+id+" and id_breakdown= "+id_bd;
      
        try {
           ResultSet rs = ordre.executeQuery(sql);
           while(rs.next()){
        	int id_bd_log = rs.getInt(1);
        	int id_car = 0;
            int id_bike = rs.getInt(3);
            int id_employee = rs.getInt(4);
            int id_b = rs.getInt(5);
            Date date_e = rs.getDate(6);
        	Date date_o = rs.getDate(7);
            Date date_r = rs.getDate(8);
            String comment = rs.getString(9);
            
            c = new LogsBreakdown(id_bd_log, id_car, id_bike, id_employee, id_b, date_e, date_o, date_r, comment);
            
           }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ordre.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
        
    }





	@Override
	public void insert() throws DAOException {
		// TODO Auto-generated method stub
		
	}





	





	public void update(int id, int id_bd, String comment, ListPieces lp) throws DAOException {
	   try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       String sql = "UPDATE LOGS_BREAKDOWNS SET ID_EMPLOYEE ="+id+", COMMENT = '"+comment+"', DATE_OCCURED= CURDATE() WHERE ID_BREAKDOWN_LOG="+id_bd;
     
       try {
          ordre.executeUpdate(sql);
          
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       try {
           ordre.close();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       for(Pieces piece: lp.getListP()){
    	   try {
               ordre = connection.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
          String sql2 = "UPDATE PIECES SET STOCK = STOCK-1 WHERE ID_PIECE="+piece.getId_piece();
        
          try {
             ordre.executeUpdate(sql2);
             
          } catch (SQLException ex) {
              Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          try {
              ordre.close();
          } catch (SQLException ex) {
              Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
       }
      
       
		
	}


	public void updateFinish(int id, int id_v){
		 try {
	            ordre = connection.createStatement();
	       } catch (SQLException ex) {
	           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	       }
		 	String sql1 = "select count(*) from logs_breakdowns where id_car ="+id_v+" and isnull(DATE_OCCURED)";
		 	
		 	int not_finished=1;
		 	 
	        try {
	           ResultSet rs = ordre.executeQuery(sql1);
	           while(rs.next()){
	        	   not_finished = rs.getInt(1);
	           }
	        }
	        catch(SQLException ex) {
		           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		      }
		 
	       if(not_finished == 0){
		 
	       String sql = "UPDATE LOGS_BREAKDOWNS SET DATE_REPARED =CURDATE() WHERE ID_BREAKDOWN_LOG="+id;
	     
	       try {
	          ordre.executeUpdate(sql);
	          
	       } catch (SQLException ex) {
	           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	       }
	       
	       
	       String sql3 = "DELETE from priorizedlist where ID_CAR = "+id_v;
	       try {
		          ordre.executeUpdate(sql3);
		          
		       } catch (SQLException ex) {
		           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		       }
	       
	       
	       }
	       else{
	    	   String sql = "UPDATE LOGS_BREAKDOWNS SET DATE_REPARED =CURDATE() WHERE ID_BREAKDOWN_LOG="+id;
	  	     
		       try {
		          ordre.executeUpdate(sql);
		          
		       } catch (SQLException ex) {
		           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		       }
		        
	       }
	       try {
	           ordre.close();
	       } catch (SQLException ex) {
	           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	       }
	}
	
	public void updateFinishB(int id, int id_v){
		try {
            ordre = connection.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
	 	String sql1 = "select count(*) from log_breakdowns where id_car ="+id_v+" and isnull(DATE_OCCURED)";
	 	
	 	int not_finished=1;
	 	 
        try {
           ResultSet rs = ordre.executeQuery(sql1);
           while(rs.next()){
        	   not_finished = rs.getInt(1);
           }
        }
        catch(SQLException ex) {
	           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	      }
	 
       if(not_finished == 0){
	 
       String sql = "UPDATE LOGS_BREAKDOWNS SET DATE_REPARED =CURDATE() WHERE ID_BREAKDOWN_LOG="+id;
     
       try {
          ordre.executeUpdate(sql);
          
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       String sql3 = "DELETE from priorizedlist where ID_BIKE = "+id_v;
       try {
	          ordre.executeUpdate(sql3);
	          
	       } catch (SQLException ex) {
	           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	       }
       
       
       }
       else{
    	   String sql = "UPDATE LOGS_BREAKDOWNS SET DATE_REPARED =CURDATE() WHERE ID_BREAKDOWN_LOG="+id;
  	     
	       try {
	          ordre.executeUpdate(sql);
	          
	       } catch (SQLException ex) {
	           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	       }
	        
       }
       try {
           ordre.close();
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
	}



	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

   
	
    
    
   
    
}

