package serv.model;

import java.util.ArrayList;

public class PerformanceList {
	

	private ArrayList<Performance> listPerf= new ArrayList<Performance>();

	public PerformanceList(ArrayList<Performance> listPerf) {
		super();
		this.setListPerf(listPerf);
	}

	public ArrayList<Performance> getListPerf() {
		return listPerf;
	}

	public void setListPerf(ArrayList<Performance> listPerf) {
		this.listPerf = listPerf;
	}

	


}
