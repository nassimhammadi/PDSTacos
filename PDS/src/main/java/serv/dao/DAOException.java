/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.serv.dao;
/**
 * Class which represent a DAOException
 * @author hammadin hollardl
 */
public class DAOException extends RuntimeException {
	
	/**
	 * Class constructor
	 * @param message The exception message
	 */
    public DAOException( String message ) {
        super( message );
    }

    /**
     * Class constructor
     * @param message The exception message
     * @param cause The exception cause
     */
    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     * Class constructor
     * @param cause The exception cause
     */
    public DAOException( Throwable cause ) {
        super( cause );
    }
}   

