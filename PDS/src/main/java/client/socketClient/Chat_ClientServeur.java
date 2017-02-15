package client.socketClient;


import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Chat_ClientServeur implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Scanner sc;
	private Thread t3, t4;
	private String lastMessageFromServer="", lastMessageToServer="";
	
	
	public Chat_ClientServeur(Socket s){
		socket = s;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			sc = new Scanner(System.in);
			
			Thread t4 = new Thread(new Emission(out, this));
			t4.start();
			Thread t3 = new Thread(new Reception(in, this));
			t3.start();
		
		   
		    
		} catch (IOException e) {
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}

	public synchronized String getLastMessageFromServeur() {
		return lastMessageFromServer;
	}

	public synchronized void setLastMessageFromServer(String lastMessageServeur) {
		this.lastMessageFromServer = lastMessageServeur;
	}

	public synchronized String getLastMessageToServer() {
		return lastMessageToServer;
	}

	public synchronized void setLastMessageToServer(String lastMessageToServer) {
		this.lastMessageToServer = lastMessageToServer;
	}

}
