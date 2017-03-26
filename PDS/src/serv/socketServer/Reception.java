package serv.socketServer;

import java.io.BufferedReader;
import java.io.IOException;
/***
 * 
 * @author lazaredantz
 * We wait for a new message from client
 * When we get a new message, we update the lastMessageClient attribute
 */

public class Reception implements Runnable {

	private BufferedReader in;
	private String message = "", login = null;
	private Chat_ClientServeur ccs;
	
	
	public Reception(BufferedReader in, String login, Chat_ClientServeur ccs){
		
		this.in = in;
		this.login = login;
		this.ccs=ccs;
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
	        	
	        	
			message = in.readLine();
			ccs.setLastMessageClient(message);
			System.out.println("Le client a formul� la requ�te suivante :");
			System.out.println(login+" : "+message);
			
			
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}