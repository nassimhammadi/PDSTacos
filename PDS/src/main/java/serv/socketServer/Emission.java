package serv.socketServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import main.java.client.socketClient.requestToServer;
import main.java.serv.json.Json;


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
	

	private String resolveRequest(String message2) {
		
		
		String result="Votre requête n'est pas valide";
		if (message2.equals("1")){
		result=getListVehicule();
		}
		
		else if (message2.equals("2")){
		result=getListEmployee();
		}
		
		
		
		return result;
	}


	private String getListEmployee() {
		// Appel JDBC, puis serialise via Gson

		String workspace=System.getProperty("user.dir");
		byte[] encoded;
		String monJson="";
		try {
			encoded = Files.readAllBytes(Paths.get(workspace+"/src/main/java/client/json/listEmployee.json"));
			// Récupération du contenu du fichier version décodé
			monJson= new String(encoded, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return monJson;
	}


	private String getListVehicule() {
		// Appel JDBC, puis serialise via Gson

		String workspace=System.getProperty("user.dir");
		byte[] encoded;
		String monJson="";
		try {
			encoded = Files.readAllBytes(Paths.get(workspace+"/src/main/java/client/json/listVoiture.json"));
			// Récupération du contenu du fichier version décodé
			monJson= new String(encoded, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return monJson;
	}
}
