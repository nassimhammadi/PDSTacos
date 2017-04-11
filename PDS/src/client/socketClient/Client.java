package client.socketClient;




import java.io.*;
import java.net.*;

import com.mysql.jdbc.Connection;

import serv.socketServer.Serveur;

public class Client {

	public static Socket socket = null;
	public static Thread t1;
	private Chat_ClientServeur ccs;
	
	public synchronized Chat_ClientServeur getCcs() {
		return ccs;
	}



	public synchronized void setCcs(Chat_ClientServeur ccs) {
		this.ccs = ccs;
	}



	public void connect(){
		try {
			
			System.out.println("Demande de connexion");
			socket = new Socket("127.0.0.1",2009);
			System.out.println("Connexion etablie avec le serveur :"); // Si le message s'affiche c'est que je suis connecté
			ccs=new Chat_ClientServeur(socket);
			t1 = new Thread(ccs);
			t1.start();
			
			
			
		} catch (UnknownHostException e) {
		  System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
		} catch (IOException e) {
		  System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
		}
		
		
	}
	
	
	
	

}
