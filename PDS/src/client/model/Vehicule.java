/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;
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
    private String license_number ;
    /**
     * The vehicle's entry date into the database
     */
    private String year;
    private int type;
   
    private Boolean is_electric;
    private Boolean is_present;
    private String brand;
    private String model;

    /**
     * Blank constructor
     */
    public Vehicule(){}
    
    /**
     * 
     * @param identifiant
     * @param license
     * @param typeV
     * @param yearV
     * @param electric
     * @param present
     * @param brandV
     * @param modelV
     * Constructor
     */
    public Vehicule(int identifiant, String license , int typeV, String yearV, Boolean electric, Boolean present, String brandV, String modelV){
        this.id= identifiant;
        this.license_number = license;
        this.type = typeV;
        this.year = yearV;
        this.is_electric = electric;
        this.is_present = present;
        this.brand = brandV;
        this.model = modelV;
    }
    
    /**
     * 
     * @param license
     * @param typeV
     * @param yearV
     * @param electric
     * @param present
     * @param brandV
     * @param modelV
     * Constructor without primary key
     */
    public Vehicule(String license , int typeV, String yearV, Boolean electric, Boolean present, String brandV, String modelV){
        
        this.license_number = license;
        this.type = typeV;
        this.year = yearV;
        this.is_electric = electric;
        this.is_present = present;
        this.brand = brandV;
        this.model = modelV;
    }
    
public Vehicule( int typeV, String yearV, Boolean electric, Boolean present, String brandV, String modelV){
        
       
        this.type = typeV;
        this.year = yearV;
        this.is_electric = electric;
        this.is_present = present;
        this.brand = brandV;
        this.model = modelV;
    }
    
    
    /**
     * 
     * @return
     * Return id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     * Change id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     * Return license number
     */
    public String getLicense_number() {
        return license_number;
    }

    /**
     * 
     * @param license_number
     * Change license number
     */
    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    /**
     * 
     * @return
     * Return year
     */
    public String getYear() {
        return year;
    }

    /**
     * 
     * @param year
     * Change year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * 
     * @return type 
     */
    public int getType() {
        return type;
    }

    /**
     * 
     * @param type
     * Change type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 
     * @return the state of the vehicle (if it's electric or not)
     */
    public Boolean getIs_electric() {
        return is_electric;
    }

    /**
     * 
     * @param is_electric
     * Change state
     */
    public void setIs_electric(Boolean is_electric) {
        this.is_electric = is_electric;
    }

    /**
     * 
     * @return if the vehicle is present
     */
    public Boolean getIs_present() {
        return is_present;
    }

    /**
     * 
     * @param is_present
     * Change it 
     */
    public void setIs_present(Boolean is_present) {
        this.is_present = is_present;
    }

    /**
     * 
     * @return the brand 
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 
     * @param brand
     * Change the brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 
     * @return
     * Return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     * Change the model
     */
    public void setModel(String model) {
        this.model = model;
    }

	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", license_number=" + license_number + ", type=" + type + ", year=" + year
				+ ", is_electric=" + is_electric + ", is_present=" + is_present + ", brand=" + brand + ", model="
				+ model + "]";
	}

    /**
     * Class constructor
     */
   
    /**
     * Class constructor
     * @param identifiant The vehicle's id
     * @param prov The vehicle's origin
     * @param dateE The vehicle's entry date into the database
     */
   
}