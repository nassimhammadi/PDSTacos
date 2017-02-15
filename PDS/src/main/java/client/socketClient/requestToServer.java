
package main.java.client.socketClient;

import java.io.IOException;
import java.util.LinkedHashMap;

import main.java.serv.dao.DAOFactory;
import main.java.serv.dao.Vehicule;
import main.java.serv.dao.VehiculeDAOImpl;
import main.java.serv.json.Json;

public class requestToServer {

	DAOFactory dao=new DAOFactory("jdbc:mysql://localhost:8889/tacos", "root", "root");
	private String classe;
	private TypeRequest type;
	private String objectJson="";
	LinkedHashMap<Parameter,String> listParam = new LinkedHashMap<Parameter,String>();

/*
 * Méthode permettant de traduire et d'executer la requête du client
 */
	public String evalRequest() throws IOException{
		String reponse="";
		
		switch (classe){
		case "vehicle" : 
			VehiculeDAOImpl vdao= new VehiculeDAOImpl(dao);
			switch (type){
			case SELECT : 
				switch (listParam.size()){
				case 0 : //return Vehicule.getAllVehicule();
					break;
				case 1 :
					if (listParam.containsKey(Parameter.ID)) {
						vdao.find(Integer.parseInt(listParam.get(Parameter.ID)));
					}
					else if (listParam.containsKey(Parameter.IMMAT)) {
						//	 vdao.findByImmat(Integer.parseInt(listParam.get(Parameter.IMMAT)));
					}
					break;
				}
				
			case DELETE : 
				switch (listParam.size()){
				case 1 :
						vdao.delete(Integer.parseInt(listParam.get(Parameter.ID)));
						break;
				default :
					break;
					
				}
				/*
				 * UPDATE PREND 1 param :  le  vehicule mis à jour
				 */
			case UPDATE :	
				switch (listParam.size()){
				case 0 : //RIEN
					break;
				case 1 : 	
					Json<Vehicule> myJSon= new Json<Vehicule>(Vehicule.class);
					Vehicule v= myJSon.deSerialize(objectJson);
					vdao.update(v);
					break;


				default :
					break;

				}

				/*
				 * UPDATE PREND 1 param :  le nouveau vehicule
				 */
			case INSERT:
				switch (listParam.size()){
				case 0 : //RIEN
					break;
				case 1 : 	
					Json<Vehicule> myJSon= new Json<Vehicule>(Vehicule.class);
					Vehicule v= myJSon.deSerialize(objectJson);
					vdao.insert(v);;
					break;
				default :
					break;

				}
		


			}
		case "employee" :
			switch(type){
			case LOGIN:
				switch (listParam.size()){
				case 2 :
					// Checkout login
					if (listParam.get(Parameter.NAME).equals("bonlogin") && listParam.get(Parameter.PWD).equals("bonpwd")){
						System.out.println("connection ok");
						reponse="connection ok";
					}
					else {
						System.out.println("connection ko");
						reponse="connection ko";
					}
					break;
				default :
					break;

				}
			}
			
			break;
		default :
			break;
		}
		
		return reponse;
	}

	public requestToServer(String classe, TypeRequest type, String objectJson,
			LinkedHashMap<Parameter, String> listParam) {
		this.classe = classe;
		this.type = type;
		this.objectJson = objectJson;
		this.listParam = listParam;
	}}
