package serv.model;

public class Pieces {
	private int id_piece;
	private String name;
	private int cost;
	private int stock;
	public Pieces(int id_piece, String name, int cost, int stock) {
		super();
		this.id_piece = id_piece;
		this.name = name;
		this.cost = cost;
		this.stock = stock;
	}
	public int getId_piece() {
		return id_piece;
	}
	public void setId_piece(int id_piece) {
		this.id_piece = id_piece;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
