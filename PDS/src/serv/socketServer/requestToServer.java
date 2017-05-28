
package serv.socketServer;
import serv.DB.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.model.log_breakdown;
import client.socketClient.Parameter;
import serv.model.Car;
import serv.model.ListBike;
import serv.model.ListCar;
import serv.model.Bike;
import serv.model.ListVehicle;
import serv.model.Breakdown;
import serv.model.BreakdownList;
import serv.DB.ConnectionPool;
import serv.DB.VehiculeDAOImpl;
import serv.json.Json;
import serv.model.Vehicule;
import serv.model.priorizedList;
import serv.socketServer.Serveur;



/***
 * 	
 * @author lazaredantz
 * Represent a request sent from client to server, 
 * in order to handle every kind of possible request
 */

public class requestToServer {


	private AllClasses classe;
	private TypeRequest type;
	private String objectJson="";
	private Vehicule v;
	private LinkedHashMap<Parameter,String> listParam = new LinkedHashMap<Parameter,String>();
	private Connection co;
	private ListVehicle listV;
	private priorizedList pList;
	private BreakdownList bList;
	private Car car;
	private Breakdown breakdown;
	private Bike Bike;
	private ListCar listC;
	private ListBike listB;
	/*
	 * M?thode permettant de traduire et d'executer la requ?te du client
	 */
	public String evalRequest() throws IOException{
		String reponse="";

		switch (classe){
		case BREAKDOWN :
			BreakdownDAOImpl bdao = new BreakdownDAOImpl(co);
			switch (type){
			case SELECT:
				this.bList = bdao.findAll();
				Json<BreakdownList> jV = new Json<BreakdownList>(BreakdownList.class);
				String jsonListBreak = jV.serialize(bList);
				return reponse = "selectAllBreakDown/"+jsonListBreak;
			
			
				
			}
			
			


			break;



		case REPAIR :
			priorizedListDAOimpl ldao = new priorizedListDAOimpl(co);
			switch (type){
			case SELECT: 
				switch (listParam.size()){
				case 0 : //return Vehicule.getAllVehicule();
					this.pList = ldao.findAll();
					Json<priorizedList> jV = new Json<priorizedList>(priorizedList.class);
					String jsonListPrio = jV.serialize(pList);
					return reponse = "selectAllPrio/"+jsonListPrio;
				}
			}
		case VEHICULE : 
			VehiculeDAOImpl vdao= new VehiculeDAOImpl(co);
			switch (type){
			case SELECT: 

				switch (listParam.size()){
				case 0 : //return Vehicule.getAllVehicule();
					this.listV = vdao.findAll();
					Json<ListVehicle> jV = new Json<ListVehicle>(ListVehicle.class);
					String jsonVehicle = jV.serialize(listV);
					return reponse = "selectAll/"+jsonVehicle;
				case 1 :

					if (listParam.containsKey(Parameter.ID)) {
						this.v = vdao.find(Integer.parseInt(listParam.get(Parameter.ID)));
						Json<Vehicule> jV2 = new Json<Vehicule>(Vehicule.class);
						String jsonVehicle2 = jV2.serialize(this.v);
						return reponse = "select/"+jsonVehicle2;

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
				 * UPDATE PREND 1 param :  le  vehicule mis ? jour
				 */
			case UPDATE:	

				switch (listParam.size()){
				case 0 : //RIEN
					break;
				case 1 : 

					Json<Vehicule> myJSon= new Json<Vehicule>(Vehicule.class);
					Vehicule v_update = myJSon.deSerialize(objectJson);
					Boolean bool =Boolean.valueOf(listParam.get(Parameter.PRESENCE));
					vdao.update(v_update);
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
					vdao.insert(v);
					return reponse = "insert";
				default :
					break;

				}



			}
		case CAR : 
			CarDAOImpl cdao= new CarDAOImpl(co);
			
			switch (type){
			case SELECT: 

				switch (listParam.size()){
				
				case 0 : //return Vehicule.getAllVehicule();
					this.listC = cdao.findAll();
					Json<ListCar> jV = new Json<ListCar>(ListCar.class);
					String jsonCar = jV.serialize(listC);
					return reponse = "selectAllCar/"+jsonCar;
				case 1 :

					if (listParam.containsKey(Parameter.ID)) {
						this.car = cdao.find(Integer.parseInt(listParam.get(Parameter.ID)));
						Json<Car> jV2 = new Json<Car>(Car.class);
						String jsonVehicle2 = jV2.serialize(this.car);
						return reponse = "selectCar/"+jsonVehicle2;

					}
					else if (listParam.containsKey(Parameter.IMMAT)) {
						//	 vdao.findByImmat(Integer.parseInt(listParam.get(Parameter.IMMAT)));
					}
					break;
				}

			case DELETE : 
				switch (listParam.size()){
				case 1 :
					cdao.delete(Integer.parseInt(listParam.get(Parameter.ID)));
					return reponse = "delete";
				default :
					break;

				}
				/*
				 * UPDATE PREND 1 param :  le  vehicule mis ? jour
				 */
			case UPDATE:	

				switch (listParam.size()){
				case 0 : //RIEN
					break;
				case 1 : 

					Json<Car> myJSon= new Json<Car>(Car.class);
					Car c_update = myJSon.deSerialize(objectJson);
					Boolean bool =Boolean.valueOf(listParam.get(Parameter.PRESENCE));
					cdao.update(c_update);
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
				case 3 :

					Json<Car> myJSon= new Json<Car>(Car.class);
					Car ca= myJSon.deSerialize(objectJson);
					// On vérifie l'existence du véhicule
					Car c=cdao.findByLicense(ca.getLicense_number());
					// Cas 1 : véhicule existe dans la BDD
					if (!(c==null)){
					cdao.update(ca);
					reponse="update";
					}
					// Cas 2 : véhicule non présent dans la BDD
					else {
					cdao.insert(ca);
					reponse="insert";
					c=cdao.findByLicense(ca.getLicense_number());
					
					}
					BreakdownDAOImpl breakdao= new BreakdownDAOImpl(co);
					String motif_breakdown=listParam.get(Parameter.MOTIF_BREAKDOWN);
					int id_breakdown=Integer.parseInt(listParam.get(Parameter.ID_BREAKDOWN));
					int id_employee=Integer.parseInt(listParam.get(Parameter.ID));
					log_breakdown lb=new log_breakdown(c.getId(),0 , id_employee, id_breakdown, motif_breakdown);
					breakdao.insert(lb);
					return reponse;
				default :
					break;

				}



			}
			
			case BIKE : 
				BikeDAOImpl bikedao= new BikeDAOImpl(co);
				
				switch (type){
				case SELECT: 

					switch (listParam.size()){
					
					case 0 : 
						this.listB = bikedao.findAll();
						Json<ListBike> jV = new Json<ListBike>(ListBike.class);
						String jsonBike = jV.serialize(listB);
						return reponse = "selectAllBike/"+jsonBike;
			
					
					
					
					case 1 :

						if (listParam.containsKey(Parameter.ID)) {
							this.Bike = bikedao.find(Integer.parseInt(listParam.get(Parameter.ID)));
							Json<Bike> jV2 = new Json<Bike>(Bike.class);
							String jsonVehicle2 = jV2.serialize(this.Bike);
							return reponse = "selectBike/"+jsonVehicle2;

						}
						else if (listParam.containsKey(Parameter.IMMAT)) {
							//	 vdao.findByImmat(Integer.parseInt(listParam.get(Parameter.IMMAT)));
						}
						break;
					}

				case DELETE : 
					switch (listParam.size()){
					case 1 :
						bikedao.delete(Integer.parseInt(listParam.get(Parameter.ID)));
						return reponse = "delete";
					default :
						break;

					}
					/*
					 * UPDATE PREND 1 param :  le  vehicule mis ? jour
					 */
				case UPDATE:	

					switch (listParam.size()){
					case 0 : //RIEN
						break;
					case 1 : 

						Json<Bike> myJSon= new Json<Bike>(Bike.class);
						Bike c_update = myJSon.deSerialize(objectJson);
						Boolean bool =Boolean.valueOf(listParam.get(Parameter.PRESENCE));
						bikedao.update(c_update);
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
					case 3 :
						// A Faire :
						//Gérer un numéro unique de vélo (style license)
						Json<Bike> myJSon= new Json<Bike>(Bike.class);
						Bike bike= myJSon.deSerialize(objectJson);
						// On vérifie l'existence du véhicule
						Bike b=bikedao.find(bike.getId());
						// Cas 1 : véhicule existe dans la BDD
						if (!b.equals(null)){
						bikedao.update(bike);
						reponse="update";
						}
						// Cas 2 : véhicule non présent dans la BDD
						else {
						bikedao.insert(bike);
						reponse="insert";
						}
						BreakdownDAOImpl breakdao= new BreakdownDAOImpl(co);
						
						String motif_breakdown=listParam.get(Parameter.MOTIF_BREAKDOWN);
						int id_breakdown=Integer.parseInt(listParam.get(Parameter.ID_BREAKDOWN));
						int id_employee=Integer.parseInt(listParam.get(Parameter.ID));
						log_breakdown lb=new log_breakdown(0,b.getId(), id_employee, id_breakdown, motif_breakdown);
						breakdao.insert(lb);
						return reponse;
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

					int id = udao.checkUserdb(listParam.get(Parameter.NAME), listParam.get(Parameter.PWD));

					if (id!=0){
						System.out.println("connection ok");
						reponse="connection ok+"+id;
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
