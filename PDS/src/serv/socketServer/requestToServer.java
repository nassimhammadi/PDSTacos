
package serv.socketServer;
import serv.DB.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.model.log_breakdown;
import client.socketClient.Parameter;
import serv.model.Car;
import serv.model.ListBike;
import serv.model.ListCar;
import serv.model.ListPieces;
import serv.model.Bike;
import serv.model.ListVehicle;
import serv.model.LogsBreakdown;
import serv.model.LogsBreakdownList;
import serv.model.PerformanceList;
import serv.model.UserList;
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
	private UserList uList;
	private Car car;
	private Breakdown breakdown;
	private Bike Bike;
	private ListCar listC;
	private ListBike listB;
	private LogsBreakdown logB;
	private ListPieces listP;
	private PerformanceList nbRep;
	private LogsBreakdownList bd_l;
	/*
	 * M?thode permettant de traduire et d'executer la requ?te du client
	 */
	public String evalRequest() throws IOException{
		String reponse="";

		switch (classe){
		case LOGS_BREAKDOWN:
			LogsBreakdownDAOImpl ldimpl = new LogsBreakdownDAOImpl(co);
			switch(type){
			case SELECT:
				this.logB = ldimpl.findCar(Integer.parseInt(listParam.get(Parameter.ID)),Integer.parseInt(listParam.get(Parameter.ID_BREAKDOWN)));
				Json<LogsBreakdown> jV = new Json<LogsBreakdown>(LogsBreakdown.class);
				String jsonLogCar = jV.serialize(logB);
				return reponse = "selectLog/"+jsonLogCar;
			case SELECTB:
				this.logB = ldimpl.findBike(Integer.parseInt(listParam.get(Parameter.ID)),Integer.parseInt(listParam.get(Parameter.ID_BREAKDOWN)));
				Json<LogsBreakdown> jV2 = new Json<LogsBreakdown>(LogsBreakdown.class);
				String jsonLogBike = jV2.serialize(logB);
				return reponse = "selectLog/"+jsonLogBike;
			case IND_SELECTNBREP:
				String vehicletype = listParam.get(Parameter.IND_VEHICLETYPE);
				
				int id_ope=0;
				if (!listParam.get(Parameter.IND_IdOPE).contains("Indifferent"))
				 id_ope= Integer.parseInt(listParam.get(Parameter.IND_IdOPE));
				
				
				int id_emp=0;
				if (!listParam.get(Parameter.IND_IdEMP).contains("Indifferent"))
					 id_emp= Integer.parseInt(listParam.get(Parameter.IND_IdEMP));
				
				Date dateBegin= Date.valueOf(listParam.get(Parameter.IND_DATEBEGIN));
				Date dateEnd= Date.valueOf(listParam.get(Parameter.IND_DATEEND));
				String periode= listParam.get(Parameter.IND_Periode);
				
				this.nbRep= ldimpl.countRep(vehicletype, id_ope, id_emp, dateBegin, dateEnd, periode);
				Json<PerformanceList> jV3 = new Json<PerformanceList>(PerformanceList.class);
				String jsonNbRep = jV3.serialize(nbRep);
				return reponse ="selectNbRep/"+jsonNbRep;
				
			case SELECT_MONITORER:
				this.bd_l = ldimpl.findCar(Integer.parseInt(listParam.get(Parameter.ID)));
				Json<LogsBreakdownList> jV4 = new Json<LogsBreakdownList>(LogsBreakdownList.class);
				String jsonLogCar4 = jV4.serialize(bd_l);
				return reponse = "selectCarLog/"+jsonLogCar4;
				
			}
		
		case BREAKDOWN :
			BreakdownDAOImpl bdao = new BreakdownDAOImpl(co);
			switch (type){
			case SELECT:
				this.bList = bdao.findAll();
				Json<BreakdownList> jV = new Json<BreakdownList>(BreakdownList.class);
				String jsonListBreak = jV.serialize(bList);
				return reponse = "selectAllBreakDown/"+jsonListBreak;
			
			case SELECTID:
				this.bList = bdao.findAll(Integer.parseInt(listParam.get(Parameter.ID)));
				Json<BreakdownList> jV2 = new Json<BreakdownList>(BreakdownList.class);
				String jsonListBreak2 = jV2.serialize(bList);
				return reponse = "selectAllBreakDownId/"+jsonListBreak2;
				
			}
			
			


			break;



		case REPAIR :
			
			switch (type){
			case SELECT: 
				priorizedListDAOimpl ldao = new priorizedListDAOimpl(co);
				switch (listParam.size()){
				case 0 : //return Vehicule.getAllVehicule();
					this.pList = ldao.findAll();
					Json<priorizedList> jV = new Json<priorizedList>(priorizedList.class);
					String jsonListPrio = jV.serialize(pList);
					return reponse = "selectAllPrio/"+jsonListPrio;
				}
			case UPDATE: 
				LogsBreakdownDAOImpl lbimpl = new LogsBreakdownDAOImpl(co);
				int id = Integer.parseInt(listParam.get(Parameter.ID));
				int id_bd_log = Integer.parseInt(listParam.get(Parameter.ID_BREAKDOWN));;
				String comment = listParam.get(Parameter.COM) ;
				ListPieces lp = null;
				Json <ListPieces> myJSon= new Json<ListPieces>(ListPieces.class);
				try {
					lp = myJSon.deSerialize(listParam.get(Parameter.LIST));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Listppppp"+lp);
				lbimpl.update(id,id_bd_log, comment,lp);
				return reponse="ok";
			case FINISH:
				
					LogsBreakdownDAOImpl lbimplf = new LogsBreakdownDAOImpl(co);
					int idf = Integer.parseInt(listParam.get(Parameter.ID));
					int id_v = Integer.parseInt(listParam.get(Parameter.VEHICULE));
					lbimplf.updateFinish(idf, id_v);
					return reponse="ok";
				
			case FINISHB :
				LogsBreakdownDAOImpl lbimplf2 = new LogsBreakdownDAOImpl(co);
				int idf2 = Integer.parseInt(listParam.get(Parameter.ID));
				int id_v2 = Integer.parseInt(listParam.get(Parameter.VEHICULE));
				lbimplf2.updateFinishB(idf2, id_v2);
				return reponse="ok";
			
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
		case PIECES:
			PiecesDAOImpl pdao = new PiecesDAOImpl(co);
			switch(type){
			case SELECT:
				this.listP= pdao.findList(Integer.parseInt(listParam.get(Parameter.ID)));
				Json<ListPieces> jV = new Json<ListPieces>(ListPieces.class);
				String jsonPieces = jV.serialize(listP);
				return reponse = "selectAllPieces/"+jsonPieces;
			case SELECTB: 
				this.listP= pdao.findListB();
				Json<ListPieces> jV2 = new Json<ListPieces>(ListPieces.class);
				String jsonPieces2 = jV2.serialize(listP);
				return reponse = "selectAllPieces/"+jsonPieces2;
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
				case 2 :
					this.car = cdao.findAndDeletePrio(Integer.parseInt(listParam.get(Parameter.ID)),Integer.parseInt(listParam.get(Parameter.ID_PRIO)));
					Json<Car> jV2 = new Json<Car>(Car.class);
					String jsonVehicle2 = jV2.serialize(this.car);
					return reponse = "selectCar/"+jsonVehicle2;
					
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
			case OCCURED: 
				this.listC = cdao.findAllOccured();
				Json<ListCar> jV = new Json<ListCar>(ListCar.class);
				String jsonCar = jV.serialize(listC);
				return reponse = "selectAllCarOccured/"+jsonCar;
			case FINISHED:
				this.listC = cdao.findAllFinished();
				Json<ListCar> jV2 = new Json<ListCar>(ListCar.class);
				String jsonCar2 = jV2.serialize(listC);
				return reponse = "selectAllCarRepared/"+jsonCar2;
			case INSERT:
				switch (listParam.size()){
				case 0 : //RIEN
					break;
				case 3 :

					Json<Car> myJSon= new Json<Car>(Car.class);
					Car ca= myJSon.deSerialize(objectJson);
					// On v�rifie l'existence du v�hicule
					Car c=cdao.findByLicense(ca.getLicense_number());
					// Cas 1 : v�hicule existe dans la BDD
					if (!(c==null)){
					cdao.updateByLicense(ca);
					reponse="update";
					}
					// Cas 2 : v�hicule non pr�sent dans la BDD
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
						//G�rer un num�ro unique de v�lo (style license)
						Json<Bike> myJSon= new Json<Bike>(Bike.class);
						Bike bike= myJSon.deSerialize(objectJson);
						// On v�rifie l'existence du v�hicule
						Bike b=bikedao.find(bike.getId());
						// Cas 1 : v�hicule existe dans la BDD
						if (!(b==null)){
						bikedao.update(bike);
						reponse="update";
						}
						// Cas 2 : v�hicule non pr�sent dans la BDD
						else {
						bikedao.insert(bike);
						reponse="insert";
						}
						BreakdownDAOImpl breakdao= new BreakdownDAOImpl(co);
						
						String motif_breakdown=listParam.get(Parameter.MOTIF_BREAKDOWN);
						int id_breakdown=Integer.parseInt(listParam.get(Parameter.ID_BREAKDOWN));
						int id_employee=Integer.parseInt(listParam.get(Parameter.ID));
						log_breakdown lb=new log_breakdown(0,bike.getId(), id_employee, id_breakdown, motif_breakdown);
						breakdao.insert(lb);
						return reponse;
					default :
						break;

					}



				}
		case EMPLOYEE :
			UserDAOImpl udao= new UserDAOImpl(co);
			switch(type){
			case SELECT:
				switch (listParam.size()){
				case 0:
				this.uList = udao.getAllUser();
				Json<UserList> jV = new Json<UserList>(UserList.class);
				String jsonListUser = jV.serialize(uList);
				return reponse = "selectAllUser/"+jsonListUser;
				default :
					break;
				
				}

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
