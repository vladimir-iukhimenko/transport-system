package transportsystem.model;

public class Nomenclature {
	private double length;
	private double width;
	private double height;
	private double weight;
	private StringBuilder comment;
	public Nomenclature(double length, double width, double height, double weight) {
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}
	public String getComment() {return this.comment.toString();}
	//TODO: implement function
	public boolean addComment() {return false;}
}
