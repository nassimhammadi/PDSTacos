
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


	private AllClasses classe;
	private TypeRequest type;
	private String objectJson="";
	private Vehicule v;
	private LinkedHashMap<Parameter,String> listParam = new LinkedHashMap<Parameter,String>();
	private Connection co;
/*
 * M�thode permettant de traduire et d'executer la requ�te du client
 */
	public String evalRequest() throws IOException{
		String reponse="";
		
		switch (classe){
		case VEHICULE : 
			VehiculeDAOImpl vdao= new VehiculeDAOImpl(co);
			switch (type){
			case SELECT: 
				
				switch (listParam.size()){
				case 0 : //return Vehicule.getAllVehicule();
					break;
				case 1 :
					if (listParam.containsKey(Parameter.ID)) {
						this.v = vdao.find(Integer.parseInt(listParam.get(Parameter.ID)));
						Json<Vehicule> jV = new Json<Vehicule>(Vehicule.class);
						String jsonVehicle = jV.serialize(this.v);
						return reponse = "select/"+jsonVehicle;
						
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
						return reponse = "delete";
				default :
					break;
					
				}
				/*
				 * UPDATE PREND 1 param :  le  vehicule mis � jour
				 */
			case UPDATE:	
				
				switch (listParam.size()){
				case 0 : //RIEN
					break;
				case 2 : 
					Vehicule v_update = vdao.find(Integer.parseInt(listParam.get(Parameter.ID)));
					System.out.println(v_update);
					Boolean bool =Boolean.valueOf(listParam.get(Parameter.PRESENCE));
					Vehicule v_update2 = new Vehicule(v_update.getId(),v_update.getLicense_number(),v_update.getYear(),v_update.getType(),v_update.getIs_electric(),bool,v_update.getBrand(),v_update.getModel());
					System.out.println(v_update2);
					vdao.update(v_update2);
					return reponse = "update";
					


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
					return reponse = "insert";
				default :
					break;

				}
		


			}
		case EMPLOYEE :
			UserDAOImpl udao= new UserDAOImpl(co);
			switch(type){
			case LOGIN:
				switch (listParam.size()){
				case 2 :
					// Checkout login
					
					Boolean checkU = udao.checkUserdb(listParam.get(Parameter.NAME), listParam.get(Parameter.PWD));
					if (checkU){
						System.out.println("connection ok");
						reponse="connection ok";
					}
					// On rend la connexion
					else {
						System.out.println("connection ko");
						reponse="connection ko";
						Serveur.CP.ConnectionToPool(co);
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

	public requestToServer(AllClasses classe, TypeRequest type, String objectJson,
			LinkedHashMap<Parameter, String> listParam) {
		this.classe = classe;
		this.type = type;
		this.objectJson = objectJson;
		this.listParam = listParam;
	}

	public Connection getCo() {
		return co;
	}

	public void setCo(Connection co) {
		this.co = co;
	}
	
	
}
