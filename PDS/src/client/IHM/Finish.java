package client.IHM;




import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import client.json.Json;
import client.model.priorizedListObject;
import client.socketClient.AllClasses;
import client.socketClient.Client;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;
import serv.socketServer.Serveur;


/**
 *
 * @author Laura nassim
 */
public class Finish extends JFrame {
	private JFrame myJFrame;
	private JCheckBox c1, c2;
	private JTextField enterID;
	private JTextField enterPWD;
	private Client c;
	private int idb;
	private int ide;
	private priorizedListObject ob;
	private int idc;
	private int id_b;
	private int id_v;

	/**
	 * 
	 * @param myJFrame
	 * Constructor of the Authentification class
	 */
	public Finish(Client cli, int id_b, int id_e, priorizedListObject o, int id_car, int id_bike){
		/*
		 * Lancement du serveur
		 */
		this.c = cli;
		this.ob = o;
		this.idc = id_car;
		this.id_b= id_bike;
		
		this.idb = id_b;
		this.ide = id_e;
		JPanel pannel = new JPanel();
		pannel.setBackground(Color.WHITE);
		pannel.setPreferredSize(new Dimension(450,150));
		add(pannel);
		pannel.setLayout(new BorderLayout()); 
		JButton bouton1=new JButton("Terminer la répararion numéro "+id_b);
		pannel.add(bouton1,BorderLayout.CENTER);
		enterListener listener=new enterListener(this);
		bouton1.addActionListener(listener);
		this.setLocationRelativeTo(null);
		setVisible(true);
		this.pack();
		if(this.idc != 0){
			this.id_v = this.idc;
		}
		else{
			this.id_v = this.id_b;
		}
		System.out.println("id_v"+this.id_v);
	}
/**
 * 
 * @author nassimhammadi laurahollard
 * Inner class which implements ActionListener
 * 
 */
	
	
	
	
	class enterListener implements ActionListener{

		Finish A;

		/**
		 * 
		 * @param authentification
		 * Constructor
		 */
		public enterListener(Finish authentification) {
			A=authentification;
		}

		/**
		 * Method which checks if the login and the password match with the database 
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(idc != 0){
			String id= String.valueOf(idb);
			String id_vehicule =  String.valueOf(id_v);
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			param.put(Parameter.ID, id);
			param.put(Parameter.VEHICULE, id_vehicule);
			requestToServer rts=new requestToServer(AllClasses.REPAIR,TypeRequest.FINISH,"",param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rts);
			rep=c.getCcs().getLastMessageFromServeur();
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(A, rep);
			Thread t=new Thread(cmc);
			t.start();
			dispose();
			Repair r = new Repair(c,ide);
			}
			else{
				String id= String.valueOf(idb);
				String id_vehicule =  String.valueOf(id_v);
				String rep="";
				LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
				param.put(Parameter.ID, id);
				param.put(Parameter.VEHICULE, id_vehicule);
				requestToServer rts=new requestToServer(AllClasses.REPAIR,TypeRequest.FINISHB,"",param);
				Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
				String jsonAuth = jsonRTS.serialize(rts);
				rep=c.getCcs().getLastMessageFromServeur();
				c.getCcs().setLastMessageToServer(jsonAuth);
				checkMessageChange cmc= new checkMessageChange(A, rep);
				Thread t=new Thread(cmc);
				t.start();
				dispose();
				Repair r = new Repair(c,ide);
			}
		}
	}
	
	
	/*
	 * Processus attendant une r?ponse du serveur
	 */
	class checkMessageChange implements Runnable{
		Finish A;
		String rep="";
		checkMessageChange(Finish A, String rep){
			this.A=A;
			this.rep=rep;
		}
		@Override
		public void run() {
			boolean fin=false;
			while (!fin){
				if(!rep.equals(c.getCcs().getLastMessageFromServeur())){
					if (c.getCcs().getLastMessageFromServeur().startsWith("connection ok")){
						System.out.println("bon");
						myJFrame.dispose();
						
						
						fin=true;
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
















