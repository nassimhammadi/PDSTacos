package client.IHM;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import client.IHM.MenuBar;
import client.IHM.Indicator.checkMessageChange;
import client.IHM.Indicator.deleteListener;
import client.IHM.Indicator.insertListener;
import client.IHM.Indicator.selectListener;
import client.IHM.Indicator.updateListener;
import client.json.Json;
import client.model.Vehicule;
import client.socketClient.AllClasses;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;
import java.util.Calendar;
import java.util.Date;

public class Indicator extends JFrame{


    private JTextField id_search;
    private JFrame jf;
    private JTextField id_update;
    private JTextField im_ins;
    private Checkbox ct1_ins;
    private Checkbox ct2_ins;
    private JTextField year_ins;
    private Checkbox cm1_ins;
    private JTextField brand_ins;
    private JTextField model_ins;
    private Checkbox cp1_ins;
    private JTextField im_up;
    private Checkbox ct1_up;
    private JTextField year_up;
    private Checkbox cm1_up;
    private JTextField brand_up;
    private JTextField model_up;
    private JTextField id_up;
    private Checkbox cp1_up;
    private JTextField id_del;
    
	public Indicator() {
		this.jf = this;
        // Add Menu
        MenuBar menu = new MenuBar();
        JPanel panelNord = new JPanel();
        Color c=new Color(1f,0f,0f,.5f);
        panelNord.setBackground(c);
        panelNord.setLayout(new GridLayout(1, 1)); // 1 ligne, 2 colonnes
        panelNord.add(menu.getMenu());
        
        
        JPanel panelWest = new JPanel(new GridLayout(1,4));
        panelWest.setBackground(Color.white);
        
        // JPanel to search vehicle thanks to ID
        JPanel panelWest1 = new JPanel(new GridLayout(6,1));
        panelWest1.setBackground(Color.white);
        panelWest1.setPreferredSize(new Dimension(300, 200));
        panelWest1.setBorder(new TitledBorder("Listes des informations sur le véhicule : "));
        panelWest1.add(new JLabel("Type de Véhicule :"));
        JComboBox<String> vehicletype= new JComboBox<String>();
        vehicletype.addItem("Indifférent");
        vehicletype.addItem("Voiture");
        vehicletype.addItem("Velo");
        panelWest1.add(vehicletype);
        panelWest1.add(new JLabel("Type d'opération :"));
        JComboBox<String> operationtype= new JComboBox<String>();
        operationtype.addItem("Indifférent");
        operationtype.addItem("Pneu");
        operationtype.addItem("Moteur");
        operationtype.addItem("Frein");
        panelWest1.add(operationtype);
        panelWest1.add(new JLabel("Employé :"));
        JComboBox<String> employe= new JComboBox<String>();
        employe.addItem("Indifférent");
        employe.addItem("Franky");
        employe.addItem("Cid");
        employe.addItem("Bulma");
        employe.addItem("Johnny");
        panelWest1.add(employe);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel modeleBegin = new UtilDateModel();
        UtilDateModel modeleEnd = new UtilDateModel();
        JDatePanelImpl datePanelBegin = new JDatePanelImpl(modeleBegin,p);
        JDatePanelImpl datePanelEnd = new JDatePanelImpl(modeleEnd,p);
        JDatePickerImpl dateBegin = new JDatePickerImpl(datePanelBegin,new DateLabelFormatter());
        JDatePickerImpl dateEnd = new JDatePickerImpl(datePanelEnd,new DateLabelFormatter());
        panelWest1.add(new JLabel("Du:")); 
        panelWest1.add(dateBegin);
        panelWest1.add(new JLabel("Au:")); 
        panelWest1.add(dateEnd);
        
      
        JButton search = new JButton("Rechercher");
        
        panelWest1.add(search);
        selectListener sl = new selectListener(this);
        search.addActionListener(sl);
        panelWest.add(panelWest1);
 

        
        JPanel panelSouth =  new JPanel(new GridLayout(9,1));
        panelSouth.setBackground(Color.white);
        panelSouth.setPreferredSize(new Dimension(300,350));
        panelSouth.setBorder(new TitledBorder("Resultat : "));
     //   panelSouth.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		 
      
        
      

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
    
    


    /**
     * 
     * @author nassimhammadi laurahollard
     * Inner class which implements ActionListener.
     * Uses when the user click on update
     *
     */
    class updateListener implements ActionListener{

		private Indicator hm;

		public updateListener(Indicator h) {
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
			Json myJSon_up= new Json(Vehicule.class);
			String v_i= myJSon_up.serialize(v_up);
			param.put(Parameter.ID, id_up.getText());
			
			System.out.println("Param"+Parameter.ID);
			requestToServer rtsu=new requestToServer(AllClasses.VEHICULE,TypeRequest.UPDATE,v_i,param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rtsu);
			System.out.println("Last Message :"+rep);
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

		private Indicator hm;

		public deleteListener(Indicator h) {
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

		private Indicator hm;

		public selectListener(Indicator h) {
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

		private Indicator hm;

		public insertListener(Indicator h) {
			this.hm = h;
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
			checkMessageChange cmc= new checkMessageChange(rep);
			Thread t=new Thread(cmc);
			t.start();
		}
	}
	
	

	/**
	 * 
	 * @author nassimhammadi laurahollard
	 * Processus attendant une rÃ©ponse du serveur
	 */
	class checkMessageChange implements Runnable{
		
		String rep="";
		checkMessageChange(String rep){
			this.rep=rep;
		}
		@Override
		public void run() {
			boolean fin=false;
			
			
			

		}


	}


	

	



public static void main(String args[]){
	System.out.println("test");
	Indicator indicateur = new Indicator();
}
}