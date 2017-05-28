package client.model;

import java.util.ArrayList;

import client.model.Car;

public class ListCar {

	private ArrayList<Car> l_b;

	public ArrayList<Car> getL_b() {
		return l_b;
	}

	public void setL_b(ArrayList<Car> l_b) {
		this.l_b = l_b;
	}

	public ListCar(ArrayList<Car> l_b) {
		super();
		this.l_b = l_b;
	}
}
