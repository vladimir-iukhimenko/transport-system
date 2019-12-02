package transportsystem.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employeedocuments")
public class DocumentEmployee extends Document{

	@Getter
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid")
	private Employee employee;
	
	public DocumentEmployee(String documenttype, int number, String issuedby) {
		this.documenttype = documenttype;
		this.number = number;
		this.issuedby = issuedby;
	}

	public DocumentEmployee() {this("",0,"");}

    public void addEmployee(Employee employee) {
        this.employee = employee;
        employee.getEmployeedocuments().add(this);
    }

}
