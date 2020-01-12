package com.transportsystem.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import java.util.List;

@Entity
@Indexed
@Table(name="transportmodel")
public class TransportModel {

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
	private String name;

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String color;

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String producer;

    @Column
    @Getter
    @Setter
	private Integer maxweight;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "engineid")
    @JsonBackReference
	private Engine engine;

    @Column
    @Getter
    @Setter
	private String enginepower;

    @Getter
    @OneToMany(mappedBy = "transportmodel", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transport> transports;


    public void addEngine(Engine engine) {
        this.engine = engine;
        engine.getTransportmodels().add(this);
    }

}
