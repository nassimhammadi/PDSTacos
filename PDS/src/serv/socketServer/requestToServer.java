
package serv.socketServer;
import serv.DB.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import serv.model.Car;
import serv.model.ListBreakdowns;
import serv.model.ListVehicle;
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
	private Car car;
	private ListBreakdowns bdList;
/*
 * M�thode permettant de traduire et d'executer la requ�te du client
 */
	public String evalRequest() throws IOException{
		String reponse="";
		
		switch (classe){
		case BREAKDOWNS : 
			BreakdownsDAOImpl bdao = new BreakdownsDAOImpl(co);
			switch(type){
			case SELECTBDCAR:
				switch(listParam.size()){
				case 1 :
					this.bdList =  bdao.findAllCar(1);
					Json<ListBreakdowns> jV = new Json<ListBreakdowns>(ListBreakdowns.class);
					String jsonListBd = jV.serialize(bdList);
					return reponse = "selectBdCar/"+jsonListBd;
				}
			}
		
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
				 * UPDATE PREND 1 param :  le  vehicule mis � jour
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
					 * UPDATE PREND 1 param :  le  vehicule mis � jour
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
					case 1 : 	
						Json<Car> myJSon= new Json<Car>(Car.class);
						Car ca= myJSon.deSerialize(objectJson);
						cdao.insert(ca);
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
