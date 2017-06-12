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
import serv.model.LogsBreakdownList;
import serv.model.Performance;
import serv.model.PerformanceList;
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

public PerformanceList countRep(String vehicletype, int id_ope, int id_emp, Date dateBegin, Date dateEnd, String periode) throws DAOException {
    
	PerformanceList list= null;
	ArrayList<Performance> nbrep= new ArrayList<Performance>();
	Performance p= null;
	
    try {
         ordre = connection.createStatement();
    } catch (SQLException ex) {
        Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    String sql = null;
    
    switch(vehicletype){
    	case "Indifferent":
    		if (id_ope==0 && id_emp==0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+"'";
    		}
    		else if (id_ope==0 && id_emp!=0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    					"' and ID_EMPLOYEE="+id_emp;
    		}
    		else if (id_ope!=0 && id_emp==0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		    		"' and ID_BREAKDOWN="+id_ope;
    		}
    		else
    		{
    		 sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		"' and ID_BREAKDOWN="+id_ope+" and ID_EMPLOYEE="+id_emp;
    		}
    		
    		 break;
    		 
    	case "Voiture":
    		if (id_ope==0 && id_emp==0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		    	"' and ID_CAR is not null";
    		}
    		else if (id_ope==0 && id_emp!=0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    					"' and ID_EMPLOYEE="+id_emp+" and ID_CAR is not null";
    		}
    		else if (id_ope!=0 && id_emp==0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		    		"' and ID_BREAKDOWN="+id_ope+" and ID_CAR is not null";
    		}
    		
    		else
    		{
    		 sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		"' and ID_BREAKDOWN="+id_ope+" and ID_EMPLOYEE="+id_emp+" and ID_CAR is not null";
    		}
    		
    		break;
    		 
    		 
    	case "Velo":
    		
    		if (id_ope==0 && id_emp==0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		    	"' and ID_BIKE is not null";
    		}
    		else if (id_ope==0 && id_emp!=0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    					"' and ID_EMPLOYEE="+id_emp+" and ID_BIKE is not null";
    		}
    		else if (id_ope!=0 && id_emp==0)
    		{
    			sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		    		"' and ID_BREAKDOWN="+id_ope+" and ID_BIKE is not null";
    		}
    		
    		else
    		{
    		 sql = "select count(*),AVG(timestampdiff(second,DATE_OCCURED,Date_REPARED)) from LOGS_BREAKDOWNS where DATE_REPARED is not null and DATE_REPARED between '"+dateBegin+"' and '"+dateEnd+
    		"' and ID_BREAKDOWN="+id_ope+" and ID_EMPLOYEE="+id_emp+" and ID_BIKE is not null";
    		}
    		
    		break;
    }
    
   
    switch(periode){
    
    	case "Semaine":
    		sql = sql+" group by WEEKOFYEAR(DATE_REPARED) order by WEEKOFYEAR(DATE_REPARED)";
    		break;
    		
    	case "Mois":
    		sql = sql+" group by MONTH(DATE_REPARED) order by MONTH(DATE_REPARED)";
    		break;
    	case "Annee":
    		sql = sql+" group by YEAR(DATE_REPARED) order by YEAR(DATE_REPARED)";
    		break;
    }
    
    
    
    try {
       ResultSet rs = ordre.executeQuery(sql);
       while(rs.next()){
    	   int rep = rs.getInt(1);
    	   int duree = rs.getInt(2);
    	   p = new Performance(rep,duree,0);
    	   nbrep.add(p);
        
       }
    } catch (SQLException ex) {
        Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    try {
        ordre.close();
    } catch (SQLException ex) {
        Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    list= new PerformanceList(nbrep);
    return list;
    
}




	@Override
	public void insert() throws DAOException {
		// TODO Auto-generated method stub
		
	}




	 public LogsBreakdownList findCar( int id_car ) throws DAOException {
	        LogsBreakdownList bd_l = null;
	        ArrayList<LogsBreakdown> a_bd = new ArrayList<LogsBreakdown>();
	    	LogsBreakdown c = null;
	        try {
	             ordre = connection.createStatement();
	        } catch (SQLException ex) {
	            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        String sql = "select * from LOGS_BREAKDOWNS where ID_CAR = "+id_car;
	      
	        try {
	           ResultSet rs = ordre.executeQuery(sql);
	           while(rs.next()){
	        	int id_bd_log = rs.getInt(1);
	        	int id_c = rs.getInt(2);
	            int id_bike = 0;
	            int id_employee = rs.getInt(4);
	            int id_b = rs.getInt(5);
	            Date date_e = rs.getDate(6);
	        	Date date_o = rs.getDate(7);
	            Date date_r = rs.getDate(8);
	            String comment = rs.getString(9);
	            
	            c = new LogsBreakdown(id_bd_log, id_c, id_bike, id_employee, id_b, date_e, date_o, date_r, comment);
	            a_bd.add(c);
	           }
	        } catch (SQLException ex) {
	            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        
	        try {
	            ordre.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        bd_l = new LogsBreakdownList(a_bd);
	        return bd_l;
	        
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
		       
		       String clo = "UPDATE Car set location = null, is_present = false where id_car="+id_v;
		       try {
			          ordre.executeUpdate(clo);
			          
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
	       
	       String clo = "UPDATE Bike set location = null, is_present = false where id_bike="+id_v;
	       try {
		          ordre.executeUpdate(clo);
		          
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

