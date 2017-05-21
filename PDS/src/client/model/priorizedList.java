package client.model;

import java.sql.Date;
import java.util.ArrayList;

public class priorizedList {
	private ArrayList<priorizedListObject> priorizedList;
	
	
	
	public priorizedList(ArrayList<priorizedListObject> priorizedList) {
		super();
		this.priorizedList = priorizedList;
	}


	public ArrayList<priorizedListObject> getPriorizedList() {
		return priorizedList;
	}



	public void setPriorizedList(ArrayList<priorizedListObject> priorizedList) {
		this.priorizedList = priorizedList;
	}
	
	@Override
	public String toString() {
		return "priorizedList [priorizedList=" + priorizedList + "]";
	}

}