package serv.model;

import java.util.ArrayList;

public class LogsBreakdownList {
	private ArrayList<LogsBreakdown> a_bd;

	public LogsBreakdownList(ArrayList<LogsBreakdown> a_bd) {
		super();
		this.a_bd = a_bd;
	}

	public ArrayList<LogsBreakdown> getA_bd() {
		return a_bd;
	}

	public void setA_bd(ArrayList<LogsBreakdown> a_bd) {
		this.a_bd = a_bd;
	}

	@Override
	public String toString() {
		return "LogsBreakdownList [a_bd=" + a_bd + "]";
	}
	
	
}
