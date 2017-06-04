package serv.model;

public class Performance {
	
	private int nbRep;
	private int dureeOp;
	private int nbPieces;
	
	
	
	
	
	public Performance(int nbRep, int dureeOp, int nbPieces) {
		super();
		this.nbRep = nbRep;
		this.dureeOp = dureeOp;
		this.nbPieces = nbPieces;
	}
	
	
	
	@Override
	public String toString() {
		return "Performance [nbRep=" + nbRep + ", dureeOp=" + dureeOp + ", nbPieces=" + nbPieces + "]";
	}



	public int getNbRep() {
		return nbRep;
	}
	public void setNbRep(int nbRep) {
		this.nbRep = nbRep;
	}
	public int getDureeOp() {
		return dureeOp;
	}
	public void setDureeOp(int dureeOp) {
		this.dureeOp = dureeOp;
	}
	public int getNbPieces() {
		return nbPieces;
	}
	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}
	
	
	

}
