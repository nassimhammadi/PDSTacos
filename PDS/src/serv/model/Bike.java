package serv.model;

import java.sql.Timestamp;

public class Bike {
	/**
	 * The vehicle's id
	 */
    private int id;
 
    /**
     * The vehicle's entry date into the database
     */
 
    private String year;
    private Boolean is_electric;
    private Boolean is_present;
    private String brand;
    private String model;
    private String dateEntry;
    private int duration;

    /**
     * Blank constructor
     */
    public Bike(){}
    
    /**
     * 
     * @param identifiant
     * @param license
     * @param yearV
     * @param electric
     * @param present
     * @param brandV
     * @param modelV
     * Constructor
     */
    public Bike(int identifiant, String yearV, Boolean electric, Boolean present, String brandV, String modelV, String dateE){
        this.id= identifiant;
        this.year = yearV;
        this.is_electric = electric;
        this.is_present = present;
        this.brand = brandV;
        this.model = modelV;
        this.dateEntry = dateE.toString();
    }
    public Bike(int identifiant, String yearV, Boolean electric, Boolean present, String brandV, String modelV, String dateE, int duration){
        this.id= identifiant;
        this.year = yearV;
        this.is_electric = electric;
        this.is_present = present;
        this.brand = brandV;
        this.model = modelV;
        this.dateEntry = dateE.toString();
        this.duration=duration;
    }
    /**
     * 
     * @param license
     * @param yearV
     * @param electric
     * @param present
     * @param brandV
     * @param modelV
     * Constructor without primary key
     */
  
    
    public Bike(String yearV, Boolean electric, Boolean present, String brandV, String modelV, Timestamp dateE){
    
        this.year = yearV;
        this.is_electric = electric;
        this.is_present = present;
        this.brand = brandV;
        this.model = modelV;
        this.dateEntry = dateE.toString();
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
		return "Vehicule [id=" + id + ", year=" + year
				+ ", is_electric=" + is_electric + ", is_present=" + is_present + ", brand=" + brand + ", model="
				+ model + "dateEntry=" + dateEntry + "]";
	}

	public String getDateEntry() {
		return dateEntry;
	}

	public void setDateEntry(String dateEntry) {
		this.dateEntry = dateEntry;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
