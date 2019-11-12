package transportsystem.model;

import java.time.LocalDate;

public class EmployeeDocument extends Document{
	private Employee owner;
	
	public EmployeeDocument(String documenttype, int series, int number, String issuedby, LocalDate issueddate, Employee owner) {
		this.documenttype = documenttype;
		this.series = series;
		this.number = number;
		this.issuedby = issuedby;
		this.issueddate = issueddate;
		this.owner = owner;
	}
	
}
