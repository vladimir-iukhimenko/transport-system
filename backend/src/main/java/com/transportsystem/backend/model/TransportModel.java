package com.transportsystem.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import java.util.List;

@Entity
@Indexed
@Table(name="transportmodel")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
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
	private String producer;

    @Column
    @Getter
    @Setter
	private Integer maxweight;

    @Column
    @Getter
    @Setter
    private Integer length;

    @Column
    @Getter
    @Setter
    private Integer width;

    @Column
    @Getter
    @Setter
    private Integer height;

    @Getter
    @OneToMany(mappedBy = "transportmodel", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "transports-transportmodels")
    private List<Transport> transports;




}
