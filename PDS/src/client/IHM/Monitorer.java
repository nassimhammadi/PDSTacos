package client.IHM;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import client.IHM.Repair.checkMessageChange;
import client.json.Json;
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
	  
	public Monitorer(Client cli){
		
	    this.setTitle("Activity of Deposit - Workflow");
	    this.setSize(700, 700);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    //On définit le layout à utiliser sur le content pane
	    this.setLayout(new BorderLayout());
	    
	    
	    JPanel nord = new JPanel(new GridLayout(1,2));
	    nord.setBackground(Color.white);
	    nord.setPreferredSize(new Dimension(50,50));
	    
        nord.setBorder(new TitledBorder("Rechercher"));
        
	    
	    JLabel statut = new JLabel("Statut :");
	    Choice c = new Choice();  
	       c.addItem("All");  
	       c.addItem("En cours");  
	       c.addItem("Terminé");  
	       c.addItem("En attente");
	      
	       c.select("All");  
	    
	       nord.add(statut);
	       nord.add(c);
	      
	       JPanel centre = new JPanel(new GridLayout(1,2));
		   centre.setBackground(Color.white);
		   centre.setPreferredSize(new Dimension(50,50));
		
		   
		 JPanel west = new JPanel();
		 west.setPreferredSize(new Dimension(50,50));
		 west.setBorder(new TitledBorder("Liste des véhicules"));
		 displayAllVehicle();
		 centre.add(west);
		 
		 JPanel east = new JPanel();
		 
		 east.setPreferredSize(new Dimension(50,50));
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
	

	
	public void getAllVehicle(){
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

	public void displayAllVehicle(){
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
    	for(priorizedListObject pList : prioList.getPriorizedList()){
            sizeOfPrioList+=1;
        }  
    	System.out.println(sizeOfPrioList);
        // JPanel to show priorizedList of vehicle
        list = new JPanel(new GridLayout(sizeOfPrioList+1,1));
        list.setBackground(Color.WHITE);
    	list.add(new JLabel("Priorité"));
    	list.add(new JLabel("Identifiant du véhicule"));
    	list.add(new JLabel("Date d'entrée"));
    	for(priorizedListObject pList : prioList.getPriorizedList()){
            list.add(new JLabel(""+pList.getId_prio()));
            list.add(new JLabel(""+pList.getId_car()));
            list.add(new JLabel(""+pList.getDate_occured()));
        }  
    	centrewest.add(list);
    	
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
			
				}
			}

		}

}

public static void main(String[] args){
	Monitorer essai = new Monitorer(null);
}
}
