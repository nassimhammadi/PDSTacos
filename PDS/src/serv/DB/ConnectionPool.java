/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nassimhammadi
 */
public class ConnectionPool {
	
	/**
	 * the pool of connections
	 */
	private final ArrayList<Connection> pool = new ArrayList<Connection>();
	
	/**
	 * The database url
	 */
	private final String url = "jdbc:mysql://localhost:3306/CSC";
	
	/**
	 * JDBC driver
	 */
	private final String driver = "com.mysql.jdbc.Driver";
	
	/**
	 * The username (login) to access the database
	 */
	private final String user = "root";
	
	/**
	 * The password to access the database
	 */
	private final String password = "root";
	
	/**
	 * Class constructor
	 * @param nbConnection The number of connections in the pool
	 */
	public ConnectionPool() {
		int nbConnection=20;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0 ; i < nbConnection ; i++) {
			try {
				Connection co = DriverManager.getConnection(url, user, password);
                                
				pool.add(co);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Return an available connection from the pool
	 * @return an available connection
	 */
	public synchronized Connection getConnectionPool() {
                System.out.println(pool.size()-1);
		if(pool.isEmpty()) {
			System.out.println("There are no connection available");
			return null;
		}
		return pool.remove(pool.size()-1);
	}
	
	/**
	 * Put the connection back to the pool
	 * @param co the connection which go back to the pool
	 */
	public synchronized void ConnectionToPool(Connection co) {
		pool.add(co);
		System.out.println(pool.size());
	}
       
	/**
	 * Close all connections to prevent to waste resources
	 */
	public void closeAllConnection(){
		for (int i=0; i < pool.size(); i++){
			try {
				pool.get(i).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pool.remove(i);
		}
		System.out.println("Connections are well closed !");
	}
       
}


