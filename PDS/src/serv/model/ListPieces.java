package serv.model;

import java.util.ArrayList;

public class ListPieces {
	private ArrayList<Pieces> listP;

	public ListPieces(ArrayList<Pieces> listP) {
		super();
		this.listP = listP;
	}

	public ArrayList<Pieces> getListP() {
		return listP;
	}

	public void setListP(ArrayList<Pieces> listP) {
		this.listP = listP;
	}

	@Override
	public String toString() {
		return "ListPieces [listP=" + listP + "]";
	}
	
	
}
