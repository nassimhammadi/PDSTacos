package client.IHM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import client.IHM.Repair.checkMessageChange;
import client.IHM.Repair.updateListener;
import client.json.Json;
import client.model.ListCar;
import client.model.ListPieces;
import client.model.LogsBreakdownList;

import client.model.priorizedList;
import client.socketClient.AllClasses;
import client.socketClient.Client;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;

public class Pieces extends JFrame{
	
		private Client c;
		private int idc;
		private CheckboxGroup cbg;
		private ListPieces listP;
		private JButton order;
		private JTextField jtf;
		private Thread t_all;
		private JPanel panelSouth;
		private JPanel ps;

		public Pieces(Client cli, int id_client){
			MenuBar menu = new MenuBar(this, cli, id_client);
			this.c = cli;
			this.idc = id_client;
			this.cbg = new CheckboxGroup();
			this.setSize(400, 400);
			this.setLocationRelativeTo(null);
			JPanel panelPrincipal = new JPanel(new BorderLayout());
			//Panel North
			JPanel panelNorth = new JPanel(new FlowLayout());
			jtf = new JTextField("Nombre");
			order = new JButton("Commander");
			//order.addActionListener(new orderp());
			
			panelNorth.add(jtf);
			panelNorth.add(order);
			
			//Panel South
			panelSouth = new JPanel(new FlowLayout());
			panelSouth.setBorder(new TitledBorder("Liste des pieces)"));
			//Main panel
			
			panelPrincipal.add(panelNorth, BorderLayout.NORTH);
			panelPrincipal.add(panelSouth, BorderLayout.CENTER);
			panelPrincipal.add(menu.getMenu(), BorderLayout.SOUTH);
			this.setContentPane(panelPrincipal);
			this.setVisible(true);
			displayAllPieces();
			
		}
		
		public void getAllPieces(){
			String rep = "";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			requestToServer rts=new requestToServer(AllClasses.PIECES,TypeRequest.SELECTB,"",param);
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
		public void displayAllPieces(){
			panelSouth.removeAll();
			getAllPieces();
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
			int taille = 0;
			for(client.model.Pieces p : listP.getListP()){
				taille++;
			}
			System.out.println("Pieces qu'on a : "+taille);
			ps = new JPanel(new GridLayout(taille+1,2));
			ps.setSize(new Dimension(500,500));
			ps.add(new JLabel("Nom pieces"));
			ps.add(new JLabel(""));
			for(client.model.Pieces p : listP.getListP()){
				JLabel j;
	    		String name = p.getName();
	    		int stock = p.getStock();
	            if(stock <=10){
	            	j = new JLabel("- "+name+" "+stock+"/40");
	            	j.setForeground(Color.RED);
	            }
	            else{
	            	j = new JLabel("- "+name+" "+stock+"/40");
	            	
	            }
	            ps.add(j);
	            ps.add(new Checkbox("", cbg, false));
	            
	        }  
			ps.setVisible(true);
			panelSouth.add(ps);
			panelSouth.updateUI();
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
					 if(part1.equals("selectAllPieces")){
						Json <ListPieces> myJSon= new Json<ListPieces>(ListPieces.class);
						 
						try {
							listP = myJSon.deSerialize(part2);
							
							System.out.println("All :"+listP);
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


		
}
