package client.model;

public class log_breakdown {
	private int id_log_breakdown=0;
	private int id_car=0;
	private int id_bike=0;
	private int id_employee=0;
	private int id_breakdown=0;
	private String date_entry;
	public int getId_log_breakdown() {
		return id_log_breakdown;
	}

	public void setId_log_breakdown(int id_log_breakdown) {
		this.id_log_breakdown = id_log_breakdown;
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

	public int getId_breakdown() {
		return id_breakdown;
	}

	public void setId_breakdown(int id_breakdown) {
		this.id_breakdown = id_breakdown;
	}

	public String getDate_entry() {
		return date_entry;
	}

	public void setDate_entry(String date_entry) {
		this.date_entry = date_entry;
	}

	public String getDate_repared() {
		return date_repared;
	}

	public void setDate_repared(String date_repared) {
		this.date_repared = date_repared;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private String date_repared;
	private String comment;
	
	public log_breakdown(int id_log_breakdown, int id_car, int id_bike, int id_employee, int id_breakdown,
			String date_entry, String date_repared, String comment) {
		super();
		this.id_log_breakdown = id_log_breakdown;
		this.id_car = id_car;
		this.id_bike = id_bike;
		this.id_employee = id_employee;
		this.id_breakdown = id_breakdown;
		this.date_entry = date_entry;
		this.date_repared = date_repared;
		this.comment = comment;
	}

	public log_breakdown(int id_car, int i, int id_employee2, int id_breakdown2, String motif_breakdown) {
		this.id_car=id_car;
		this.id_bike=i;
		this.id_employee=id_employee2;
		this.id_breakdown=id_breakdown2;
		this.comment=motif_breakdown;
	}
	
	
}
