
package client.model;

import java.util.ArrayList;

public class ListVehicle {
	
	private ArrayList<Vehicule> all_vehicle;

	public ListVehicle(ArrayList<Vehicule> all_v) {
		super();
		this.all_vehicle = all_v;
	}
	
	public ArrayList<Vehicule> getListv(){
		return this.all_vehicle;
	}
	
	
}

