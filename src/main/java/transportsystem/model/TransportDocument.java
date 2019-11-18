package transportsystem.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transportdocuments")
public class TransportDocument extends Document {

    @Column(name = "expiredate")
    private LocalDate expiredate;
	
	public TransportDocument(String documenttype, Integer number, String issuedby) {
		this.documenttype = documenttype;
		this.number = number;
		this.issuedby = issuedby;
	}

	public TransportDocument(){this("",0,"");}

	public LocalDate getExpiredate() {return this.expiredate;}

	public void setExpiredate(String expiredate) {this.expiredate = LocalDate.parse(expiredate);}

}
