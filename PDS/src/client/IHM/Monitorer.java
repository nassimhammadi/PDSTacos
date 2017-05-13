package client.IHM;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class Monitorer extends JFrame{

	public Monitorer(){
		
	    this.setTitle("Activity of Deposit - Workflow");
	    this.setSize(500, 500);
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
	      
	       c.select("All");  
	    
	       nord.add(statut);
	       nord.add(c);
	      
	       JPanel west = new JPanel(new GridLayout(1,2));
		   west.setBackground(Color.white);
		   west.setPreferredSize(new Dimension(50,50));
		    
	    
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
        panelPrincipal.add(west, BorderLayout.CENTER);
        panelPrincipal.add(south, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    setContentPane(panelPrincipal);
	    
	    this.setVisible(true);
	  
	    
	    
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Monitorer essai = new Monitorer();
	}

}
