package transportsystem.model;

import java.time.LocalDate;

public abstract class Document {
	protected String documenttype;
	protected Integer series;
	protected Integer number;
	protected String issuedby;
	protected LocalDate issueddate;
	
	public String getDocumentyype() {return this.documenttype;}
	public Integer getSeries() {return this.series;}
	public Integer getNumber() {return this.number;}
	public String getIssuedby() {return this.issuedby;}
	public LocalDate getIssueddate() {return this.issueddate;}
	public void setDocumenttype(String documenttype) {this.documenttype = documenttype;}
    public void setSeries(Integer series) {this.series = series;}
    public void setNumber{Integer number} {this.number = number;}
    public void setIssuedby(String issuedby) {this.issuedby = issuedby;}
    public void setIssueddate(String issueddate) {this.issueddate = LocalDate.parse(issueddate);}
	
}
