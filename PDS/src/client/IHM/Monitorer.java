package client.IHM;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import client.IHM.Repair.checkMessageChange;
import client.json.Json;
import client.model.Car;
import client.model.ListCar;
import client.model.ListVehicle;
import client.model.Vehicule;
import client.model.priorizedList;
import client.model.priorizedListObject;
import client.socketClient.AllClasses;
import client.socketClient.Client;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;


public class Monitorer extends JFrame{
	private Client c;
	private priorizedList prioList;
	private Thread t_all;
	private JPanel centrewest;
	private JPanel list;
	private int sizeOfPrioList;
	private JPanel west;
	private ListCar listC;
	private Choice choice;
	  
	public Monitorer(Client cli){
		this.c = cli;
	    this.setTitle("Activity of Deposit - Workflow");
	    this.setSize(900, 900);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    //On définit le layout à utiliser sur le content pane
	    this.setLayout(new BorderLayout());
	    
	    
	    centrewest = new JPanel(new FlowLayout());
	    
        centrewest.setBackground(Color.white);
	    JPanel nord = new JPanel(new GridLayout(1,2));
	    nord.setBackground(Color.white);
	    nord.setPreferredSize(new Dimension(50,50));
	    
        nord.setBorder(new TitledBorder("Rechercher"));
        
	    
	    JLabel statut = new JLabel("Statut :");
	    choice = new Choice();  
	       choice.addItem("All");  
	       choice.addItem("En cours");  
	       choice.addItem("Termine");  
	       choice.addItem("En attente");
	      
	       choice.select("All");  
	       choice.addItemListener(new ItemListener(){
		        public void itemStateChanged(ItemEvent ie)
		        {
		        	if(choice.getSelectedItem() == "All"){
		        		displayAllVehicle();
		        	}
		        	else if(choice.getSelectedItem() == "En cours"){
		        		displayOccuredVehicle();
		        	}
		        	else if(choice.getSelectedItem() == "Termine"){
		        		displayFinishedVehicle();
		        	}
		        	else if(choice.getSelectedItem() == "En attente"){
		        		displayAllPLVehicle();
		        	}
		        }
		    });
	       nord.add(statut);
	       nord.add(choice);
	      
	       JPanel centre = new JPanel(new GridLayout(1,2));
		   centre.setBackground(Color.white);
		   
		
		   
		 west = new JPanel();
		 
		 west.setBorder(new TitledBorder("Liste des véhicules"));
		 displayAllVehicle();
		 centre.add(west);
		 
		 JPanel east = new JPanel();
		 
		 
		 east.setBorder(new TitledBorder("Workflow"));
		 centre.add(east);
		 
		 JLabel SearchIDVehicule = new JLabel("Recherche : ");
		
		 JTextField IDVehicule = new JTextField("Entrez l'ID du véhicule");
		 east.add(SearchIDVehicule);
		 east.add(IDVehicule);
	    
		    JPanel south = new JPanel(new GridLayout(1,1));
		    south.setBackground(Color.white);
		    south.setPreferredSize(new Dimension(50,50));
		    
	        south.setBorder(new TitledBorder("Cumul Journée"));
	        
	        String format = "dd/MM/yy hh:mm:ss"; 

	        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
	        java.util.Date date = new java.util.Date(); 

	       
	        JLabel dateCJ = new JLabel("Aujourd'hui (" + formater.format( date ) + "), il y a eu 4 voitures réparés");
	        
		    south.add(dateCJ);
		    
		    
	    JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.white);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(nord, BorderLayout.NORTH);
        panelPrincipal.add(centre, BorderLayout.CENTER);
     
        panelPrincipal.add(south, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    setContentPane(panelPrincipal);
	    
	    this.setVisible(true);
	   
	    
	    
	}
	

	
	public void getPLVehicle(){
		String identif ="";
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		requestToServer rts=new requestToServer(AllClasses.REPAIR,TypeRequest.SELECT,"",param);
		Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
		String jsonAuth = jsonRTS.serialize(rts);
		rep=c.getCcs().getLastMessageFromServeur();
		c.getCcs().setLastMessageToServer(jsonAuth);
		checkMessageChange cmc= new checkMessageChange(rep);
		t_all=new Thread(cmc);
		t_all.start();
	}

