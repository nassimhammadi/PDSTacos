package client.model;

import java.sql.Date;

public class priorizedListObject {
	private int id_prio;
	private Date date_occured;
	private int id_vehicle;
	
	
	public priorizedListObject(int id_prio, Date date_occured, int id_vehicle) {
		super();
		this.id_prio = id_prio;
		this.date_occured = date_occured;
		this.id_vehicle = id_vehicle;
	}
	@Override
	public String toString() {
		return "priorizedList [id_prio=" + id_prio + ", date_occured=" + date_occured + ", id_vehicle=" + id_vehicle
				+ "]";
	}
	public int getId_prio() {
		return id_prio;
	}
	public void setId_prio(int id_prio) {
		this.id_prio = id_prio;
	}
	public Date getDate_occured() {
		return date_occured;
	}
	public void setDate_occured(Date date_occured) {
		this.date_occured = date_occured;
	}
	public int getId_vehicle() {
		return id_vehicle;
	}
	public void setId_vehicle(int id_vehicle) {
		this.id_vehicle = id_vehicle;
	}
}