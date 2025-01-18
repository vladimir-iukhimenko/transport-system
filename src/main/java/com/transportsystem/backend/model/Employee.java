package com.transportsystem.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import com.transportsystem.backend.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

@Entity
@Indexed
@Table(name = "employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class Employee {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
	private Integer id;

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String surname;

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String name;

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String patronymic;

    @Column
    @Getter
    @Setter
	private String department;

    @Column
    @Getter
    @Setter
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String position;

    @Column
    @Getter
    @Setter
	private String address;

    @Column
    @Getter
    @Setter
	private Integer telephonenumber;

    @Column
    @Getter
    @Setter
    private LocalDate dateofreceipt;

    @Column
    @Getter
    @Setter
	private LocalDate dateofdismissal;

    @Getter
    @OneToOne(mappedBy = "employee")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @Getter
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<DocumentEmployee> employeedocuments;

    @Getter
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "employeeresponsible", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<TransportOrder> transportordersresponsible;

    @Getter
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "employeecustomer", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<TransportOrder> transportordercustomer;
}
