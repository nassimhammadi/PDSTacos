package main.java.serv.json;


public class Composant {

	private String type;
	private String reference;
	
	public Composant(String type, String reference) {
		super();
		this.type = type;
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "Composant [type=" + type + ", reference=" + reference + "]";
	}
	
	
}
