package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Document {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name="documenttype")
	protected String documenttype;

    @Column(name="series")
	protected Integer series;

    @Column(name="number")
	protected Integer number;

    @Column(name="issuedby")
	protected String issuedby;

    @Column(name="issueddate")
	protected LocalDate issueddate;
	
	public Integer getId() {return this.id;}
    public String getDocumenttype() {return this.documenttype;}
	public Integer getSeries() {return this.series;}
	public Integer getNumber() {return this.number;}
	public String getIssuedby() {return this.issuedby;}
	public LocalDate getIssueddate() {return this.issueddate;}
	public void setDocumenttype(String documenttype) {this.documenttype = documenttype;}
    public void setSeries(Integer series) {this.series = series;}
    public void setNumber(Integer number) {this.number = number;}
    public void setIssuedby(String issuedby) {this.issuedby = issuedby;}
    public void setIssueddate(String issueddate) {this.issueddate = LocalDate.parse(issueddate);}
    public void setId(Integer id) {this.id = id;}
	
}
