/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.IHM;
import java.awt.*;
import javax.swing.*;

/*tesst*/

/**
 *
 * @author Laura
 */
public class InterfaceGraphique extends JFrame {
    
        Image img;
        Authentification fenetre;
    public InterfaceGraphique(){
            fenetre = new Authentification(this);
            setTitle("Interface");
            setSize(800, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.getContentPane().add(fenetre);
            this.pack();
            setLocationRelativeTo(null); 
            setVisible(true);
            
            // When the user exit the application, we close his socket
            this.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                   fenetre.getC().getSocket().close();
                }
            
            
            }
    
    /**
     * 
     * @param args
     * Main
     */
      public static void main(String[] args) {
       	
          InterfaceGraphique inter = new InterfaceGraphique();

	
    }
}