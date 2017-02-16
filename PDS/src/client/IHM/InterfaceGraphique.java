/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.IHM;
import java.awt.*;
import javax.swing.*;



/**
 *
 * @author Laura
 */
public class InterfaceGraphique extends JFrame {
    
        Image img;
    public InterfaceGraphique(){
            Authentification fenetre = new Authentification(this);
            setTitle("Interface");
            setSize(800, 400);
            setLocationRelativeTo(null); 
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.getContentPane().add(fenetre);
            setVisible(true);
            }
    
    
      public static void main(String[] args) {
       	
          InterfaceGraphique inter = new InterfaceGraphique();
          
       
     
        
	
    }
}