package serv.socketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;


public class Chat_ClientServeur implements Runnable {

	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String login = "";
	private String lastMessageClient="";
	private Thread t3, t4;
	private Connection co=Serveur.CP.getConnectionPool();
	
	public Chat_ClientServeur(Socket s, String log){
		
		socket = s;
		login = log;
		
	}
	public void run() {
		
		try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
				
		Thread t3 = new Thread(new Reception(in,login, this));
		t3.start();
		
		Thread t4 = new Thread(new Emission(out, this));
		t4.start();
		
		boolean fin=false;
		while (!fin){
			if (!socket.isConnected()){
				socket.close();
				fin=true;
			}
		}
		
		
		} catch (IOException e) {
			System.err.println(login +"s'est déconnecté ");
		}
}
	public synchronized String getLastMessageClient() {
		return lastMessageClient;
	}
	public synchronized void setLastMessageClient(String lastMessageClient) {
		this.lastMessageClient = lastMessageClient;
	}
	public Connection getCo() {
		return co;
	}
	public void setCo(Connection co) {
		this.co = co;
	}
}
