package transportsystem.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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
    @NotBlank(message = "Number is required!")
	private String number;

    @Column
    @Getter
    @Setter
    @NotBlank(message = "VIN is required!")
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
    @NotNull(message = "Year is required!")
	private Integer producedyear;

    @Column
    @Getter
    @Past(message = "Date is required!")
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
