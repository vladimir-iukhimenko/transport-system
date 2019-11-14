package transportsystem.model;

import javax.persistence.*;

@Entity
@Table(name="transportmodelid")
public class TransportModel {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name="name")
	private String name;

    @Column(name="color")
	private String color;

    @Column(name="producer")
	private String producer;

    @Column(name="maxweight")
	private Integer maxweight;

    @Column(name="engineid")
	private Integer engineid;

    @Column(name="enginepower")
	private String enginepower;
	
	public TransportModel(String name, String color, String producer, Integer maxweight, Integer engineid, String enginepower)
    {
		this.name = name;
		this.color = color;
		this.producer = producer;
		this.maxweight = maxweight;
		this.engineid = engineid;
		this.enginepower = enginepower;
	}

    public TransportModel() {
        this("","","",0,0,"");
    }
	public String getName() {return this.name;}
	public String getColor() {return this.color;}
	public String getProducer() {return this.producer;}
	public Integer getMaxweight() {return this.maxweight;}
	public String getEnginepower() {return this.enginepower;}
	public Integer getId() {return this.id;}
	public Integer getEngineid() {return this.engineid;}
    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;}
    public void setProducer(String producer) {this.producer = producer;}
    public void setMaxweight(Integer maxweight) {this.maxweight = maxweight;}
    public void setEnginepower(String enginepower) {this.enginepower = enginepower;}
    public void setId(Integer id) {this.id = id;}
    public void setEngineid(Integer id) {this.engineid = engineid;}
}
