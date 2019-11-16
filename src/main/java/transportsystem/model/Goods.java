package transportsystem.model;

public class Goods {
	private Integer id;
	private Integer nomenclatureid;
	private Integer amount;
	private Double length;
	private Double height;
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
