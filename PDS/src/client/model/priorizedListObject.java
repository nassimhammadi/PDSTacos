package client.model;

import java.sql.Date;

public class priorizedListObject {
	private int id_prio;
	private int id_car;
	private int id_bike;
	private String model;
	private int prio;
	private Date date_occured;
	
	
	
	
	public priorizedListObject(int id_prio, int id_car, int id_bike, String model, int prio, Date date_occured) {
		super();
		this.id_prio = id_prio;
		this.id_car = id_car;
		this.id_bike = id_bike;
		this.model = model;
		this.prio = prio;
		this.date_occured = date_occured;
	}
	public int getId_prio() {
		return id_prio;
	}
	public void setId_prio(int id_prio) {
		this.id_prio = id_prio;
	}
	public int getId_car() {
		return id_car;
	}
	public void setId_car(int id_car) {
		this.id_car = id_car;
	}
	public int getId_bike() {
		return id_bike;
	}
	public void setId_bike(int id_bike) {
		this.id_bike = id_bike;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrio() {
		return prio;
	}
	public void setPrio(int prio) {
		this.prio = prio;
	}
	public Date getDate_occured() {
		return date_occured;
	}
	public void setDate_occured(Date date_occured) {
		this.date_occured = date_occured;
	}
	
	
	
}