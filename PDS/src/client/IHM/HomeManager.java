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
import java.util.LinkedHashMap;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import client.json.Json;
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
    
	private Serveur s=new Serveur();
	private Client c= new Client();
    private CheckboxGroup cbg_type = new CheckboxGroup();
    private CheckboxGroup cbg_motorisation = new CheckboxGroup();
    private CheckboxGroup cbg_insert_presence = new CheckboxGroup();
    private CheckboxGroup cbg_update_presence = new CheckboxGroup();
    Checkbox cb_update_presence = new Checkbox("Oui",cbg_update_presence,true);
    Checkbox cb_update_no_presence = new Checkbox("Non",cbg_update_presence,false);
    
    private JTextField id_update;
    
    public HomeManager(){
      
    	s.launch();
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
        JTextField id = new JTextField();
        JButton search = new JButton("Rechercher");
        panelWest1.add(id);
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
        JTextField id3 = new JTextField();
        JButton search3 = new JButton("Supprimer");
        panelEast1.add(id3);
        panelEast1.add(search3);
        
        // Jpanel to add a vehicle
        JPanel panelEast2 = new JPanel(new GridLayout(9,2));
        panelEast2.setBackground(Color.white);
        panelEast2.setPreferredSize(new Dimension(300, 200));
        panelEast2.setBorder(new TitledBorder("Ajouter un véhicule : "));
        panelEast2.add(new JLabel("Identifiant :"));
        JTextField id5 = new JTextField();
        panelEast2.add(id5);
        panelEast2.add(new JLabel("Immatriculation :"));
        JTextField id6 = new JTextField();
        panelEast2.add(id6);
        panelEast2.add(new JLabel("Type de véhicule :"));
        JPanel panelEast22 = new JPanel(new GridLayout(1,2));
        panelEast22.add(new Checkbox("Voiture",cbg_type,true));
        panelEast22.add(new Checkbox("Vélo",cbg_type,false));
        panelEast2.add(panelEast22);
        panelEast2.add(new JLabel("Année du véhicule :"));
        JTextField id8 = new JTextField();
        panelEast2.add(id8);
        panelEast2.add(new JLabel("Motorisation :"));
        JPanel panelEast3 = new JPanel(new GridLayout(1,2));
        panelEast3.add(new Checkbox("Thermique",cbg_motorisation,true));
        panelEast3.add(new Checkbox("Elec",cbg_motorisation,false));
        panelEast2.add(panelEast3);
        panelEast2.add(new JLabel("Marque :"));
        JTextField id10 = new JTextField();
        panelEast2.add(id10);
        panelEast2.add(new JLabel("Modèle :"));
        JTextField id11 = new JTextField();
        panelEast2.add(id11);
        panelEast2.add(new JLabel("Présence dans le dépôt :"));
        JPanel panelEast4 = new JPanel(new GridLayout(1,2));
        panelEast4.add(new Checkbox("Oui",cbg_insert_presence,true));
        panelEast4.add(new Checkbox("Non",cbg_insert_presence,false));
        panelEast2.add(panelEast4);
        JButton search5 = new JButton("Ajouter");
        search5.setBackground(Color.white);
        panelEast2.add(search5);
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
        JTextField immatricul = new JTextField();
        immatricul.setColumns(10);
        southLeft.add(immatricul);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Type de véhicule : "));
        JTextField type = new JTextField();
        type.setColumns(10);
        southLeft.add(type);     
        southLeft.add(new JLabel("Année de mise en circulation : "));
        JTextField annee = new JTextField();
        type.setColumns(10);
        southLeft.add(annee);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Motorisation : "));
        JTextField motor = new JTextField();
        type.setColumns(10);
        southLeft.add(motor);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Présence dans le dépôt : "));
        JRadioButton present = new JRadioButton("Présent");
        southLeft.add(present);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Marque : "));
        JTextField marque = new JTextField();
        type.setColumns(10);
        southLeft.add(marque);
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Modèle : "));
        JTextField model = new JTextField();
        type.setColumns(10);
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
			String rep="";
			LinkedHashMap<Parameter,String> param=new LinkedHashMap<>();
			param.put(Parameter.ID, identif);
			
			requestToServer rts=new requestToServer("vehicle",TypeRequest.UPDATE,"",param);
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
					if (c.getCcs().getLastMessageFromServeur().equals("update")){
						System.out.println("Mise à jour effectué");
						
						
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
        
   
        
        
    
    
 
    
    
    
  
    




