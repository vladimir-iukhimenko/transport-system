package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
	private Integer id;

    @Column
    @Getter
    @Setter
	private String surname;

    @Column
    @Getter
    @Setter
	private String name;

    @Column
    @Getter
    @Setter
	private String patronymic;

    @Column
    @Getter
    @Setter
	private String department;

    @Column
    @Getter
    @Setter
	private String position;

    @Column
    @Getter
    @Setter
	private String address;

    @Column
    @Getter
    @Setter
	private Integer telephonenumber;

    @Column
    @Getter
    private LocalDate dateofreceipt;

    @Column
    @Getter
	private LocalDate dateofdismissal;
	
	public Employee(String surname, String name, String department, String position, String dateofreceipt) {
		this.surname = surname;
		this.name = name;
		this.department = department;
		this.position = position;
		this.dateofreceipt = LocalDate.parse(dateofreceipt);
	}

    public Employee() {this("","","","",LocalDate.now().toString());}


    public void setDateofreceipt(String dateofreceipt) {this.dateofdismissal = LocalDate.parse(dateofreceipt);}
    public void setDateofdismissal(String dateofdismissal) {this.dateofdismissal = LocalDate.parse(dateofdismissal);}

}
