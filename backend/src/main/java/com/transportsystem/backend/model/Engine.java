package com.transportsystem.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import com.transportsystem.backend.model.TransportModel;

import java.util.List;

@Entity
@Table(name="engine")
public class Engine {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Integer id;

    @Column
    @Getter
    @Setter
	private String name;

    @Column
    @Getter
    @Setter
	private Double volume;

    @Column
    @Getter
    @Setter
	private String fuel;

    @Getter
    @Setter
    @OneToMany(mappedBy = "engine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TransportModel> transportmodels;

}
