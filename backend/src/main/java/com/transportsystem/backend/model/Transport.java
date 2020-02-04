package com.transportsystem.backend.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.bridge.builtin.IntegerBridge;

@Entity
@Indexed
@Table(name="transports")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
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
    @JsonBackReference
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
    @FieldBridge(impl = IntegerBridge.class)
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
    @JsonIgnore
    private List<TransportOrder> transportorders;

    public void setStartupdate(String startupdate) {
        this.startupdate = LocalDate.parse(startupdate);
    }

    //TODO: Replacing transport to other transportmodel needs to delete from previous transportmodel
    public void addTransportmodel(TransportModel transportModel) {
        this.transportmodel = transportModel;
        transportModel.getTransports().add(this);
    }
}
