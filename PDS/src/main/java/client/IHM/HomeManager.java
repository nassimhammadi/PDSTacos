/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.client.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

/**
 *
 * @author hollard hammadi
*/
public class HomeManager extends JFrame{
        
    
    public HomeManager(){
        MenuBar menu = new MenuBar();
        JPanel panelNord = new JPanel();
        Color c=new Color(1f,0f,0f,.5f);
        panelNord.setBackground(c);
        panelNord.setLayout(new GridLayout(1, 1)); // 1 ligne, 2 colonnes
        panelNord.add(menu.getMenu());
        
        JPanel panelWest = new JPanel(new GridLayout(1,4));
        panelWest.setBackground(Color.white);
        
        JPanel panelWest1 = new JPanel(new GridLayout(5,1));
        panelWest1.setBackground(Color.white);
        panelWest1.setPreferredSize(new Dimension(300, 200));
        panelWest1.setBorder(new TitledBorder("Listes des informations sur le véhicule : "));
        panelWest1.add(new JLabel("Identifiant du véhicule :"));
        JTextField id = new JTextField();
        JButton search = new JButton("Rechercher");
        panelWest1.add(id);
        panelWest1.add(search);
        JPanel panelWest2 = new JPanel(new GridLayout(5,1));
        panelWest2.setBackground(Color.white);
        panelWest2.setPreferredSize(new Dimension(300, 200));
        panelWest2.setBorder(new TitledBorder("Mettre à jour l'année du véhicule : "));
        panelWest2.add(new JLabel("Identifiant du véhicule :"));
        JTextField iden = new JTextField();
        panelWest2.add(iden);
        panelWest2.add(new JLabel("Année du véhicule :"));
        JTextField id4 = new JTextField();
        JButton search2 = new JButton("Mettre à jour");
        panelWest2.add(id4);
        panelWest2.add(search2);
        JPanel panelWest3 = new JPanel(new GridLayout(5,1));
        panelWest3.setBackground(Color.white);
        panelWest3.setPreferredSize(new Dimension(300, 200));
        panelWest3.setBorder(new TitledBorder("Supprimer un véhicule de la base : "));
        panelWest3.add(new JLabel("Identifiant du véhicule :"));
        JTextField id3 = new JTextField();
        JButton search3 = new JButton("Supprimer");
        panelWest3.add(id3);
        panelWest3.add(search3);
        JPanel panelWest4 = new JPanel(new GridLayout(6,2));
        panelWest4.setBackground(Color.white);
        panelWest4.setPreferredSize(new Dimension(300, 200));
        panelWest4.setBorder(new TitledBorder("Ajouter un véhicule : "));
        panelWest4.add(new JLabel("Identifiant :"));
        JTextField id5 = new JTextField();
        panelWest4.add(id5);
        panelWest4.add(new JLabel("Année :"));
        JTextField id6 = new JTextField();
        panelWest4.add(id6);
        panelWest4.add(new JLabel("Marque :"));
        JTextField id7 = new JTextField();
        panelWest4.add(id7);
        panelWest4.add(new JLabel("Modèle :"));
        JTextField id8 = new JTextField();
        panelWest4.add(id8);
        panelWest4.add(new JLabel("Type :"));
        JTextField id9 = new JTextField();
        panelWest4.add(id9);
        JButton search5 = new JButton("Ajouter");
        panelWest4.add(search5);
        
        panelWest.add(panelWest1);
        panelWest.add(panelWest2);
        panelWest.add(panelWest3);
        panelWest.add(panelWest4);

        JPanel panelSouth =  new JPanel(new GridLayout(1,2));
        panelSouth.setBackground(Color.white);
        panelSouth.setPreferredSize(new Dimension(300,400));
        panelSouth.setBorder(new TitledBorder("Informations sur le véhicule : "));
        panelSouth.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JPanel southLeft =  new JPanel();
        southLeft.setBackground(Color.white);
        BoxLayout layoutSouthLeft = new BoxLayout(southLeft, BoxLayout.Y_AXIS);
        southLeft.setLayout(layoutSouthLeft);

        southLeft.add(new JLabel("Modèle : "));
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Année : "));
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Marque : "));
        southLeft.add(Box.createGlue());
        southLeft.add(new JLabel("Type : "));
        southLeft.add(Box.createGlue());
        panelSouth.add(southLeft);

        JPanel southRight = new JPanel();
        southRight.setBackground(Color.white);
        southRight.add(Box.createGlue());

        southRight.add(Box.createGlue());
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
        panelSouth.add(jsp);
        
      

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
        this.setTitle("CSC App - Nassim Hammadi (M)");
        this.setBackground(Color.white);
        setVisible(true);

    }
    
    

}
        
   
        
        
    
    
 
    
    
    
  
    




