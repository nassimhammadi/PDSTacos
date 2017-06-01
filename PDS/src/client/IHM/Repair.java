package client.IHM;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import client.IHM.HomeManager.checkMessageChange;
import client.IHM.HomeManager.deleteListener;
import client.IHM.HomeManager.insertListener;
import client.IHM.HomeManager.selectListener;
import client.IHM.HomeManager.updateListener;
import client.json.Json;
import client.model.Car;
import client.model.ListVehicle;
import client.model.Vehicule;
import client.model.priorizedList;
import client.model.priorizedListObject;
import client.socketClient.AllClasses;
import client.socketClient.Client;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;
import serv.socketServer.Serveur;


public class Repair extends JFrame {
	private Client c;
    private CheckboxGroup cbg_type = new CheckboxGroup();
    private CheckboxGroup cbg_motorisation = new CheckboxGroup();
    private CheckboxGroup cbg_type_up = new CheckboxGroup();
    private CheckboxGroup cbg_motorisation_up = new CheckboxGroup();
    private CheckboxGroup cbg_insert_presence = new CheckboxGroup();
    private CheckboxGroup cbg_update_presence = new CheckboxGroup();
    Checkbox cb_update_presence = new Checkbox("Oui",cbg_update_presence,true);
    Checkbox cb_update_no_presence = new Checkbox("Non",cbg_update_presence,false);
    private JTextField id_search;
    private JLabel immatricul;
    private JLabel dateEntry;
    private JLabel yearv;
    private JLabel motor;
    private JRadioButton present;
    private JLabel brand;
    private JLabel model;
    private JFrame jf;
    private JTextField id_update;
    private JTextField im_ins;
    private Checkbox ct1_ins;
    private Checkbox ct2_ins;
    private JTextField year_ins;
    private Checkbox cm1_ins;
    private Checkbox cm2_ins;
    private JLabel im_j_ins;
    private JTextField brand_ins;
    private JTextField model_ins;
    private JTextField id_ins;
    private Checkbox cp1_ins;
    private Checkbox cp2_ins;
    private JLabel im_j_up;
    private JTextField im_up;
    private Checkbox ct1_up;
    private Checkbox ct2_up;
    private JTextField year_up;
    private Checkbox cm1_up;
    private Checkbox cm2_up;
    private JTextField brand_up;
    private JTextField model_up;
    private JTextField id_up;
    private Checkbox cp1_up;
    private Checkbox cp2_up;
    private JTextField id_del;
    private ListVehicle listV;
    private priorizedList prioList;
    private priorizedListObject prioListObject;
    private JPanel southRight;
    private JPanel list;
    private Thread t_all;
    private int sizeOfPrioList;
    private Car myCar;
    private JScrollPane jsp;
    private JPanel panelEast2;
    

    /**
     * 
     * @param cli
     * Constructor which create the manager view
     */

    

