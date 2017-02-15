package main.java.client.socketClient;


import java.io.BufferedReader;
import java.io.IOException;


import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	private Chat_ClientServeur ccs;
	
	public Reception(BufferedReader in, Chat_ClientServeur ccs){
		
		this.in = in;
		this.ccs=ccs;
		
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
			message = in.readLine();
			
			
			if (!message.equals(ccs.getLastMessageFromServeur())){
			System.out.println("Le serveur vous dit :" +message);
			ccs.setLastMessageFromServer(message);
			}
			
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
