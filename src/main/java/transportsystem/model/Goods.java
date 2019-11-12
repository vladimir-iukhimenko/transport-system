package transportsystem.model;

public class Goods {
	private Nomenclature nomenclature;
	private int amount;
	private double length;
	private double height;
	private double weight;
	
	public Goods(Nomenclature nomenclature, int amount, double length, double height, double weight) {
		this.nomenclature = nomenclature;
		this.amount = amount;
		this.length = length;
		
	}
}
