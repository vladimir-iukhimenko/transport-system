package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nomenclatures")
public class Nomenclature {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "length")
	private Double length;

    @Column(name = "width")
	private Double width;

    @Column(name = "height")
	private Double height;

    @Column(name = "weight")
	private Double weight;

    @Column(name = "comment")
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
