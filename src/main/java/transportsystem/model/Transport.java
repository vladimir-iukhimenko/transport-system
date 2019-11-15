package transportsystem.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name="transports")
public class Transport {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="number")
	private String number;

    @Column(name="vin")
	private String vin;

    @Column(name="transportmodelid")
	private Integer transportmodelid;

	//private ArrayList<TransportDocument> documents;

    @Column(name="producedyear")
	private Integer producedyear;

    @Column(name="startupdate")
	private LocalDate startupdate;

    @Column(name="writeoffdate")
	private LocalDate writeoffdate;
	
	public Transport(String number, String vin, Integer transportmodelid, Integer producedyear, String startupdate)
	{
		this.number = number;
		this.vin = vin;
		this.transportmodelid = transportmodelid;
		this.producedyear = producedyear;
		this.startupdate = LocalDate.parse(startupdate);
	}

	public Transport(String number,String vin,Integer transportmodelid,Integer producedyear)
	{
		this(number,vin,transportmodelid,producedyear,LocalDate.now().toString());
	}


    public Transport(String number,String vin,Integer transportmodelid)
    {
        this(number,vin,transportmodelid,LocalDate.now().getYear(),LocalDate.now().toString());
    }

	public Transport()
	{this("","",null);}


	public String getNumber() {return this.number;}
	public String getVin() {return this.vin;}
	public Integer getProducedyear() {return this.producedyear;}
	public LocalDate getStartupdate() {return this.startupdate;}
	public LocalDate getWriteoffdate() {return this.writeoffdate;}
    public Integer getId() {return this.id;}
	public Integer getTransportmodelid() {return this.transportmodelid;}
    public void setNumber(String number) {this.number = number;}
    public void setVin(String vin) {this.vin = vin;}
    public void setProducedyear(Integer producedyear) {this.producedyear = producedyear;}
    public void setStartupdate(String startupdate) {
        if (startupdate.equals("")) {
            this.startupdate = LocalDate.now();
            return;
        }
        this.startupdate = LocalDate.parse(startupdate);
    }
    public void setWriteoffdate(LocalDate writeoffdate) {}
    public void setId(Integer id) {this.id = id;}
    public void setTransportmodelid(Integer transportmodelid) {this.transportmodelid = transportmodelid;}

}
