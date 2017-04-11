/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.IHM;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import client.json.Json;
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
public class Authentification extends JPanel {
	private JFrame myJFrame;
	private JCheckBox c1, c2;
	private JTextField enterID;
	private JTextField enterPWD;
	private Client c= new Client();
	

	/**
	 * 
	 * @param myJFrame
	 * Constructor of the Authentification class
	 */
	public Authentification(JFrame myJFrame){
		
		c.connect();


		this.myJFrame=myJFrame;
		JPanel pannel = new JPanel();

		pannel.setBackground(Color.WHITE);

		pannel.setLayout(new GridLayout(3,3));
		pannel.setPreferredSize(new Dimension(450,150));
		add(pannel);
		pannel.setBorder(new TitledBorder("Authentifiez-vous")); 
		pannel.setLayout(new BoxLayout(pannel, BoxLayout.Y_AXIS)); 

		JLabel id = new JLabel();
		id.setText("Identifiant :");
		this.enterID = new JTextField("", 20);
		pannel.add(id);
		pannel.add(enterID);
		JLabel mdp = new JLabel();
		mdp.setText("Mot de passe :");
		this.enterPWD = new JTextField("", 20);
		JButton bouton1=new JButton("Se connecter");
		pannel.add(mdp);
		pannel.add(enterPWD);
		pannel.add(bouton1);
		
		enterListener listener=new enterListener(this);
		bouton1.addActionListener(listener);
		
		setVisible(true);
		this.myJFrame.pack();

	}
/**
 * 
 * @author nassimhammadi laurahollard
 * Inner class which implements ActionListener
 * 
 */
	class enterListener implements ActionListener{

		Authentification A;

		/**
		 * 
		 * @param authentification
		 * Constructor
		 */
		public enterListener(Authentification authentification) {
			A=authentification;
		}

		/**
		 * Method which checks if the login and the password match with the database 
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name=enterID.getText();
			String pwd=enterPWD.getText();
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			param.put(Parameter.NAME, name);
			param.put(Parameter.PWD,pwd);
			requestToServer rts=new requestToServer(AllClasses.EMPLOYEE,TypeRequest.LOGIN,"",param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rts);
			rep=c.getCcs().getLastMessageFromServeur();
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(A, rep);
			Thread t=new Thread(cmc);
			t.start();
		}
	}
	
	
	/*
	 * Processus attendant une r�ponse du serveur
	 */
	class checkMessageChange implements Runnable{
		Authentification A;
		String rep="";
		checkMessageChange(Authentification A, String rep){
			this.A=A;
			this.rep=rep;
		}
		@Override
		public void run() {
			boolean fin=false;
			while (!fin){
				if(!rep.equals(c.getCcs().getLastMessageFromServeur())){
					if (c.getCcs().getLastMessageFromServeur().equals("connection ok")){
						System.out.println("bon");
						myJFrame.dispose();
						HomeManager HM= new HomeManager(c);
						
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
















