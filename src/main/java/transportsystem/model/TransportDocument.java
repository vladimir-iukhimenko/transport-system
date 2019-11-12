package transportsystem.model;

import java.time.LocalDate;

public class TransportDocument extends Document {
	private LocalDate expiredate;
	
	public TransportDocument(String documenttype, int series, int number, String issuedby, LocalDate issueddate, LocalDate expiredate) {
		this.documenttype = documenttype;
		this.series = series;
		this.number = number;
		this.issuedby = issuedby;
		this.issueddate = issueddate;
		this.expiredate = expiredate;
	}
	
}
