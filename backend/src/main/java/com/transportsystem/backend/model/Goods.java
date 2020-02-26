package com.transportsystem.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

@Entity
@Indexed
@Table(name = "goods")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class Goods {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
	private Integer id;

    @Getter
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "nomenclatureid")
    @JsonBackReference
	private Nomenclature nomenclature;

    @Column
    @Getter
    @Setter
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String name;

    @Column
    @Getter
    @Setter
	private Integer amount;

    @Column
    @Getter
    @Setter
	private Double length;

    @Column
    @Getter
    @Setter
	private Double height;

    @Column
    @Getter
    @Setter
	private Double weight;

    @Getter
    @ManyToOne(optional = true)
    @JoinColumn(name = "transportorderid")
    @JsonIgnore
    private TransportOrder transportorder;

    //TODO: Replacing goods to other nomenclature has to deleting goods from previous nomenclature
	public void addNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
        nomenclature.getGoods().add(this);
    }

    public void addTransportorder(TransportOrder transportorder) {
        this.transportorder = transportorder;
        transportorder.getGoods().add(this);
    }

}
