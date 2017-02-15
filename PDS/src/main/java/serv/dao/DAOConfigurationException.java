/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.serv.dao;

/**
 * Class which represent a DAOConfigurationException
 * @author hammadin hollardl
 */
public class DAOConfigurationException extends RuntimeException {
	
	/**
	 * Class constructor
	 * @param message The exception message
	 */
    public DAOConfigurationException( String message ) {
        super( message );
    }

    /**
     * Class constructor
     * @param message The exception message
     * @param cause The exception cause
     */
    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     * Class constructor
     * @param cause The exception cause
     */
    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}

