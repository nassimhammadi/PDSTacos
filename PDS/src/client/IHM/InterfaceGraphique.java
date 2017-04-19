/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.IHM;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;

/*tests*/

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
				JOptionPane.showMessageDialog(null, "Your connection has been closed !");
				try {
					fenetre.getC().socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
);

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
