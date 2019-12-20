package transportsystem.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

@Entity
@Indexed
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
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @NotBlank(message = "Number is required!")
	private String number;

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
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
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
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
