package main.java.serv.json;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class listeVoiture {
	private ArrayList<Voiture> listeV;
	


	@Override
	public String toString() {
		String res=new String();
		for (Voiture v: listeV){
			res+=v.toString();
			
		}
		return "listeVoiture : "+res;
	}


	public ArrayList<Voiture> getListeV() {
		return listeV;
	}


	public void setListeV(ArrayList<Voiture> listeV) {
		this.listeV = listeV;
	}
	
	
	
}
