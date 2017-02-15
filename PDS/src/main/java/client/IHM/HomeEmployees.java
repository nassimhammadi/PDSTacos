/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.client.IHM;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.File;
import static java.sql.JDBCType.NULL;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Laura Nassim
 */
public class HomeEmployees extends JFrame {

    public HomeEmployees(){
      
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimEcran = tk.getScreenSize(); 
        setSize(dimEcran.width, dimEcran.height);
        setLocationRelativeTo(null);
        JPanel panelNord = new JPanel();
        panelNord.setLayout(new GridLayout(1, 2)); // 1 ligne, 2 colonnes
        JPanel panelBarre = new JPanel();
        BoxLayout layoutBarre = new BoxLayout(panelBarre, BoxLayout.X_AXIS);
        panelBarre.add(new JLabel("Manager : Monkey D. Dragon "));
        panelNord.add(panelBarre);

        JPanel panelWest = new JPanel();
        BoxLayout layoutWest = new BoxLayout(panelWest, BoxLayout.X_AXIS);
        JPanel panelWest1 = new JPanel(new GridLayout(5,1));
        panelWest1.setPreferredSize(new Dimension(300, 200));
        panelWest1.setBorder(new TitledBorder("Listes des informations sur le véhicule : "));
        panelWest1.add(new JLabel("Identifiant du véhicule :"));
        JTextField id = new JTextField(10);
        JButton search = new JButton("Rechercher");
        panelWest1.add(id);
        panelWest1.add(search);
        panelWest.add(panelWest1);
        

        JPanel panelSouth =  new JPanel(new GridLayout(1,2));
        panelSouth.setPreferredSize(new Dimension(300,400));
        panelSouth.setBorder(new TitledBorder("Informations sur le véhicule : "));
        panelSouth.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JPanel southLeft =  new JPanel();
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
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelNord, BorderLayout.NORTH);
        panelPrincipal.add(panelWest, BorderLayout.WEST);
        panelPrincipal.add(panelSouth, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        setContentPane(panelPrincipal);

        setVisible(true);

    }

}
        
   
        
        
    
    
 
    
    
    
  
    



