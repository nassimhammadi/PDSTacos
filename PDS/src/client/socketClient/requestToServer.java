
package client.socketClient;
import serv.DB.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedHashMap;

import serv.DB.ConnectionPool;
import serv.DB.VehiculeDAOImpl;
import serv.json.Json;
import serv.model.Vehicule;
import serv.socketServer.Serveur;

public class requestToServer {


	private String classe;
	private TypeRequest type;
	private String objectJson="";
	LinkedHashMap<Parameter,String> listParam = new LinkedHashMap<Parameter,String>();

/*
 * M�thode permettant de traduire et d'executer la requ�te du client
 */
	public String evalRequest() throws IOException{
		String reponse="";
		
		switch (classe){
		case "vehicle" : 
			VehiculeDAOImpl vdao= new VehiculeDAOImpl(Client.CO);
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
				 * UPDATE PREND 1 param :  le  vehicule mis � jour
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
			UserDAOImpl udao= new UserDAOImpl(Client.CO);
			switch(type){
			case LOGIN:
				switch (listParam.size()){
				case 2 :
					// Checkout login
					System.out.println(listParam.get(Parameter.NAME) + " " + listParam.get(Parameter.PWD) );
					Boolean checkU = udao.checkUserdb(listParam.get(Parameter.NAME), listParam.get(Parameter.PWD));
					if (checkU){
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
