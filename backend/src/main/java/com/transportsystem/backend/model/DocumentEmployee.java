package com.transportsystem.backend.model;

import javax.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "employeedocuments")
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
