package com.transportsystem.backend.model;


import javax.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transportdocuments")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class DocumentTransport extends Document {

    @Column
    @Getter
    private LocalDate expiredate;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "transportid")
    @JsonBackReference(value = "bounded-transportdocuments")
    private Transport transport;
	
	public void setExpiredate(String expiredate) {this.expiredate = LocalDate.parse(expiredate);}

    public void addTransport(Transport transport) {
        this.transport = transport;
        transport.getTransportdocuments().add(this);
    }

}