	public void displayAllPLVehicle(){
		west.removeAll();
    	getPLVehicle();
    	Thread a = new Thread();
    	a.start();
    	try {
			a.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			a.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		sizeOfPrioList =0;
    	for(priorizedListObject pList : prioList.getPriorizedList()){
            sizeOfPrioList+=1;
        }  
    	System.out.println(sizeOfPrioList);
        // JPanel to show priorizedList of vehicle
        list = new JPanel(new GridLayout(sizeOfPrioList+1,3));
      
        //list.setBackground(Color.WHITE);
    	
    	list.add(new JLabel("Identifiant du véhicule"));
    	list.add(new JLabel("   Modèle"));
    	list.add(new JLabel("   Date d'entrée"));
    	for(priorizedListObject pList : prioList.getPriorizedList()){
    		list.add(new JLabel(""+pList.getId_car()));
            list.add(new JLabel("   "+pList.getModel()));
            list.add(new JLabel("   "+pList.getDate_occured()));
        }  
    	west.add(list);
    	west.updateUI();
    	setVisible(true);
    	
    }
	
	public void displayAllVehicle(){
		west.removeAll();
		getAllVehicle();
    	Thread a = new Thread();
    	a.start();
    	try {
			a.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			a.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		sizeOfPrioList =0;
    	for(Car c : listC.getL_b()){
            sizeOfPrioList+=1;
        }  
    	System.out.println(sizeOfPrioList);
        // JPanel to show priorizedList of vehicle
        list = new JPanel(new GridLayout(sizeOfPrioList+1,3));
      
        //list.setBackground(Color.WHITE);
    	list.add(new JLabel("Identifiant du véhicule"));
    	list.add(new JLabel("    Modèle"));
    	list.add(new JLabel("    Date d'entrée"));
    	for(Car c : listC.getL_b()){
            list.add(new JLabel(""+c.getId()));
            list.add(new JLabel("    "+c.getModel()));
            list.add(new JLabel("    "+c.getDateEntry()));
        }  
    	west.add(list);
    	west.updateUI();
    	setVisible(true);
    	
	}
	
	public void getAllVehicle(){
		String identif ="";
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		requestToServer rts=new requestToServer(AllClasses.CAR,TypeRequest.SELECT,"",param);
		Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
		String jsonAuth = jsonRTS.serialize(rts);
		rep=c.getCcs().getLastMessageFromServeur();
		c.getCcs().setLastMessageToServer(jsonAuth);
		checkMessageChange cmc= new checkMessageChange(rep);
		t_all=new Thread(cmc);
		t_all.start();
		try {
			t_all.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getOccuredVehicle(){
		String identif ="";
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		requestToServer rts=new requestToServer(AllClasses.CAR,TypeRequest.OCCURED,"",param);
		Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
		String jsonAuth = jsonRTS.serialize(rts);
		rep=c.getCcs().getLastMessageFromServeur();
		c.getCcs().setLastMessageToServer(jsonAuth);
		checkMessageChange cmc= new checkMessageChange(rep);
		t_all=new Thread(cmc);
		t_all.start();
		try {
			t_all.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayOccuredVehicle(){
		west.removeAll();
		getOccuredVehicle();
    	Thread a = new Thread();
    	a.start();
    	try {
			a.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			a.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		sizeOfPrioList =0;
    	for(Car c : listC.getL_b()){
            sizeOfPrioList+=1;
        }  
    	System.out.println(sizeOfPrioList);
        // JPanel to show priorizedList of vehicle
        list = new JPanel(new GridLayout(sizeOfPrioList+1,3));
      
        //list.setBackground(Color.WHITE);
    	list.add(new JLabel("Identifiant du véhicule"));
    	list.add(new JLabel("    Modèle"));
    	list.add(new JLabel("    Date d'entrée"));
    	for(Car c : listC.getL_b()){
            list.add(new JLabel(""+c.getId()));
            list.add(new JLabel("    "+c.getModel()));
            list.add(new JLabel("    "+c.getDateEntry()));
        }  
    	west.add(list);
    	west.updateUI();
    	setVisible(true);
    	
	}
	
	public void getFinishedVehicle(){
		String identif ="";
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		requestToServer rts=new requestToServer(AllClasses.CAR,TypeRequest.FINISHED,"",param);
		Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
		String jsonAuth = jsonRTS.serialize(rts);
		rep=c.getCcs().getLastMessageFromServeur();
		c.getCcs().setLastMessageToServer(jsonAuth);
		checkMessageChange cmc= new checkMessageChange(rep);
		t_all=new Thread(cmc);
		t_all.start();
		try {
			t_all.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayFinishedVehicle(){
		west.removeAll();
		getFinishedVehicle();
    	Thread a = new Thread();
    	a.start();
    	try {
			a.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			a.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		sizeOfPrioList =0;
    	for(Car c : listC.getL_b()){
            sizeOfPrioList+=1;
        }  
    	System.out.println(sizeOfPrioList);
        // JPanel to show priorizedList of vehicle
        list = new JPanel(new GridLayout(sizeOfPrioList+1,3));
      
        //list.setBackground(Color.WHITE);
    	list.add(new JLabel("Identifiant du véhicule"));
    	list.add(new JLabel("Modèle"));
    	list.add(new JLabel("    Date d'entrée"));
    	for(Car c : listC.getL_b()){
            list.add(new JLabel(""+c.getId()));
            list.add(new JLabel(""+c.getModel()));
            list.add(new JLabel("    "+c.getDateEntry()));
        }  
    	west.add(list);
    	west.updateUI();
    	setVisible(true);
    	
	}
	
	
class checkMessageChange implements Runnable{
		
		String rep="";
		checkMessageChange(String rep){
			this.rep=rep;
		}
		@Override
		public void run() {
			boolean fin=false;
			
			
			
			while (!fin){
                if(!rep.equals(c.getCcs().getLastMessageFromServeur())){
				String string = c.getCcs().getLastMessageFromServeur();
				String [] strings = string.split("/");
				String part1 = strings[0];
				String part2 ="";
				if(strings.length==2){
					part2 = strings[1];
				}
				 if(part1.equals("selectAllPrio")){
					Json <priorizedList> myJSon= new Json<priorizedList>(priorizedList.class);
					 
					try {
						prioList = myJSon.deSerialize(part2);
						
						System.out.println("All :"+prioList);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fin =true;
				}
				 else if(part1.equals("selectAllCar")){
						Json <ListCar> myJSon2= new Json<ListCar>(ListCar.class);

					try {
							listC= myJSon2.deSerialize(part2);
						System.out.println("All :"+listC);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fin =true;
				}
				else if(part1.equals("selectAllCarOccured")){
						Json <ListCar> myJSon3= new Json<ListCar>(ListCar.class);

					try {
							listC= myJSon3.deSerialize(part2);
						System.out.println("All :"+listC);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fin =true;
				}
				else if(part1.equals("selectAllCarRepared")){
					Json <ListCar> myJSon3= new Json<ListCar>(ListCar.class);

				try {
						listC= myJSon3.deSerialize(part2);
					System.out.println("All :"+listC);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fin =true;
			}
				}
			}

		}

}

public static void main(String[] args){
	Monitorer essai = new Monitorer(null);
}
}
