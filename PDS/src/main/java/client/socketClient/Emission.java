package main.java.client.socketClient;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Emission implements Runnable {

	private PrintWriter out;
	private String login = null, message = "";
	private Scanner sc = null;
	private Chat_ClientServeur ccs;

	public Emission(PrintWriter out, Chat_ClientServeur ccs) {
		this.out = out;
		this.ccs=ccs;
	}


	public void run() {

		sc = new Scanner(System.in);

		while(true){
			if(!message.equals(ccs.getLastMessageToServer())){
				message=ccs.getLastMessageToServer();
				out.println(message);
				out.flush();
			}
		}
	}


	public PrintWriter getOut() {
		return out;
	}


	public void setOut(PrintWriter out) {
		this.out = out;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Scanner getSc() {
		return sc;
	}


	public void setSc(Scanner sc) {
		this.sc = sc;
	}
}
