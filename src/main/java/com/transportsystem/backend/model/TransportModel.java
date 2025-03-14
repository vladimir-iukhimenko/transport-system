package com.transportsystem.backend.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.*;
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
    @JsonIdentityReference(alwaysAsId = true)
    private List<Transport> transports;




}
