/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.serv.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Class which represent a DAOFactory
 * @author hammadin hollardl
 */
public class DAOFactory {
   
	/**
	 * The location of the dao.properties file
	 */
    private static final String FICHIER_PROPERTIES       = "C:/Users/nassi/Documents/DLP/workspace/Tacos2/src/com/tacos/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    /**
     * The database url
     */
    private String              url;
    
    /**
     * The username (login) to access the database
     */
    private String              username;
    
    /**
     * The password to access the database
     */
    private String              password;

    /**
     * Class constructor
     * @param url The url of the database
     * @param username The login to access the database
     * @param password the password to access the database
     */
    public DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Return an instance of DAOFactory after getting the database's connection informations and after charging the JDBC driver
     * @return An instance of DAOFactory
     * @throws DAOConfigurationException
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

       /* ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }
*/
           // properties.load( fichierProperties );
            url = "jdbc:mysql://localhost:8889/tacos";
            driver = "com.mysql.jdbc.Driver";
            nomUtilisateur = "root";
            motDePasse = "root";

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données */
     /* package */ Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO (un seul
     * pour le moment)
     */
     
     /**
      * Return an instance of VehiculeDAO
      * @return A VehiculeDAO instance
      */
    public VehiculeDAO getUtilisateurDao() {
        return new VehiculeDAOImpl( this );
    }
}

