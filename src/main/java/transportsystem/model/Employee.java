package transportsystem.model;

import java.time.LocalDate;

public class Employee {
	private String surname;
	private String name;
	private String patronymic;
	private String department;
	private String position;
	private String address;
	private int telephonenumber;
	private LocalDate dateofreceipt;
	private LocalDate dateofdismissal;
	
	public Employee(String surname, String name, String patronymic, String department, String position, 
					String address, int telephonenumber, LocalDate dateofreceipt) {
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.department = department;
		this.position = position;
		this.address = address;
		this.telephonenumber = telephonenumber;
		this.dateofreceipt = dateofreceipt;
	}
	public String getSurname() {return this.surname;}
	public String getName() {return this.name;}
	public String getPatronymic() {return this.patronymic;}
	public String getDepartment() {return this.department;}
	public String getPosition() {return this.position;}
	public String getAddress() {return this.address;}
	public int getTelephoneNumber() {return this.telephonenumber;}
	public LocalDate getDateOfReceipt() {return this.dateofreceipt;}
	public LocalDate getDateOfDismissal() {return this.dateofdismissal;}
	//TODO: implement this method
	public boolean setDateOfDismissal() {return false;}
}
