package transportsystem.model;

import java.time.LocalDateTime;

public class Nomenclature {
	private Integer id;
	private Double length;
	private Double width;
	private Double height;
	private Double weight;
	private StringBuilder comment;
	public Nomenclature(Double length, Double width, Double height, Double weight) {
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	public Nomenclature() {this(0.0,0.0,0.0,0.0);}

	public Integer getId() {return this.id;}
	public Double getLength() {return this.length;}
	public Double getWidth() {return this.width;}
	public Double getHeight() {return this.height;}
	public Double getWeight() {return this.weight;}
	public String getComment() {return this.comment.toString();}
    public void setId(Integer id) {this.id = id;}
    public void setLength(Double length) {this.length = length;}
	public void setWidth(Double width) {this.width = width;}
    public void setHeight(Double height) {this.height = height;}
    public void setWeight(Double weight) {this.weight = weight;}
    public void setComment(String comment) {
        this.comment.append(LocalDateTime.now().toString() + " " + comment + "\r\n");
    }
}
