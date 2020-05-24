package com.transportsystem.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Document {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
    protected Integer id;

    @Column(name="documenttype")
	@Getter
	@Setter
	protected String documenttype;

    @Column(name="series")
	@Getter
	@Setter
	protected Integer series;

    @Column(name="number")
	@Getter
	@Setter
	protected Integer number;

    @Column(name="issuedby")
	@Getter
	@Setter
	protected String issuedby;

    @Column(name="issueddate")
	@Getter
	protected LocalDate issueddate;

    public void setIssueddate(String issueddate) {this.issueddate = LocalDate.parse(issueddate);}
	
}
