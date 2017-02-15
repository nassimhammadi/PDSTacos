package main.java.client.json;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.*;


public class Voiture {

	private int annee;
	private int kilometrage;
	private String constructeur;
	private String modele;
	private String immatriculation;
	private boolean fonctionne;
	private ArrayList <Composant> pieceAchanger;
	private ArrayList <Composant> pieceAReparer;
	
	
	
	public Voiture(){}
	public Voiture(int annee, int kilometrage, String constructeur,
			String modele, String immatriculation, boolean fonctionne,
			ArrayList<Composant> pieceAchanger, ArrayList<Composant> pieceAReparer) {
		super();
		this.annee = annee;
		this.kilometrage = kilometrage;
		this.constructeur = constructeur;
		this.modele = modele;
		this.immatriculation = immatriculation;
		this.fonctionne = fonctionne;
		this.pieceAchanger = pieceAchanger;
		this.pieceAReparer = pieceAReparer;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Voiture [annee=" + annee + ", kilometrage=" + kilometrage
				+ ", constructeur=" + constructeur + ", modele=" + modele
				+ ", immatriculation=" + immatriculation + ", fonctionne="
				+ fonctionne + ", pieceAchanger=" + pieceAchanger
				+ ", pieceAReparer=" + pieceAReparer + "]";
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getKilometrage() {
		return kilometrage;
	}
	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}
	public String getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public boolean isFonctionne() {
		return fonctionne;
	}
	public void setFonctionne(boolean fonctionne) {
		this.fonctionne = fonctionne;
	}
	public ArrayList<Composant> getPieceAchanger() {
		return pieceAchanger;
	}
	public void setPieceAchanger(ArrayList<Composant> pieceAchanger) {
		this.pieceAchanger = pieceAchanger;
	}
	public ArrayList<Composant> getPieceAReparer() {
		return pieceAReparer;
	}
	public void setPieceAReparer(ArrayList<Composant> pieceAReparer) {
		this.pieceAReparer = pieceAReparer;
	}
	
	
	
	
	
}
