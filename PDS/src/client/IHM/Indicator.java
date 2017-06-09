package client.IHM;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
import client.json.Json;
import client.IHM.MenuBar;
import client.IHM.Indicator.checkMessageChange;
import client.IHM.Indicator.selectListener;
import client.json.Json;
import client.model.Bike;
import client.model.Breakdown;
import client.model.BreakdownList;
import client.model.Car;
import client.model.ListCar;
import client.model.ListVehicle;
import client.model.Performance;
import client.model.PerformanceList;
import client.model.User;
import client.model.Vehicule;
import client.socketClient.AllClasses;
import client.socketClient.Client;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;
import client.model.UserList;

import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

public class Indicator extends JFrame{


	private JTextField id_search;
	private JTextField id_update;
	private JTextField im_up;
	private Checkbox ct1_up;
	private JTextField year_up;
	private Checkbox cm1_up;
	private JTextField brand_up;
	private JTextField model_up;
	private JTextField id_up;
	private Checkbox cp1_up;
	private JTextField id_del;
	private JComboBox<String> operationtype;
	private JComboBox<String> employeelist;
	private Thread t_all;
	private Client c;
	private BreakdownList listB;
	private UserList listU;
	private JDatePickerImpl dateBegin;
	private JDatePickerImpl dateEnd;
	private JComboBox<String> vehicletype;
	private PerformanceList nbRep;
	private ButtonGroup bg;
	private JRadioButton semaine;
	private JRadioButton mois;
	private JRadioButton annee;
	private JPanel panelSouth;
	private int id_client;


	public Indicator(Client client,int id_client) {
		this.c = client;
		this.id_client = id_client;
		
		// Add Menu
		MenuBar menu = new MenuBar();
		JPanel panelNord = new JPanel();
		Color c=new Color(1f,0f,0f,.5f);
		panelNord.setBackground(c);
		panelNord.setLayout(new GridLayout(1, 1)); // 1 ligne, 2 colonnes
		panelNord.add(menu.getMenu());


		JPanel panelButton = new JPanel(new GridLayout(1,2));
		panelButton.setBackground(Color.white);

		// JPanel to search vehicle thanks to ID
		GridLayout gl= new GridLayout(7,1);
		gl.setVgap(5);
		gl.setHgap(5);
		
		JPanel panelWest1 = new JPanel(gl);
		panelWest1.setBackground(Color.white);
		panelWest1.setPreferredSize(new Dimension(350, 300));
		panelWest1.setBorder(new TitledBorder("Filtres de recherche : "));
		panelWest1.add(new JLabel("Type de Vehicule :"));
		this.vehicletype= new JComboBox<String>();
		vehicletype.addItem("Indifferent");
		vehicletype.addItem("Voiture");
		vehicletype.addItem("Velo");
		panelWest1.add(vehicletype);
		panelWest1.add(new JLabel("Type d'operation :"));
		this.operationtype= new JComboBox<String>();
		this.operationtype.addItem("Indifferent");
		panelWest1.add(operationtype);
		panelWest1.add(new JLabel("Employe :"));
		this.employeelist= new JComboBox<String>();
		this.employeelist.addItem("Indifferent");
		panelWest1.add(employeelist);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		SqlDateModel modeleBegin = new SqlDateModel();
		SqlDateModel modeleEnd = new SqlDateModel();
		JDatePanelImpl datePanelBegin = new JDatePanelImpl(modeleBegin,p);
		JDatePanelImpl datePanelEnd = new JDatePanelImpl(modeleEnd,p);
		this.dateBegin = new JDatePickerImpl(datePanelBegin,new DateLabelFormatter());
		this.dateEnd = new JDatePickerImpl(datePanelEnd,new DateLabelFormatter());
		panelWest1.add(new JLabel("Du:")); 
		panelWest1.add(dateBegin);
		panelWest1.add(new JLabel("Au:")); 
		panelWest1.add(dateEnd);
		this.bg= new ButtonGroup();
		this.semaine= new JRadioButton("Semaine");
		this.mois= new JRadioButton("Mois");
		this.annee= new JRadioButton("Annee");
		this.bg.add(semaine);
		this.bg.add(mois);
		this.bg.add(annee);
		panelButton.add(semaine);
		panelButton.add(mois);
		panelButton.add(annee);
		semaine.setSelected(true);
		
		
		panelWest1.add(new JLabel("Periode :"));
		panelWest1.add(panelButton);

		JButton search = new JButton("Rechercher");
		panelWest1.add(search);
		
		selectListener sl = new selectListener(this);
		search.addActionListener(sl);

		JButton retour = new JButton("Retour");
		retour.addActionListener(new Retour());
		panelWest1.add(retour);
		
		

	/*	panelSouth =  new JPanel(new GridLayout(2,1));
		panelSouth.setBackground(Color.white);
		panelSouth.setPreferredSize(new Dimension(300,350));
		panelSouth.setBorder(new TitledBorder("Resultat : "));*/
		
		
		
		//   panelSouth.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);






		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.white);
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(panelNord, BorderLayout.NORTH);
		panelPrincipal.add(panelWest1, BorderLayout.CENTER);
		/*panelPrincipal.add(panelSouth, BorderLayout.SOUTH);*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 


		setContentPane(panelPrincipal);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle("CSC App - Nassim Hammadi (M)");
		this.setBackground(Color.white);
		setVisible(true);
		displayAllEmployee();      displayAllBreakdown();
		

	}
	
	
	
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
	
	

	public void getAllEmployee(){
		String rep = "";
		LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
		requestToServer rts=new requestToServer(AllClasses.EMPLOYEE,TypeRequest.SELECT,"",param);
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

	public void displayAllEmployee(){
		getAllEmployee();
		
		for(User u : listU.getListUser()){
			employeelist.addItem(u.toStringLabel());
			setVisible(true);
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
		try {
			t_all.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayAllBreakdown(){
		getAllBreakdown();
		
		for(Breakdown b : listB.getListBreakdown()){
			operationtype.addItem(b.toString());
			setVisible(true);
		}
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


	class Retour implements ActionListener{
		
		 public void actionPerformed(ActionEvent arg0) {
		      dispose();
		      MenuPrincipal HM= new MenuPrincipal(c,id_client);
		    }
	}

	/**
	 * 
	 * @author nassimhammadi laurahollard
	 * Inner class which implements ActionListener.
	 * Uses when the user click on update
	 *
	 */
	class selectListener implements ActionListener{

