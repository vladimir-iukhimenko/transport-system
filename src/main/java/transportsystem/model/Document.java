package transportsystem.model;

import java.time.LocalDate;

public abstract class Document {
	protected String documenttype;
	protected int series;
	protected int number;
	protected String issuedby;
	protected LocalDate issueddate;
	
	public String getDocumentType() {return this.documenttype;}
	public int getSeries() {return this.series;}
	public int getNumber() {return this.number;}
	public String getIssuedBy() {return this.issuedby;}
	public LocalDate getIssuedDate() {return this.issueddate;}
	
}