    public Repair(Client cli){
    	
    	this.c=cli;
    	this.jf = this;
    	getAllVehicle();
        // Add Menu
        MenuBar menu = new MenuBar();
        JPanel panelNord = new JPanel();
        Color c=new Color(1f,0f,0f,.5f);
        panelNord.setBackground(c);
        panelNord.setLayout(new GridLayout(1, 1)); // 1 ligne, 2 colonnes
        panelNord.add(menu.getMenu());
        System.out.println(listV);
        
        JPanel panelWest = new JPanel(new BorderLayout());
        panelWest.setBackground(Color.white);
        
        // JPanel to take a repair
        JPanel panelWest1 = new JPanel(new GridLayout(4,1));
        panelWest1.setBackground(Color.white);
        panelWest1.setPreferredSize(new Dimension(300, 200));
        panelWest1.add(new JLabel("Vous avez une réparation urgente"));
        JButton search = new JButton("Prendre en charge");
        selectListener sl = new selectListener(this);
        search.addActionListener(sl);
        panelWest1.add(search);
       
        // Jpanel to check breakdowns on a specific vehicle
        panelEast2 = new JPanel(new GridLayout(10,2));
        panelEast2.setBackground(Color.white);
        panelEast2.setPreferredSize(new Dimension(300, 200));
        panelEast2.setPreferredSize(new Dimension(500,500));
        panelWest.add(panelWest1,BorderLayout.WEST);
        panelWest.add(panelEast2,BorderLayout.CENTER);
       
        
        JPanel panelSouth =  new JPanel(new GridLayout(1,1));
        panelSouth.setBackground(Color.white);
        panelSouth.setPreferredSize(new Dimension(300,350));
        panelSouth.setBorder(new TitledBorder("Informations sur le véhicule : "));
     //   panelSouth.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		 
        JPanel southLeft =  new JPanel(new GridLayout(9,1));
        southLeft.setBackground(Color.white);
        BoxLayout layoutSouthLeft = new BoxLayout(southLeft, BoxLayout.Y_AXIS);
        southLeft.setLayout(layoutSouthLeft);
        
        southRight = new JPanel(new GridLayout(9,1));
        southRight.setBackground(Color.white);
        southLeft.add(Box.createGlue());
        
        
        present = new JRadioButton("Présent dans le dépôt");
        southLeft.add(present);
        
        
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Immatriculation : "));
        immatricul = new JLabel();
        southLeft.add(immatricul);
        
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Date d'entrée du véhicule : "));
        dateEntry = new JLabel();
        southLeft.add(dateEntry);
        
        
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Année de mise en circulation : "));
        yearv = new JLabel();
        southLeft.add(yearv);
        
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Motorisation : "));
        motor = new JLabel();
        southLeft.add(motor);

     
        
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Marque : "));
        brand = new JLabel();
        southLeft.add(brand);
        
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Modèle : "));
        model = new JLabel();
        southLeft.add(model);
        
        southLeft.add(Box.createGlue());
        panelSouth.add(southLeft);
        panelSouth.add(southRight);
        
       

       
        int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED; 
        jsp=new JScrollPane(southRight,v,h);
        jsp.setBorder(new TitledBorder("Liste priorisée des réparations : "));
        BoxLayout layoutSouthRight = new BoxLayout(southRight, BoxLayout.Y_AXIS);
        southRight.setLayout(layoutSouthRight);
        
        displayAllVehicle();
        panelSouth.add(jsp);
        
      

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.white);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelNord, BorderLayout.NORTH);
        panelPrincipal.add(panelWest, BorderLayout.CENTER);
        panelPrincipal.add(panelSouth, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       

        setContentPane(panelPrincipal);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("CSC App - Nassim Hammadi (M)");
        this.setBackground(Color.white);
        setVisible(true);

    }

   /**
    * 
    * @return
    * Return id_update
    */
	public JTextField getId_update() {
		return id_update;
	}

	/**
	 * 
	 * @param id_update
	 * Set id_update
	 */
	public void setId_update(JTextField id_update) {
		this.id_update = id_update;
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
        list = new JPanel(new GridLayout(sizeOfPrioList+1,3));
        list.setBackground(Color.WHITE);
    	list.add(new JLabel("Priorité"));
    	list.add(new JLabel("Identifiant du véhicule"));
    	list.add(new JLabel("Date d'entrée"));
    	int i =0;
    	for(priorizedListObject pList : prioList.getPriorizedList()){
    		int id;
    		if(pList.getId_car() == 0){
    			id=pList.getId_bike();
    		}
    		else{
    			id=pList.getId_car();
    		}
    		if(i==0){
    			JLabel j1 = new JLabel(""+pList.getId_prio());
    			JLabel j2 = new JLabel(""+id);
    			JLabel j3 = new JLabel(""+pList.getDate_occured());
    			j1.setBackground(Color.RED);
    			j1.setOpaque(true);
    			j2.setBackground(Color.RED);
    			j2.setOpaque(true);
    			j3.setBackground(Color.RED);
    			j3.setOpaque(true);
    			list.add(j1);
    			list.add(j2);
    			list.add(j3);
    			this.prioListObject = pList;
    		} 
    		else{
            list.add(new JLabel(""+pList.getId_prio()));
            list.add(new JLabel(""+id));
            list.add(new JLabel(""+pList.getDate_occured()));
    		}
    		i++;
        }  
    	southRight.add(list);
    	setVisible(true);
    	
    }
	
	public void getAllBreakDowns(){
		String identif =String.valueOf(prioListObject.getId_car());
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		param.put(Parameter.ID, identif);
		requestToServer rts=new requestToServer(AllClasses.BREAKDOWN,TypeRequest.SELECT,"",param);
		Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
		String jsonAuth = jsonRTS.serialize(rts);
		rep=c.getCcs().getLastMessageFromServeur();
		c.getCcs().setLastMessageToServer(jsonAuth);
		checkMessageChange cmc= new checkMessageChange(rep);
		t_all=new Thread(cmc);
		t_all.start();
	}

	public void displayAllBreakDowns(){
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
        list = new JPanel(new GridLayout(sizeOfPrioList+1,3));
        list.setBackground(Color.WHITE);
    	list.add(new JLabel("Priorité"));
    	list.add(new JLabel("Identifiant du véhicule"));
    	list.add(new JLabel("Date d'entrée"));
    	int i =0;
    	for(priorizedListObject pList : prioList.getPriorizedList()){
    		int id;
    		if(pList.getId_car() == 0){
    			id=pList.getId_bike();
    		}
    		else{
    			id=pList.getId_car();
    		}
    		if(i==0){
    			JLabel j1 = new JLabel(""+pList.getId_prio());
    			JLabel j2 = new JLabel(""+id);
    			JLabel j3 = new JLabel(""+pList.getDate_occured());
    			j1.setBackground(Color.RED);
    			j1.setOpaque(true);
    			j2.setBackground(Color.RED);
    			j2.setOpaque(true);
    			j3.setBackground(Color.RED);
    			j3.setOpaque(true);
    			list.add(j1);
    			list.add(j2);
    			list.add(j3);
    			this.prioListObject = pList;
    		} 
    		else{
            list.add(new JLabel(""+pList.getId_prio()));
            list.add(new JLabel(""+id));
            list.add(new JLabel(""+pList.getDate_occured()));
    		}
    		i++;
        }  
    	southRight.add(list);
    	setVisible(true);
    	
    }
    /**
     * 
     * @author nassimhammadi laurahollard
     * Inner class which implements ActionListener.
     * Uses when the user click on update
     *
     */
    class updateListener implements ActionListener{

    	private Repair rp;

		public updateListener(Repair r) {
			this.rp = r;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String identif=id_search.getText();
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			int t_up;
		    Boolean m_up = false;
		    Boolean p_up = false;
		    if(ct1_up.getState()){
		    	t_up = 1;
		    } else t_up = 0;
		    
		    if(cm1_up.getState()){
		    	m_up = true;
		    }
		    
		    if(cp1_up.getState()){
		    	p_up = true;
		    }
			Vehicule v_up = new Vehicule(Integer.parseInt(id_up.getText()),im_up.getText(),t_up,year_up.getText(),m_up,p_up,brand_up.getText(),model_up.getText());
			
			Json<Vehicule> myJSon= new Json<Vehicule>(Vehicule.class);
			Json myJSon_up= new Json(Vehicule.class);
			String v_i= myJSon_up.serialize(v_up);
			param.put(Parameter.ID, id_up.getText());
			
			System.out.println("Param"+Parameter.ID);
			requestToServer rtsu=new requestToServer(AllClasses.VEHICULE,TypeRequest.UPDATE,v_i,param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rtsu);
			rep=c.getCcs().getLastMessageFromServeur();
			System.out.println("Last Message :"+rep);
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(rep);
			Thread t=new Thread(cmc);
			t.start();
		}
	}
    
    	/**
    	 * 
    	 * @author nassimhammadi laurahollard
    	 * Inner class which implements ActionListener.
    	 * Uses when the user click on delete
    	 *
    	 */
    	class deleteListener implements ActionListener{

    		private Repair rp;

    		public deleteListener(Repair r) {
    			this.rp = r;
    		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			param.put(Parameter.ID, id_del.getText());
			requestToServer rts=new requestToServer(AllClasses.VEHICULE,TypeRequest.DELETE,"",param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rts);
			rep=c.getCcs().getLastMessageFromServeur();
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(rep);
			Thread t=new Thread(cmc);
			t.start();
		}
    	}
	
	
	/**
	 * 
	 * @author nassimhammadi laurahollard
	 * Inner class which implements ActionListener.
     * Uses when the user click on find
	 *
	 */
    	 
        /* model.setText("");
         brand.setText("");
         motor.setText("");
         yearv.setText("");
         dateEntry.setText("");
         immatricul.setText("");
         present.setSelected(true);*/
     
	class selectListener implements ActionListener{

		private Repair rp;

		public selectListener(Repair r) {
			this.rp = r;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(prioListObject.getId_car()==1){
	         	String identif=String.valueOf(prioListObject.getId_car());
	 			String rep="";
	 			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
	 			param.put(Parameter.ID, identif);
	 			requestToServer rts=new requestToServer(AllClasses.CAR,TypeRequest.SELECT,"",param);
	 			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
	 			String jsonAuth = jsonRTS.serialize(rts);
	 			rep=c.getCcs().getLastMessageFromServeur();
	 			c.getCcs().setLastMessageToServer(jsonAuth);
	 			checkMessageChange cmc= new checkMessageChange(rep);
	 			Thread t=new Thread(cmc);
	 			t.start();
	         }
			else{
				String identif=String.valueOf(prioListObject.getId_bike());
	 			String rep="";
	 			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
	 			param.put(Parameter.ID, identif);
	 			requestToServer rts=new requestToServer(AllClasses.BIKE,TypeRequest.SELECT,"",param);
	 			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
	 			String jsonAuth = jsonRTS.serialize(rts);
	 			rep=c.getCcs().getLastMessageFromServeur();
	 			c.getCcs().setLastMessageToServer(jsonAuth);
	 			checkMessageChange cmc= new checkMessageChange(rep);
	 			Thread t=new Thread(cmc);
	 			t.start();
			}
		}
	}
	
	/**
	 * 
	 * @author nassimhammadi laurahollard
	 * Inner class which implements ActionListener.
     * Uses when the user click on find
	 *
	 */
	class insertListener implements ActionListener{

		private Repair rp;

		public insertListener(Repair r) {
			this.rp = r;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String identif=id_search.getText();
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			int t_ins;
		    Boolean m_ins = true;
		    Boolean p_ins = false;
		    Vehicule v_ins=null;
		    if(ct1_ins.getState()){
		    	t_ins = 1;
		    } else t_ins = 0;
		    
		    if(cm1_ins.getState()){
		    	m_ins = false;
		    }
		    
		    if(cp1_ins.getState()){
		    	p_ins = true;
		    }
		    if(!ct2_ins.getState()){
		    	v_ins = new Vehicule(im_ins.getText(),t_ins,year_ins.getText(),m_ins,p_ins,brand_ins.getText(),model_ins.getText());
		    }
		    else if(ct2_ins.getState()){
		    	v_ins = new Vehicule(t_ins,year_ins.getText(),m_ins,p_ins,brand_ins.getText(),model_ins.getText());
		    }
		   
			
			Json<Vehicule> myJSon= new Json<Vehicule>(Vehicule.class);
			Json myJSon_ins= new Json(Vehicule.class);
			String v_i= myJSon_ins.serialize(v_ins);
			param.put(Parameter.ID, v_i);
			requestToServer rts=new requestToServer(AllClasses.VEHICULE,TypeRequest.INSERT,v_i,param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rts);
			rep=c.getCcs().getLastMessageFromServeur();
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(rep);
			Thread t=new Thread(cmc);
			t.start();
		}
	}
	
	

	/**
	 * 
	 * @author nassimhammadi laurahollard
	 * Processus attendant une réponse du serveur
	 */
	
	
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
					
					
					if (c.getCcs().getLastMessageFromServeur().equals("update")){
						JOptionPane d2 = new JOptionPane();
						d2.showMessageDialog(jf, "Véhicule mis à jour");
						fin=true;
						
				    	
					}
					else if (part1.equals("select")){
						if(strings.length == 1){
							immatricul.setText("");
							yearv.setText("");
							brand.setText("");
							model.setText("");
							motor.setText("");
							present.setSelected(false);
							dateEntry.setText("");
							JOptionPane d = new JOptionPane();
							d.showMessageDialog(jf, "Véhicule non trouvé");
							fin = true; 
							break;
						}
						else{
						Json <Vehicule> myJSon= new Json<Vehicule>(Vehicule.class);
						Vehicule v = new Vehicule(); 
						try {
							v = myJSon.deSerialize(part2);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(v);
						immatricul.setText(v.getLicense_number());
						//dateEntry.setText(String.valueOf(v.getDateEntry()));
						yearv.setText(String.valueOf(v.getType()));
						brand.setText(v.getBrand());
						model.setText(v.getModel());
						if(v.getIs_electric())
						{
							motor.setText("Electrique");
						}
						else motor.setText("Thermique");
						if(v.getIs_present()){
							present.setSelected(true);
						} 
						else present.setSelected(false);
						
						fin = true;
						
						}}
					else if (part1.equals("insert")){
						JOptionPane d3 = new JOptionPane();
						d3.showMessageDialog(jf, "Véhicule inséré");
						fin = true;
						southRight.removeAll();
				    	
				    	displayAllVehicle();
				    	
					}
					else if (part1.equals("delete")){
						JOptionPane d3 = new JOptionPane();
						d3.showMessageDialog(jf, "Véhicule supprimé");
						fin = true;
						southRight.removeAll();
				    	displayAllVehicle();
					}
					
					else if(part1.equals("selectAllPrio")){
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
					else if(part1.equals("selectCar")){
						jsp.setVisible(false);
						panelEast2.setBorder(new TitledBorder("Liste de(s) réparation(s): "));
						Json <Car> myJSon= new Json<Car>(Car.class);
						 
						try {
							myCar = myJSon.deSerialize(part2);
							System.out.println("All :"+prioList);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 model.setText(myCar.getModel());
				         brand.setText(myCar.getBrand());
				         yearv.setText(myCar.getYear());
				         dateEntry.setText(String.valueOf(myCar.getDateEntry()));
				         immatricul.setText(myCar.getLicense_number());
				        
				         if(myCar.getIs_electric())
						 {
							motor.setText("Electrique");
						 }
						 else motor.setText("Thermique");
				         if(myCar.getIs_present()){
								present.setSelected(true);
						 } 
						 else present.setSelected(false);
						fin =true;
					}
					else {
						
						System.out.println("erreur");
						fin=true;
					}

				}
			}

		}


	}

}