		private Indicator hm;

		public selectListener(Indicator h) {
			this.hm = h;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
		//	String identif=id_search.getText();
			
			String rep="";
			java.sql.Date dateBegin_r = (java.sql.Date) dateBegin.getModel().getValue();
			java.sql.Date dateEnd_r = (java.sql.Date) dateEnd.getModel().getValue();
			if (dateBegin_r.compareTo(dateEnd_r) > 0){
				JOptionPane.showMessageDialog(null, "Date Begin must be before Date End !","Warning",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String vehicletype_r= vehicletype.getSelectedItem().toString();
			String [] strings= operationtype.getSelectedItem().toString().split(". ");
			String operationtype_r= strings[0];
			strings= employeelist.getSelectedItem().toString().split(". ");
			String employee_r= strings[0];
			String periode_r= hm.getSelectedButtonText(bg);
			
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			param.put(Parameter.IND_VEHICLETYPE,vehicletype_r);
			param.put(Parameter.IND_IdOPE,operationtype_r);
			param.put(Parameter.IND_IdEMP,employee_r);
			param.put(Parameter.IND_DATEBEGIN,dateBegin_r.toString());
			param.put(Parameter.IND_DATEEND,dateEnd_r.toString());
			param.put(Parameter.IND_Periode,periode_r);
			requestToServer rts=new requestToServer(AllClasses.LOGS_BREAKDOWN,TypeRequest.IND_SELECTNBREP,"",param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rts);
			rep=c.getCcs().getLastMessageFromServeur();
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(rep);
			Thread t=new Thread(cmc);
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
			IndicatorResultat ir= new IndicatorResultat(c,id_client,nbRep);
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
					if (part1.equals("selectAllUser")){
						Json<UserList>  jsonUser= new Json<UserList>(UserList.class);
						try {
							listU = jsonUser.deSerialize(part2);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						fin=true;

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
					else if (part1.equals("selectNbRep")){
						Json<PerformanceList> jsonNbRep= new Json<PerformanceList>(PerformanceList.class);
						
						try{
							nbRep=jsonNbRep.deSerialize(part2);
						}catch (IOException e) {
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