package main.java.serv.json;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App 
{
	public static void main( String[] args ) throws IOException
	{

		ArrayList<Voiture> listeVoiture=new ArrayList<Voiture>();
		listeVoiture listeV=new listeVoiture();
		Voiture v=new Voiture();
		String workspace=System.getProperty("user.dir");
		// Récupération du contenu du fichier version encodé
		byte[] encoded = Files.readAllBytes(Paths.get(workspace+"/src/main/java/test/test/listVoiture.json"));
		// Récupération du contenu du fichier version décodé
		String monJson= new String(encoded, "UTF-8");
		
		
		
		Json myJSon= new Json(listeVoiture.class);

		System.out.println("Lecture du fichier de départ (JSON) :");
		System.out.println(monJson);
		listeV=(listeVoiture) myJSon.deSerialize(monJson);
		System.out.println("Lecture du tableau récupéré en Java depuis le Json:");
		System.out.println(listeV);
		monJson=myJSon.serialize(listeV);
		System.out.println("Lecture du fichier JSON généré à partir de l'objet Java :");
		System.out.println(monJson);
		System.out.println("Lecture de la deuxième pièce à réparer de la deuxième voiture :");
		System.out.println(listeV.getListeV().get(1).getPieceAReparer().get(1));
	}
}
