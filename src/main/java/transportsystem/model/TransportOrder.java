package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import transportsystem.generators.TransportOrderGenerator;

@Entity
@Table(name = "transportorders")
public class TransportOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "num_gen")
    @SequenceGenerator(name = "num_gen", sequenceName = "num_gen", allocationSize = 1)
    @Column
    @Getter
    @Setter
	private Integer id;

    @Column
    @Getter
    private String ordernumber;

    @Getter
    @OneToMany(mappedBy = "transportorder", fetch = FetchType.EAGER)
	private List<Goods> goods;

    @Column
    @Getter
	private LocalDate orderdate = LocalDate.now();

    @Column
    @Getter
	private LocalDate transportpresentingdate;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "transportid")
	private Transport transport;

    @Column
    @Getter
    @Setter
	private String loadingplace;

    @Column
    @Getter
    @Setter
	private String unloadingplace;

    @Column
    @Getter
    @Setter
	private String placemethod;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "responsibleemployeeid")
	private Employee employeeresponsible;

    @Column
    @Getter
    @Setter
	private Integer telephonenumber;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "customeremployeeid")
	private Employee employeecustomer;

    @Column
	private StringBuilder comment = new StringBuilder("");

    @Column
    @Getter
    @Setter
	private String declinereason;

    public String getComment() {return this.comment.toString();}

    public void setOrdernumber() {this.ordernumber = TransportOrderGenerator.generator();}

    public void setTransportpresentingdate(String transportpresentingdate) {this.transportpresentingdate = LocalDate.parse(transportpresentingdate);}

    public void setComment(String comment) {
        this.comment.append(LocalDateTime.now().toString() + " " + comment + "\r\n");
    }

    public void addTransport(Transport transport) {
        this.transport = transport;
        transport.getTransportorders().add(this);
    }

    public void addEmployeeresponsible (Employee employee) {
        this.employeeresponsible = employee;
        employee.getTransportordersresponsible().add(this);
    }

    public void addEmployeecustomer (Employee employee) {
        this.employeecustomer = employee;
        employee.getTransportordercustomer().add(this);
    }
}
