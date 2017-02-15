/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.client.IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author laura nassimhammadi
 */
public class MenuBar {
    private JMenuBar jmenu = new JMenuBar();
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

    
    public MenuBar(){
        this.references.add(ref_bike);
        this.references.add(ref_component);
        this.references.add(ref_employees);
        this.history.add(histo_repairs);
        this.history.add(histo_deposit);
        this.management.add(stock);
        this.management.add(references);
        this.management.add(history);
        this.management.add(performance);
        this.repairs.add(support);
        this.repairs.add(repairing);
        this.information.add(bike);
        this.information.add(car);
        this.information.add(component);
        this.jmenu.add(management);
        this.jmenu.add(repairs);
        this.jmenu.add(information);
         
    }
    
    public JMenuBar getMenu(){
        return this.jmenu;   
    }
    
    
    
}
