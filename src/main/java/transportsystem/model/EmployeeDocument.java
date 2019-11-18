package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employeedocuments")
public class EmployeeDocument extends Document{


	private Integer employeeid;
	
	public EmployeeDocument(String documenttype, int number, String issuedby) {
		this.documenttype = documenttype;
		this.number = number;
		this.issuedby = issuedby;
	}

	public EmployeeDocument() {this("",0,"");}

	public Integer getEmployeeid() {return this.employeeid;}

    public void setEmployeeid(Integer id) {this.employeeid = id;}
	
}
