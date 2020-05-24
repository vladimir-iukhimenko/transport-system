package com.transportsystem.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
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
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "nomenclatureid")
    @JsonBackReference(value = "bounded-goods")
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
    @Setter
    @ManyToOne(optional = true)
    @JoinColumn(name = "transportorderid")
    @JsonIdentityReference(alwaysAsId = true)
    private TransportOrder transportorder;

    //TODO: Replacing goods to other nomenclature has to deleting goods from previous nomenclature
	public void addNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
        nomenclature.getGoods().add(this);
    }

    public void addTransportorder(TransportOrder transportorder) {
        this.transportorder = transportorder;
    }

}
