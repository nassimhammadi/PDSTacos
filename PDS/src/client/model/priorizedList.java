package client.model;

import java.sql.Date;
import java.util.ArrayList;
import serv.model.*;
public class priorizedList {
	private ArrayList<priorizedListObject> priorizedList;
	
	
	
	public priorizedList(ArrayList<priorizedListObject> al) {
		super();
		this.priorizedList = al;
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