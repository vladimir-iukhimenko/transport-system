package com.transportsystem.backend.model;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import com.transportsystem.backend.embeddable.Comment;

@Entity
@Indexed
@Table(name = "nomenclatures")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class Nomenclature {

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
    private String number;

    @Column
    @Getter
    @Setter
	private Double length;

    @Column
    @Getter
    @Setter
	private Double width;

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
    private Comment comments;

    @Getter
    @OneToMany(mappedBy = "nomenclature", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Goods> goods;

}
