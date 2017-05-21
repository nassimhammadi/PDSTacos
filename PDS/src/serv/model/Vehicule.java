/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv.model;
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
    
    private int type;
    private String year;
    private Boolean is_electric;
    private Boolean is_present;
    private String brand;
    private String model;

    public Vehicule(){}
    
    public Vehicule(int identifiant, String license , String yearV, int typeV, Boolean electric, Boolean present, String brandV, String modelV){
        this.id= identifiant;
        this.license_number = license;
        this.year = yearV;
        this.type = typeV;
        this.is_electric = electric;
        this.is_present = present;
        this.brand = brandV;
        this.model = modelV;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
	public String toString() {
		return "Vehicule [id=" + id + ", license_number=" + license_number + ", year=" + year + ", type=" + type
				+ ", is_electric=" + is_electric + ", is_present=" + is_present + ", brand=" + brand + ", model="
				+ model + "]";
	}

	public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getIs_electric() {
        return is_electric;
    }

    public void setIs_electric(Boolean is_electric) {
        this.is_electric = is_electric;
    }

    public Boolean getIs_present() {
        return is_present;
    }

    public void setIs_present(Boolean is_present) {
        this.is_present = is_present;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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