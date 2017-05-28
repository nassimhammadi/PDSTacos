package client.model;

import java.util.ArrayList;

import client.model.Bike;

public class ListBike {
private ArrayList<Bike> a_b;

public ListBike(ArrayList<Bike> a_b) {
	super();
	this.a_b = a_b;
}

public ArrayList<Bike> getA_b() {
	return a_b;
}

public void setA_b(ArrayList<Bike> a_b) {
	this.a_b = a_b;
}
}
