package com.transportsystem.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="engine")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
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
    @JsonIdentityReference(alwaysAsId = true)
    private List<Transport> transports;

}
