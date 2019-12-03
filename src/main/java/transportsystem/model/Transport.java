package transportsystem.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollectionOption;

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "transportmodelid")
	private TransportModel transportmodel;

	@Getter
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<DocumentTransport> transportdocuments;

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

    @Getter
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "transport")
    private List<TransportOrder> transportorders;

    public void setStartupdate(String startupdate) {
        this.startupdate = LocalDate.parse(startupdate);
    }

    public void addTransportmodel(TransportModel transportModel) {
        this.transportmodel = transportModel;
        transportModel.getTransports().add(this);
    }
}
