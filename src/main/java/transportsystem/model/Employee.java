package transportsystem.model;

import java.time.LocalDate;

public class Employee {
	private Integer id;
	private String surname;
	private String name;
	private String patronymic;
	private String department;
	private String position;
	private String address;
	private Integer telephonenumber;
	private LocalDate dateofreceipt;
	private LocalDate dateofdismissal;
	
	public Employee(String surname, String name, String department, String position, String dateofreceipt) {
		this.surname = surname;
		this.name = name;
		this.department = department;
		this.position = position;
		this.dateofreceipt = LocalDate.parse(dateofreceipt);
	}

    public Employee() {this("","","","",LocalDate.now().toString());}

	public String getSurname() {return this.surname;}
	public String getName() {return this.name;}
	public String getPatronymic() {return this.patronymic;}
	public String getDepartment() {return this.department;}
	public String getPosition() {return this.position;}
	public String getAddress() {return this.address;}
	public Integer getTelephonenumber() {return this.telephonenumber;}
	public LocalDate getDateOfReceipt() {return this.dateofreceipt;}
	public LocalDate getDatedfdismissal() {return this.dateofdismissal;}
    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}
    public void setSurname(String surname) {this.surname = name;}
    public void setName(String name) {this.name = name;}
    public void setPatronymic(String patronymic) {this.patronymic = patronymic;}
    public void setDepartment(String department) {this.department = department;}
    public void setPosition(String position) {this.position = position;}
    public void setAddress(String address) {this.address = address;}
    public void setTelephonenumber(Integer telephonenumber) {this.telephonenumber = telephonenumber;}
    public void setDateofreceipt(String dateofreceipt) {this.dateofdismissal = LocalDate.parse(dateofreceipt);}
    public void setDateofdismissal(String dateofdismissal) {this.dateofdismissal = LocalDate.parse(dateofdismissal);}

}
