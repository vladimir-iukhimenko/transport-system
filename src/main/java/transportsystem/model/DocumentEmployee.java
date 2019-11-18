package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employeedocuments")
public class DocumentEmployee extends Document{

	@Column(name="employeeid")
	private Integer employeeid;
	
	public DocumentEmployee(String documenttype, int number, String issuedby) {
		this.documenttype = documenttype;
		this.number = number;
		this.issuedby = issuedby;
	}

	public DocumentEmployee() {this("",0,"");}

	public Integer getEmployeeid() {return this.employeeid;}

    public void setEmployeeid(Integer id) {this.employeeid = id;}
	
}
