package com.transportsystem.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;

@Entity
@Table(name = "employeedocuments")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class DocumentEmployee extends Document{

	@Getter
	@ManyToOne(optional = false)
	@JoinColumn(name = "employeeid")
	private Employee employee;
	
	public void addEmployee(Employee employee) {
        this.employee = employee;
        employee.getEmployeedocuments().add(this);
    }

}
