package client.IHM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import client.model.Performance;
import client.model.PerformanceList;
import client.socketClient.Client;






public class MenuPrincipal extends JFrame {
	
	private Client c;
	private int id_client;

	public MenuPrincipal(Client c, int id_client) {
		this.c = c;
		this.id_client= id_client;
		
		JButton traiterVehicule= new JButton("Traiter V�hicule");
		traiterVehicule.addActionListener(new TraiterVehiculeListener());
		JButton reparer= new JButton("R�parer");
		reparer.addActionListener(new Reparer());
		JButton monitorer= new JButton("Monitorer activit� du d�pot");
		monitorer.addActionListener(new Monitorer());
		JButton indicateurPerf= new JButton("Indicateur de Performance");
		indicateurPerf.addActionListener(new Indicateur());
		JButton commanderPiece = new JButton("commanderPiece");
		commanderPiece.addActionListener(new Piece());
		
		GridLayout gl= new GridLayout(5,1);
		gl.setVgap(10);
		
		this.setLayout(gl);
		this.getContentPane().add(traiterVehicule);
		this.getContentPane().add(reparer);
		this.getContentPane().add(monitorer);
		this.getContentPane().add(indicateurPerf);
		this.getContentPane().add(commanderPiece);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setBackground(Color.white);
		setVisible(true);
		this.pack();
	}
	
	 class TraiterVehiculeListener implements ActionListener{
		    
		    public void actionPerformed(ActionEvent arg0) {
		      dispose();
		      HomeManager hm= new HomeManager(c,id_client);
		    }
	
		  }
	 
	 class Reparer implements ActionListener{
		    
		    public void actionPerformed(ActionEvent arg0) {
		      dispose();
		      Repair HM= new Repair(c,id_client);
		    }
	
		  }
	
	 class Monitorer implements ActionListener{
		    

			public void actionPerformed(ActionEvent arg0) {
		      dispose();
		      client.IHM.Monitorer hm = new client.IHM.Monitorer(c, id_client);
		      
		    }
	
		  }
	 
	 class Indicateur implements ActionListener{
		    
		    public void actionPerformed(ActionEvent arg0) {
		      dispose();
		      Indicator HM= new Indicator(c,id_client);
		    }
	
		  }
	 
	 class Piece implements ActionListener{
		    
		    public void actionPerformed(ActionEvent arg0) {
		      dispose();
		      Pieces HM= new Pieces(c,id_client);
		    }
	
		  }
	 
	 
}
