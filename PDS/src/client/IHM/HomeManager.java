/*
 * To change this license header, choose License Headers in Project Properties.
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
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import client.json.Json;
import client.model.Vehicule;
import client.socketClient.AllClasses;
import client.socketClient.Client;
import client.socketClient.Parameter;
import client.socketClient.TypeRequest;
import client.socketClient.requestToServer;
import serv.socketServer.Serveur;

/*
 *
 * @author hollard hammadi
*/
public class HomeManager extends JFrame{
    
	
	private Client c= new Client();
    private CheckboxGroup cbg_type = new CheckboxGroup();
    private CheckboxGroup cbg_motorisation = new CheckboxGroup();
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
    private JTextField brand_ins;
    private JTextField model_ins;
    private JTextField id_ins;
    private Checkbox cp1_ins;
    private Checkbox cp2_ins;
    private JTextField id_del;
    
    
    public HomeManager(){
      
    	this.jf = this;
		c.connect();
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
        JPanel panelWest2 = new JPanel(new GridLayout(5,1));
        panelWest2.setBackground(Color.white);
        panelWest2.setPreferredSize(new Dimension(300, 200));
        panelWest2.setBorder(new TitledBorder("Mettre à jour présence véhicule : "));
        panelWest2.add(new JLabel("Identifiant du véhicule :"));
        this.setId_update(new JTextField());
        panelWest2.add(getId_update());
        panelWest2.add(new JLabel("Présence dans le dépôt :"));
        JPanel panelEast25 = new JPanel(new GridLayout(1,2));
        panelEast25.add(cb_update_presence);
        panelEast25.add(cb_update_no_presence);
        panelWest2.add(panelEast25);
        JButton update_btn = new JButton("Mettre à jour");
        updateListener upl = new updateListener(this);
        update_btn.addActionListener(upl);
        panelWest2.add(update_btn);
        
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
        JPanel panelEast2 = new JPanel(new GridLayout(9,2));
        panelEast2.setBackground(Color.white);
        panelEast2.setPreferredSize(new Dimension(300, 200));
        panelEast2.setBorder(new TitledBorder("Ajouter un véhicule : "));
        //panelEast2.add(new JLabel("Identifiant :"));
        id_ins = new JTextField();
       // panelEast2.add(id_ins);
        panelEast2.add(new JLabel("Immatriculation :"));
        im_ins = new JTextField();
        panelEast2.add(im_ins);
        panelEast2.add(new JLabel("Type de véhicule :"));
        JPanel panelEast22 = new JPanel(new GridLayout(1,2));
        ct1_ins = new Checkbox("Voiture",cbg_type,true);
        ct2_ins = new Checkbox("Vélo",cbg_type,false);
        panelEast22.add(ct1_ins);
        panelEast22.add(ct2_ins);
        panelEast2.add(panelEast22);
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
       // BoxLayout layoutSouthLeft = new BoxLayout(southLeft, BoxLayout.Y_AXIS);
        //southLeft.setLayout(layoutSouthLeft);

        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Immatriculation : "));
        immatricul = new JLabel();
        
        southLeft.add(immatricul);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Type de véhicule : "));
        type = new JLabel();
        
        southLeft.add(type);     
        southLeft.add(new JLabel("Année de mise en circulation : "));
        yearv = new JLabel();
        
        southLeft.add(yearv);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Motorisation : "));
        motor = new JLabel();
        
        southLeft.add(motor);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Présence dans le dépôt : "));
        present = new JRadioButton("Présent");
        southLeft.add(present);
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

    

      /*  southRight.add(Box.createGlue());
        int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED; 
        JScrollPane jsp=new JScrollPane(southRight,v,h);
        jsp.setBorder(new TitledBorder("Historique des réparations : "));
        BoxLayout layoutSouthRight = new BoxLayout(southRight, BoxLayout.Y_AXIS);
        southRight.setLayout(layoutSouthRight);

        for(int i=0; i<30; i++){
            southRight.add(new JLabel("      - Test histo numero "+i));
            southRight.add(Box.createVerticalGlue());
        }  
        panelSouth.add(jsp);*/
        
      

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

	public JTextField getId_update() {
		return id_update;
	}

	public void setId_update(JTextField id_update) {
		this.id_update = id_update;
	}
    
    


    
    class updateListener implements ActionListener{

		private HomeManager hm;

		public updateListener(HomeManager h) {
			this.hm = h;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String identif=hm.getId_update().getText();
			boolean jckb = cb_update_presence.getState();
			String bool;
			if(jckb){
				bool = "true";
			}
			else{ bool = "false";}
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			param.put(Parameter.ID, identif);
			param.put(Parameter.PRESENCE, bool);
			requestToServer rts=new requestToServer(AllClasses.VEHICULE,TypeRequest.UPDATE,"",param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rts);
			rep=c.getCcs().getLastMessageFromServeur();
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(rep);
			Thread t=new Thread(cmc);
			t.start();
		}
	}
    
    
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
	
	class insertListener implements ActionListener{

		private HomeManager hm;

		public insertListener(HomeManager h) {
			this.hm = h;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String identif=id_search.getText();
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			int t_ins;
		    Boolean m_ins = false;
		    Boolean p_ins = false;
		    if(ct1_ins.getState()){
		    	t_ins = 1;
		    } else t_ins = 0;
		    
		    if(cm1_ins.getState()){
		    	m_ins = true;
		    }
		    
		    if(cp1_ins.getState()){
		    	p_ins = true;
		    }
			Vehicule v_ins = new Vehicule(im_ins.getText(),t_ins,year_ins.getText(),m_ins,p_ins,brand_ins.getText(),model_ins.getText());
			Json<Vehicule> myJSon= new Json<Vehicule>(Vehicule.class);
			Json myJSon_ins= new Json(Vehicule.class);
			String v_i= myJSon_ins.serialize(v_ins);
			param.put(Parameter.ID, v_i);
			requestToServer rts=new requestToServer(AllClasses.VEHICULE,TypeRequest.INSERT,v_i,param);
			Json<requestToServer>  jsonRTS= new Json<requestToServer>(requestToServer.class);
			String jsonAuth = jsonRTS.serialize(rts);
			rep=c.getCcs().getLastMessageFromServeur();
			c.getCcs().setLastMessageToServer(jsonAuth);
			checkMessageChange cmc= new checkMessageChange(rep);
			Thread t=new Thread(cmc);
			t.start();
		}
	}
	
	

	/*
	 * Processus attendant une r�ponse du serveur
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
						d2.showMessageDialog(jf, "Véhicule mit à jour");
						
						
						fin=true;
					}
					else if (part1.equals("select")){
						if(strings.length == 1){
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
						yearv.setText(v.getYear());
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
						if(v.getType() == 1){
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
						
					}
					else if (part1.equals("delete")){
						JOptionPane d3 = new JOptionPane();
						d3.showMessageDialog(jf, "Véhicule supprimé");
						fin = true;
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

        
   
        
        
    
    
 
    
    
    
  
    




