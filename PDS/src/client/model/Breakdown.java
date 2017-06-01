package client.model;

public class Breakdown {

	private int id_breakdown;
	private int id_piece;
	private String name_breakdown;
	private int type_breakdown;
	private int priority;
	private int duration;
	private String justification;

	public Breakdown(int id_breakdown, int id_piece, String name_breakdown, int type_breakdown, int priority,
			int duration, String justication) {
		super();
		this.id_breakdown = id_breakdown;
		this.id_piece = id_piece;
		this.name_breakdown = name_breakdown;
		this.type_breakdown = type_breakdown;
		this.priority = priority;
		this.duration = duration;
		this.justification = justication;
	}

	public String toString(){
		return id_breakdown+". "+name_breakdown;
	}

	public int getId_breakdown() {
		return id_breakdown;
	}
	
	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public void setId_breakdown(int id_breakdown) {
		this.id_breakdown = id_breakdown;
	}

	public String getName_breakdown() {
		return name_breakdown;
	}

	public void setName_breakdown(String name_breakdown) {
		this.name_breakdown = name_breakdown;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getType_breakdown() {
		return type_breakdown;
	}

	public void setType_breakdown(int type_breakdown) {
		this.type_breakdown = type_breakdown;
	}

	public int getId_piece() {
		return id_piece;
	}

	public void setId_piece(int id_piece) {
		this.id_piece = id_piece;
	}
	
	
	
}
