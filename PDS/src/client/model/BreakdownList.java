package client.model;

import java.util.ArrayList;

public class BreakdownList {

	private ArrayList<Breakdown> listBreakdown= new ArrayList<Breakdown>();

	public BreakdownList(ArrayList<Breakdown> listBreakdown) {
		super();
		this.setListBreakdown(listBreakdown);
	}

	public ArrayList<Breakdown> getListBreakdown() {
		return listBreakdown;
	}

	public void setListBreakdown(ArrayList<Breakdown> listBreakdown) {
		this.listBreakdown = listBreakdown;
	}

	@Override
	public String toString() {
		return "BreakdownList [listBreakdown=" + listBreakdown + "]";
	}
	

}
