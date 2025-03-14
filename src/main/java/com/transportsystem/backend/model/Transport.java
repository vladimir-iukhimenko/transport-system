package com.transportsystem.backend.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

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

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String color;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "engineid")
    @JsonIdentityReference(alwaysAsId = true)
    private Engine engine;

    @Column
    @Getter
    @Setter
    private Integer enginepower;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "transportmodelid")
    @JsonIdentityReference(alwaysAsId = true)
	private TransportModel transportmodel;

	@Getter
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
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
    @JsonIdentityReference(alwaysAsId = true)
    private List<TransportOrder> transportorders;

    public void setStartupdate(String startupdate) {
        this.startupdate = LocalDate.parse(startupdate);
    }

    //TODO: Replacing transport to other transportmodel needs to delete from previous transportmodel
    public void addTransportmodel(TransportModel transportModel) {
        this.transportmodel = transportModel;
        transportModel.getTransports().add(this);
    }

    public void addEngine(Engine engine) {
        this.engine = engine;
        engine.getTransports().add(this);
    }
}
