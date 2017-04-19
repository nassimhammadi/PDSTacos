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
;
	private String vehicleFromServer;
	
	
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
			System.err.println(" The remote server disconnected !  ");
		}
	}

	public synchronized String getLastMessageFromServeur() {
		if(lastMessageFromServer == null){return "";}
		return lastMessageFromServer;
	}
	
	public synchronized String getVehicleFromServeur(){
		return vehicleFromServer;
	}
	
	public synchronized void setVehicleFromServeur(String veh){
		this.vehicleFromServer = veh;
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
