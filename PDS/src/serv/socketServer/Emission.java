package serv.socketServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import client.socketClient.requestToServer;
import serv.json.Json;



public class Emission implements Runnable {

	private PrintWriter out;
	private String message = null;
	private String reponse = null;
	private Scanner sc = null;
	private Chat_ClientServeur ccs= null;
	
	
	
	
	public Emission(PrintWriter out, Chat_ClientServeur ccs) {
		this.out = out;
		this.ccs=ccs;
		this.message="";
	}

	
	public void run() {
		
		  sc = new Scanner(System.in);
		  
		  while(true){
			  if (!message.equals(ccs.getLastMessageClient())){
			    System.out.println("La r�ponse du serveur est :");
				message = ccs.getLastMessageClient();
				String reponse="Pas de reponse";
				Json<requestToServer> jsonRequest= new Json<requestToServer>(requestToServer.class);
				try {
					requestToServer rts= jsonRequest.deSerialize(message);
					rts.setCo(ccs.getCo());
					reponse=rts.evalRequest();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println(reponse);
			    out.flush();
			  }
			  }
	}
}
