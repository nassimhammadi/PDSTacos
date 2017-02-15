package main.java.serv.dao;
import java.sql.Timestamp;

/**
 * Class which represent a vehicle
 * @author hammadin hollardl
 */
public class Vehicule {
	
	/**
	 * The vehicle's id
	 */
    private int id;
    
    /**
     * The vehicle's origin
     */
    private String provenance ;
    
    /**
     * The vehicle's entry date into the database
     */
    private Timestamp dateEntree;

    /**
     * Class constructor
     */
    Vehicule(){
        
    }
    
    /**
     * Class constructor
     * @param identifiant The vehicle's id
     * @param prov The vehicle's origin
     * @param dateE The vehicle's entry date into the database
     */
    public Vehicule(int identifiant, String prov, Timestamp dateE){
        this.id= identifiant;
        this.provenance = prov;
        this.dateEntree = dateE;
    }
    
    /**
     * Return the id of the vehicle
     * @return The vehicle's id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Update the id of the vehicle
     * @param id The new id
     */
    public void setId( int id ) {
        this.id = id;
    }

    /**
     * Update the origin of the vehicle
     * @param prov The new origin
     */
    public void setProvenance( String prov ) {
        this.provenance = prov;
    }
    
    /**
     * Return the origin of the vehicle
     * @return The vehicle's origin
     */
    public String getNom() {
        return this.provenance;
    }

    /**
     * Return the date when the vehicle entered into the database
     * @return the vehicle's entry date into the database
     */
    public Timestamp getDateEntree() {
        return dateEntree;
    }
    
    /**
     * Update the entry date into database of the vehicle
     * @param dateEnt the new date
     */
    public void setDateEntree( Timestamp dateEnt ) {
        this.dateEntree = dateEnt;
    }

}