package com.transportsystem.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.transportsystem.backend.converter.CommentConverter;
import lombok.*;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import com.transportsystem.backend.embeddable.Comment;
import com.transportsystem.backend.generators.TransportOrderGenerator;

@Entity
@Indexed
@Table(name = "transportorders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class TransportOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "num_gen")
    @SequenceGenerator(name = "num_gen", sequenceName = "num_gen", allocationSize = 1)
    @Column
    @Getter
    @Setter
	private Integer id;

    @Column(nullable = false)
    @Getter
    @Setter
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String ordernumber;

    @Getter
    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "transportorder")
    @JsonIdentityReference(alwaysAsId = true)
	private List<Goods> goods;

    @Column(nullable = false)
    @Getter
	private LocalDate orderdate;

    @Column(nullable = false)
    @Getter
	private LocalDate transportpresentingdate;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "transportid")
    @JsonIdentityReference(alwaysAsId = true)
    private Transport transport;

    @Column(nullable = false)
    @Getter
    @Setter
	private String loadingplace;

    @Column(nullable = false)
    @Getter
    @Setter
	private String unloadingplace;

    @Column(nullable = false)
    @Getter
    @Setter
	private String placemethod;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "responsibleemployeeid")
    @JsonIdentityReference(alwaysAsId = true)
    private Employee employeeresponsible;

    @Column(nullable = false)
    @Getter
    @Setter
	private Integer telephonenumber;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "customeremployeeid")
    @JsonIdentityReference(alwaysAsId = true)
    private Employee employeecustomer;

    @Getter
    @Setter
    @Column
    @Convert(converter = CommentConverter.class)
    private Comment comments;

    //TODO: initializer for Comment object. Rework?
    {this.comments = new Comment();}

    @Column
    @Getter
    @Setter
	private String declinereason;

    public void addOrdernumber() {this.ordernumber = TransportOrderGenerator.generator();}

    @PrePersist
    public void addOrderdate() {this.orderdate = LocalDate.now();}

    public void setOrderdate(String orderdate) {this.orderdate = LocalDate.parse(orderdate);}

    public void setTransportpresentingdate(String transportpresentingdate) {this.transportpresentingdate = LocalDate.parse(transportpresentingdate);}

    public void addTransport(Transport transport) {
        this.transport = transport;
        transport.getTransportorders().add(this);
    }

    public void addEmployeeresponsible (Employee employee) {
        this.employeeresponsible = employee;
        employee.getTransportordersresponsible().add(this);
    }

    public void addEmployeecustomer (Employee employee) {
        this.employeecustomer = employee;
        employee.getTransportordercustomer().add(this);
    }
}
