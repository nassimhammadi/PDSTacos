package serv.socketServer;


import java.io.*;
import java.net.*;

import serv.DB.ConnectionPool;

/***
 * 
 * @author lazaredantz
 * Represent the server, allows the launching of the services 
 * This class will be instantiated one time
 *	
 */
public class Serveur {
 public static ServerSocket ss = null;
 public static Thread t;
 public static ConnectionPool CP= new ConnectionPool();
 
 	public void launch(){
		
		try {
			ss = new ServerSocket(2009);
			System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());
			
			t = new Thread(new Accepter_connexion(ss));
			t.start();
			
		} catch (IOException e) {
			System.err.println("Le port "+ss.getLocalPort()+" est déjà utilisé !");
		}
	
	}

 	public static void main (String args[]){
 		Serveur s=new Serveur();
 		s.launch();
 		
 	}

	
	}
