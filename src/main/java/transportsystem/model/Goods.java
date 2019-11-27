package transportsystem.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
	private Integer id;


    @Getter
    @Setter
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "nomenclatureid")
	private Nomenclature nomenclature;

    @Column(name = "amount")
    @Getter
    @Setter
	private Integer amount;

    @Column(name = "length")
    @Getter
    @Setter
	private Double length;

    @Column(name = "height")
    @Getter
    @Setter
	private Double height;

    @Column(name = "weight")
    @Getter
    @Setter
	private Double weight;
	
	public Goods(Integer amount, Double length, Double height, Double weight) {
		this.amount = amount;
		this.length = length;
		this.height = height;
        this.weight = weight;
	}

    public Goods() {this(0,0.0,0.0,0.0);}

}
