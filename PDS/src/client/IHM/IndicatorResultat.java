package client.IHM;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class IndicatorResultat extends JFrame{
	
	private Client c;
	private int id_client;
	private PerformanceList Res;
	
	
	
	
	
	public IndicatorResultat(Client c, int id_client,PerformanceList res) {
		this.c = c;
		this.id_client= id_client;
		this.Res = res;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		Object[][] data= new Object[this.Res.getListPerf().size()][3];
		int i=0;
		System.out.println(this.Res.getListPerf().size());
		for(Performance p : this.Res.getListPerf()){
			data[i][0]=i;
			data[i][1]=p.getNbRep();
			data[i][2]=p.getDureeOp()/60;
			i++;
			
		}
		String title[]={"Periode","Nombre de Reparation", "Duree moyenne Reparation en min"};
		JTable resultat= new JTable(data,title);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(resultat));
		
		JButton retour = new JButton("Retour");
		retour.addActionListener(new Retour());
		
		this.getContentPane().add(retour,BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.getContentPane().setPreferredSize(new Dimension(500,500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle("CSC App - Nassim Hammadi (M)");
		this.setBackground(Color.white);
		setVisible(true);
		this.pack();
	}
	
	class Retour implements ActionListener{
		
		 public void actionPerformed(ActionEvent arg0) {
		      dispose();
		      Indicator HM= new Indicator(c,id_client);
		    }
	}
	

	
	
}
