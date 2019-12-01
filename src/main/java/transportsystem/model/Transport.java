package transportsystem.model;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="transports")
public class Transport {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
	private Integer id;

	@Column
    @Getter
    @Setter
	private String number;

    @Column
    @Getter
    @Setter
	private String vin;

    @Getter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "transportmodelid")
	private TransportModel transportmodel;

	@Getter
    @Setter
    @OneToMany(mappedBy = "transport",fetch = FetchType.EAGER)
    private Set<DocumentTransport> documents;

    @Column
    @Getter
    @Setter
	private Integer producedyear;

    @Column
    @Getter
	private LocalDate startupdate;

    @Column
    @Getter
    @Setter
	private LocalDate writeoffdate;
	
	public Transport(String number, String vin, Integer producedyear, String startupdate)
	{
		this.number = number;
		this.vin = vin;
		this.producedyear = producedyear;
		this.startupdate = LocalDate.parse(startupdate);
	}

	public Transport(String number,String vin,Integer producedyear)
	{
		this(number,vin,producedyear,LocalDate.now().toString());
	}


    public Transport(String number,String vin)
    {
        this(number,vin,LocalDate.now().getYear(),LocalDate.now().toString());
    }

	public Transport()
	{this("","",null);}


    public void setStartupdate(String startupdate) {
        this.startupdate = LocalDate.parse(startupdate);
    }

    public void addTransportmodel(TransportModel transportModel) {
        this.transportmodel = transportModel;
        transportModel.getTransports().add(this);
    }
}
