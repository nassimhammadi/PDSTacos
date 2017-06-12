/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import client.socketClient.Client;

/**
 *
 * @author laura nassimhammadi
 */
public class MenuBar {
    private JMenuBar jmenu = new JMenuBar();
    private JButton deconnexion = new JButton("Retour au menu principal");
    private JMenuItem sd = new JMenuItem("Se déconnecter");
    private JMenu management = new JMenu("Gestion");
    private JMenu repairs = new JMenu("Réparation");
    private JMenu information = new JMenu("Informations");
    private JMenu references = new JMenu("Référentiels");
    private JMenuItem ref_car =  new JMenuItem("Voitures");
    private JMenuItem ref_bike =  new JMenuItem("Vélos");
    private JMenuItem ref_component =  new JMenuItem("Pièces");
    private JMenuItem ref_employees =  new JMenuItem("Employés");
    private JMenuItem stock = new JMenuItem("Stock");
    private JMenu history = new JMenu("Historiques");
    private JMenuItem histo_repairs = new JMenuItem("Pannes");
    private JMenuItem histo_deposit = new JMenuItem("Dépôt");
    private JMenuItem performance = new JMenuItem("Performances");
    private JMenuItem support = new JMenuItem("Prise en charge");
    private JMenuItem repairing = new JMenuItem("Panne réparée");
    private JMenuItem bike = new JMenuItem("Vélo");
    private JMenuItem car = new JMenuItem("Voiture");
    private JMenuItem component = new JMenuItem("Pièces");
    private JFrame mf;
    private Client cl;
    private int id;

    /**
     * Constructor which create the Menu of the panel
     */
    public MenuBar(JFrame myFrame, Client c, int id_c){
       
        this.jmenu.add(deconnexion);
        this.mf= myFrame;
        cl = c;
        id = id_c;
       //  ActivityDeposit.setJmenuBar(performance);
        deconnexion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mf.dispose();
				MenuPrincipal m = new MenuPrincipal(cl, id);
				
			}
        	
        });
    }
    
    /**
     * 
     * @return
     * Return the menu 
     */
    public JMenuBar getMenu(){
        return this.jmenu;   
    }
    
    
    
}
