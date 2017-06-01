/*
g * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.Request;

import client.json.Json;
import client.model.Bike;
import client.model.Breakdown;
import client.model.BreakdownList;
import client.model.Car;
import client.model.ListCar;
import client.model.ListVehicle;
import client.model.Vehicule;
import client.socketClient.AllClasses;
import client.socketClient.Client;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;
import serv.model.ListBike;
import serv.socketServer.Serveur;

/*
 *
 * @author hollard hammadi
 */
public class HomeManager extends JFrame{


	private Client c;
	private Integer id_client;
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
	private JLabel type;
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
	private JTextField motif_ins;
	private JComboBox<String> breakdown_ins;
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
	private ListBike listBike;
	private ListCar listC;
	private BreakdownList listB;
	private JPanel southRight;
	private Thread t_all;
	

	/**
	 * 
	 * @param cli
	 * Constructor which create the manager view
	 */



	public HomeManager(Client cli, int id_client){

		this.c=cli;
		this.id_client=id_client;
		this.jf = this;
		// Add Menu
		MenuBar menu = new MenuBar();
		JPanel panelNord = new JPanel();
		Color c=new Color(1f,0f,0f,.5f);
		panelNord.setBackground(c);
		panelNord.setLayout(new GridLayout(1, 1)); // 1 ligne, 2 colonnes
		panelNord.add(menu.getMenu());
		System.out.println(listV);

		JPanel panelWest = new JPanel(new GridLayout(1,4));
		panelWest.setBackground(Color.white);

		// JPanel to search vehicle thanks to ID
		JPanel panelWest1 = new JPanel(new GridLayout(5,1));
		panelWest1.setBackground(Color.white);
		panelWest1.setPreferredSize(new Dimension(300, 200));
		panelWest1.setBorder(new TitledBorder("Listes des informations sur le véhicule : "));
		panelWest1.add(new JLabel("Identifiant du véhicule :"));
		id_search = new JTextField();
		JButton search = new JButton("Rechercher");
		panelWest1.add(id_search);
		selectListener sl = new selectListener(this);
		search.addActionListener(sl);
		panelWest1.add(search);

		// JPanel to update vehicle year
		JPanel panelWest2 = new JPanel(new GridLayout(10,2));
		panelWest2.setBackground(Color.white);
		panelWest2.setPreferredSize(new Dimension(300, 200));
		panelWest2.setBorder(new TitledBorder("Mettre à jour un véhicule : "));
		panelWest2.add(new JLabel("Identifiant :"));
		id_up = new JTextField();
		panelWest2.add(id_up);
		panelWest2.add(new JLabel("Type de véhicule :"));
		JPanel panelWest22 = new JPanel(new GridLayout(1,2));
		ct1_up = new Checkbox("Voiture",cbg_type_up,true);
		ct2_up = new Checkbox("Vélo",cbg_type_up,false);
		ct2_up.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {    

				im_j_up.setVisible(false);
				im_up.setVisible(false);
			}
		});
		ct1_up.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {    

				im_j_up.setVisible(true);
				im_up.setVisible(true);
			}
		});

		panelWest22.add(ct1_up);
		panelWest22.add(ct2_up);
		panelWest2.add(panelWest22);
		im_j_up = new JLabel("Immatriculation :");
		panelWest2.add(im_j_up);
		im_up = new JTextField();
		panelWest2.add(im_up);
		panelWest2.add(new JLabel("Année du véhicule :"));
		year_up = new JTextField();
		panelWest2.add(year_up);
		panelWest2.add(new JLabel("Motorisation :"));
		JPanel panelWest3 = new JPanel(new GridLayout(1,2));
		cm1_up = new Checkbox("Thermique",cbg_motorisation_up,true);
		cm2_up = new Checkbox("Elec",cbg_motorisation_up,false);
		panelWest3.add(cm1_up);
		panelWest3.add(cm2_up);
		panelWest2.add(panelWest3);
		panelWest2.add(new JLabel("Marque :"));
		brand_up = new JTextField();
		panelWest2.add(brand_up);
		panelWest2.add(new JLabel("Modèle :"));
		model_up = new JTextField();
		panelWest2.add(model_up);
		panelWest2.add(new JLabel("Présence dans le dépôt :"));
		JPanel panelWest4 = new JPanel(new GridLayout(1,2));
		cp1_up = new Checkbox("Oui",cbg_update_presence,true);
		cp2_up = new Checkbox("Non",cbg_update_presence,false);
		panelWest4.add(cp1_up);
		panelWest4.add(cp2_up);
		panelWest2.add(panelWest4);
		JButton update_button = new JButton("Ajouter");
		update_button.setBackground(Color.white);
		updateListener upl = new updateListener(this);
		update_button.addActionListener(upl);
		panelWest2.add(update_button);

		// JPanel to delete vehicle
		JPanel panelEast1 = new JPanel(new GridLayout(5,1));
		panelEast1.setBackground(Color.white);
		panelEast1.setPreferredSize(new Dimension(300, 200));
		panelEast1.setBorder(new TitledBorder("Supprimer un véhicule de la base : "));
		panelEast1.add(new JLabel("Identifiant du véhicule :"));
		id_del = new JTextField();
		JButton delete_btn = new JButton("Supprimer");
		deleteListener dpl = new deleteListener(this);
		delete_btn.addActionListener(dpl);
		panelEast1.add(id_del);
		panelEast1.add(delete_btn);

		// Jpanel to add a vehicle
		JPanel panelEast2 = new JPanel(new GridLayout(10,2));
		panelEast2.setBackground(Color.white);
		panelEast2.setPreferredSize(new Dimension(300, 200));
		panelEast2.setBorder(new TitledBorder("Ajouter un véhicule : "));
		/* panelEast2.add(new JLabel("Identifiant :"));
        id_ins = new JTextField();
        panelEast2.add(id_ins);*/
		panelEast2.add(new JLabel("Type de véhicule :"));
		JPanel panelEast22 = new JPanel(new GridLayout(1,2));
		ct1_ins = new Checkbox("Voiture",cbg_type,true);
		ct2_ins = new Checkbox("Vélo",cbg_type,false);
		ct2_ins.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {    

				im_j_ins.setVisible(false);
				im_ins.setVisible(false);
			}
		});
		ct1_ins.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {    

				im_j_ins.setVisible(true);
				im_ins.setVisible(true);
			}
		});
		panelEast22.add(ct1_ins);
		panelEast22.add(ct2_ins);
		panelEast2.add(panelEast22);
		im_j_ins =new JLabel("Immatriculation :");
		panelEast2.add(im_j_ins);
		im_ins = new JTextField();
		panelEast2.add(im_ins);
		panelEast2.add(new JLabel("Année du véhicule :"));
		year_ins = new JTextField();
		panelEast2.add(year_ins);
		panelEast2.add(new JLabel("Motorisation :"));
		JPanel panelEast3 = new JPanel(new GridLayout(1,2));
		cm1_ins = new Checkbox("Thermique",cbg_motorisation,true);
		cm2_ins = new Checkbox("Elec",cbg_motorisation,false);
		panelEast3.add(cm1_ins);
		panelEast3.add(cm2_ins);
		panelEast2.add(panelEast3);
		panelEast2.add(new JLabel("Marque :"));
		brand_ins = new JTextField();
		panelEast2.add(brand_ins);
		panelEast2.add(new JLabel("Modèle :"));
		model_ins = new JTextField();
		panelEast2.add(model_ins);
		panelEast2.add(new JLabel("Présence dans le dépôt :"));
		JPanel panelEast4 = new JPanel(new GridLayout(1,2));
		cp1_ins = new Checkbox("Oui",cbg_insert_presence,true);
		cp2_ins = new Checkbox("Non",cbg_insert_presence,false);
		panelEast4.add(cp1_ins);
		panelEast4.add(cp2_ins);
		panelEast2.add(panelEast4);
		panelEast2.add(new JLabel("Motif :"));
		motif_ins = new JTextField();
		panelEast2.add(motif_ins);
		panelEast2.add(new JLabel("Breakdown :"));
		breakdown_ins=new JComboBox<String>();
		panelEast2.add(breakdown_ins);

		JButton insert_button = new JButton("Ajouter");
		insert_button.setBackground(Color.white);
		insertListener ipl = new insertListener(this);
		insert_button.addActionListener(ipl);
		panelEast2.add(insert_button);
		panelWest.add(panelWest1);
		panelWest.add(panelWest2);
		panelWest.add(panelEast1);
		panelWest.add(panelEast2);


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
		southLeft.add(new JLabel("Type de véhicule : "));
		type = new JLabel();
		southLeft.add(type);


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
		JScrollPane jsp=new JScrollPane(southRight,v,h);
		jsp.setBorder(new TitledBorder("Historique des réparations : "));
		BoxLayout layoutSouthRight = new BoxLayout(southRight, BoxLayout.Y_AXIS);
		southRight.setLayout(layoutSouthRight);

		displayAllVehicle();
		displayAllBreakdown();
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
		getAllCar();
		getAllBike();
	}
	
	public void getAllCar(){
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
	public void getAllBike(){
		String identif ="";
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		requestToServer rts=new requestToServer(AllClasses.BIKE,TypeRequest.SELECT,"",param);
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

	public void getAllBreakdown(){
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		requestToServer rts=new requestToServer(AllClasses.BREAKDOWN,TypeRequest.SELECT,"",param);
		Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
		String jsonAuth = jsonRTS.serialize(rts);
		rep=c.getCcs().getLastMessageFromServeur();
		c.getCcs().setLastMessageToServer(jsonAuth);
		checkMessageChange cmc= new checkMessageChange(rep);
		t_all=new Thread(cmc);
		t_all.start();
	}

	public void displayAllBreakdown(){
		getAllBreakdown();
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
		for(Breakdown b : listB.getListBreakdown()){
			breakdown_ins.addItem(b.toString());
			setVisible(true);
		}
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
		southRight.add(new JLabel("ID      Immatriculation      Modèle Durée"));

		for(Car c : listC.getL_b()){
			southRight.add(new JLabel(c.getId()+"               "+c.getLicense_number()+"                    "+c.getModel()+"           "+c.getDuration()));
		}  
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

		private HomeManager hm;

		public updateListener(HomeManager h) {
			this.hm = h;
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
			Json<Vehicule> myJSon_up= new Json<Vehicule>(Vehicule.class);
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

		private HomeManager hm;

		public deleteListener(HomeManager h) {
			this.hm = h;
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
	class selectListener implements ActionListener{

		private HomeManager hm;

		public selectListener(HomeManager h) {
			this.hm = h;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String identif=id_search.getText();
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			param.put(Parameter.ID, identif);
			requestToServer rts=new requestToServer(AllClasses.VEHICULE,TypeRequest.SELECT,"",param);
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
	class insertListener implements ActionListener{

		private HomeManager hm;

		public insertListener(HomeManager h) {
			this.hm = h;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String identif=id_search.getText();
			String rep="";
			String motif=motif_ins.getText();
			String breakdownStr= (String) breakdown_ins.getSelectedItem();
			String[] id_breakdownTab=new String[2];
			id_breakdownTab=breakdownStr.split("\\.");
			String id_breakdown=id_breakdownTab[0];
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			int t_ins;
			Boolean m_ins = true;
			Boolean p_ins = false;
			Bike bike_ins=null;
			Car car_ins=null;
			requestToServer rts=null;
			// voiture
			if(ct1_ins.getState()){
				t_ins = 1;
			} else t_ins = 0;
			//motorisation
			if(cm1_ins.getState()){
				m_ins = false;
			}
			//présence
			if(cp1_ins.getState()){
				p_ins = true;
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			if(!ct2_ins.getState()){
				car_ins = new Car(im_ins.getText(),year_ins.getText(),m_ins,p_ins,brand_ins.getText(),model_ins.getText(), timestamp);
				Json<Car> myJSon= new Json<Car>(Car.class);
				Json<Car> myJSon_ins= new Json<Car>(Car.class);
				String v_i= myJSon_ins.serialize(car_ins);
				param.put(Parameter.ID, this.hm.id_client.toString());
				param.put(Parameter.ID_BREAKDOWN, id_breakdown);
				param.put(Parameter.MOTIF_BREAKDOWN, motif);
				 rts=new requestToServer(AllClasses.CAR,TypeRequest.INSERT,v_i,param);
			}
			else if(ct2_ins.getState()){
				bike_ins = new Bike(t_ins,year_ins.getText(),m_ins,p_ins,brand_ins.getText(),model_ins.getText(), timestamp);
				Json<Bike> myJSon= new Json<Bike>(Bike.class);
				Json<Bike> myJSon_ins= new Json<Bike>(Bike.class);
				String v_i= myJSon_ins.serialize(bike_ins);
				param.put(Parameter.ID, this.hm.id_client.toString());
				param.put(Parameter.ID_BREAKDOWN, id_breakdown);
				param.put(Parameter.MOTIF_BREAKDOWN, motif);
				 rts=new requestToServer(AllClasses.VEHICULE,TypeRequest.INSERT,v_i,param);
			}



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
							type.setText("");
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
							if(Integer.parseInt(v.getYear()) == 1){
								type.setText("Voiture");
							}
							else type.setText("Vélo");
						}
						fin = true;
					}
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
					}else if(part1.equals("selectAllBike")){
						Json <ListBike> myJSon= new Json<ListBike>(ListBike.class);
			
						try {
							listBike = myJSon.deSerialize(part2);
							System.out.println("All :"+listBike);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						fin =true;
					}
					else if(part1.equals("selectAllBreakDown")){
						Json <BreakdownList> myJSon= new Json<BreakdownList>(BreakdownList.class);

						try {
							listB = myJSon.deSerialize(part2);

							System.out.println("All :"+listB);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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

















