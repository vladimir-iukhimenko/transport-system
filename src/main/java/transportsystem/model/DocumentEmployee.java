package transportsystem.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employeedocuments")
public class DocumentEmployee extends Document{

	@Column
	@Getter
	@Setter
	private Integer employeeid;
	
	public DocumentEmployee(String documenttype, int number, String issuedby) {
		this.documenttype = documenttype;
		this.number = number;
		this.issuedby = issuedby;
	}

	public DocumentEmployee() {this("",0,"");}

}
