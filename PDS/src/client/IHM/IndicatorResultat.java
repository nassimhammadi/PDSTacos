package client.IHM;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import client.model.Performance;
import client.model.PerformanceList;
import client.socketClient.Client;

public class IndicatorResultat extends JFrame{
	
	private Client c;
	private PerformanceList Res;
	
	
	
	
	
	public IndicatorResultat(Client c, PerformanceList res) {
		this.c = c;
		this.Res = res;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		Object[][] data= new Object[this.Res.getListPerf().size()][3];
		int i=0;
		System.out.println(this.Res.getListPerf().size());
		for(Performance p : this.Res.getListPerf()){
			data[i][0]=i;
			data[i][1]=p.getNbRep();
			data[i][2]=p.getDureeOp();
			i++;
			
		}
		String title[]={"Periode","Nombre de Reparation", "Duree moyenne Reparation"};
		JTable resultat= new JTable(data,title);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(resultat));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle("CSC App - Nassim Hammadi (M)");
		this.setBackground(Color.white);
		setVisible(true);
		this.pack();
	}
	
	
	

	
	
}
