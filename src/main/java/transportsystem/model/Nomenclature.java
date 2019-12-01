package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nomenclatures")
public class Nomenclature {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column
    @Getter
    @Setter
	private Double length;

    @Column
    @Getter
    @Setter
	private Double width;

    @Column
    @Getter
    @Setter
	private Double height;

    @Column
    @Getter
    @Setter
	private Double weight;

    @Column
	private StringBuilder comment;

    @Getter
    @OneToMany(mappedBy = "nomenclature", fetch = FetchType.EAGER)
    private List<Goods> goods;

	public Nomenclature(Double length, Double width, Double height, Double weight) {
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	public Nomenclature() {this(0.0,0.0,0.0,0.0);}


	public String getComment() {return this.comment.toString();}
    public void setComment(String comment) {
        this.comment.append(LocalDateTime.now().toString() + " " + comment + "\r\n");
    }
}
