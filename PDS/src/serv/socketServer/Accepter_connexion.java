package serv.socketServer;

import java.io.*;
import java.net.*;


public class Accepter_connexion implements Runnable{

	private ServerSocket socketserver = null;
	private Socket socket = null;

	public Thread t1;
	public Accepter_connexion(ServerSocket ss){
	 socketserver = ss;
	}
	
	public void run() {
		
		try {
			while(true){
				
			socket = socketserver.accept();
			System.out.println("Quelqu'un veut se connecter  ");
			
			/*t1 = new Thread(new Authentification(socket));
			t1.start();*/
			

            t1 = new Thread(new Chat_ClientServeur(socket,"user"));

            t1.start();
			
			}
		} catch (IOException e) {
			
			System.err.println("Erreur serveur");
		}
		
	}
}
