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

    @Column
    @Getter
    @Setter
	private Integer nomenclatureid;

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
	
	public Goods(Integer nomenclatureid, Integer amount, Double length, Double height, Double weight) {
		this.nomenclatureid = nomenclatureid;
		this.amount = amount;
		this.length = length;
		this.height = height;
        this.weight = weight;
	}

    public Goods() {this(0,0,0.0,0.0,0.0);}

}
