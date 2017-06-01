package serv.model;

public class Breakdowns {
	private int id_breakdowns;
	private int id_piece;
	private String name_breakdown;
	private int type;
	private int priority;
	private int duration;
	private String justification;
	
	public Breakdowns(int id_breakdowns, int id_piece, String name_breakdown, int type, int priority, int duration,
			String justification) {
		super();
		this.id_breakdowns = id_breakdowns;
		this.id_piece = id_piece;
		this.name_breakdown = name_breakdown;
		this.type = type;
		this.priority = priority;
		this.duration = duration;
		this.justification = justification;
	}
	
	public Breakdowns(int id_breakdowns, int id_piece, String name_breakdown) {
		super();
		this.id_breakdowns = id_breakdowns;
		this.id_piece = id_piece;
		this.name_breakdown = name_breakdown;
		
	}

	public int getId_breakdowns() {
		return id_breakdowns;
	}

	public void setId_breakdowns(int id_breakdowns) {
		this.id_breakdowns = id_breakdowns;
	}

	public int getId_piece() {
		return id_piece;
	}

	public void setId_piece(int id_piece) {
		this.id_piece = id_piece;
	}

	public String getName_breakdown() {
		return name_breakdown;
	}

	public void setName_breakdown(String name_breakdown) {
		this.name_breakdown = name_breakdown;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	
	
}
