package transportsystem.model;

import java.time.LocalDate;

public class EmployeeDocument extends Document{
	private Integer employeeid;
	
	public EmployeeDocument(String documenttype, int series, int number, String issuedby, LocalDate issueddate, Integer employeeid) {
		this.documenttype = documenttype;
		this.series = series;
		this.number = number;
		this.issuedby = issuedby;
		this.issueddate = issueddate;
		this.employeeid = employeeid;
	}

	public EmployeeDocument() {}

	public Integer getEmployeeid() {return this.employeeid;}

    public void setEmployeeid(Integer id) {this.employeeid = id;}
	
}
