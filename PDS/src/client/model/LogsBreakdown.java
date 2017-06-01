package client.model;

import java.sql.Date;

public class LogsBreakdown {
	private int id_bd_log;
	private int id_car;
	private int id_bike;
	private int id_employee;
	private int id_bd;
	private Date date_e;
	private Date date_o;
	private Date date_r;
	private String comment;
	public LogsBreakdown(int id_bd_log, int id_car, int id_employee, int id_bd, Date date_e, Date date_o, Date date_r,
			String comment) {
		super();
		this.id_bd_log = id_bd_log;
		this.id_car = id_car;
		this.id_bike = 0;
		this.id_employee = id_employee;
		this.id_bd = id_bd;
		this.date_e = date_e;
		this.date_o = date_o;
		this.date_r = date_r;
		this.comment = comment;
	}
	public LogsBreakdown(int id_bd_log, int id_car, int id_bike, int id_employee, int id_bd, Date date_e, Date date_o, Date date_r,
			String comment) {
		super();
		this.id_bd_log = id_bd_log;
		this.id_bike = id_bike;
		this.id_car =0;
		this.id_employee = id_employee;
		this.id_bd = id_bd;
		this.date_e = date_e;
		this.date_o = date_o;
		this.date_r = date_r;
		this.comment = comment;
	}
	public int getId_bd_log() {
		return id_bd_log;
	}
	public void setId_bd_log(int id_bd_log) {
		this.id_bd_log = id_bd_log;
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
	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	public int getId_bd() {
		return id_bd;
	}
	public void setId_bd(int id_bd) {
		this.id_bd = id_bd;
	}
	public Date getDate_e() {
		return date_e;
	}
	public void setDate_e(Date date_e) {
		this.date_e = date_e;
	}
	public Date getDate_o() {
		return date_o;
	}
	public void setDate_o(Date date_o) {
		this.date_o = date_o;
	}
	public Date getDate_r() {
		return date_r;
	}
	public void setDate_r(Date date_r) {
		this.date_r = date_r;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "LogsBreakdown [id_bd_log=" + id_bd_log + ", id_car=" + id_car + ", id_bike=" + id_bike
				+ ", id_employee=" + id_employee + ", id_bd=" + id_bd + ", date_e=" + date_e + ", date_o=" + date_o
				+ ", date_r=" + date_r + ", comment=" + comment + "]";
	}
	
	
	
}

