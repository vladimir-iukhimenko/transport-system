package transportsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "nomenclatureid")
	private Integer nomenclatureid;

    @Column(name = "amount")
	private Integer amount;

    @Column(name = "length")
	private Double length;

    @Column(name = "height")
	private Double height;

    @Column(name = "weight")
	private Double weight;
	
	public Goods(Integer nomenclatureid, Integer amount, Double length, Double height, Double weight) {
		this.nomenclatureid = nomenclatureid;
		this.amount = amount;
		this.length = length;
		this.height = height;
        this.weight = weight;
	}

    public Goods() {this(0,0,0.0,0.0,0.0);}

    public Integer getId() {return this.id;}
    public Integer getNomenclatureid() {return this.nomenclatureid;}
    public Integer getAmount() {return this.amount;}
    public Double getLength() {return this.length;}
    public Double getHeight() {return this.height;}
    public Double getWeight() {return this.weight;}
    public void setId(Integer id) {this.id = id;}
    public void setNomenclatureid(Integer nomenclatureid) {this.nomenclatureid = nomenclatureid;}
    public void setAmount(Integer amount) {this.amount = amount;}
    public void setLength(Double length) {this.length = length;}
    public void setHeight(Double height) {this.height = height;}
    public void setWeight(Double weight) {this.weight = weight;}
}
