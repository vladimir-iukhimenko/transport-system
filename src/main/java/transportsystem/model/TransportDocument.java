package transportsystem.model;

import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public class TransportDocument extends Document {
	private LocalDate expiredate;
	
	public TransportDocument(String documenttype, Integer number, String issuedby) {
		this.documenttype = documenttype;
		this.number = number;
		this.issuedby = issuedby;
		this.issueddate = issueddate;
		this.expiredate = expiredate;
	}

	public TransportDocument(){this("",0,"");}

	public LocalDate getExpiredate() {return this.expiredate;}

	public void setExpiredate(String expiredate) {this.expiredate = LocalDate.parse(expiredate);}

}
