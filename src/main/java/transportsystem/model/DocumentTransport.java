package transportsystem.model;


import javax.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transportdocuments")
public class DocumentTransport extends Document {

    @Column
    @Getter
    private LocalDate expiredate;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "transportid")
    private Transport transport;
	
	public DocumentTransport(String documenttype, Integer number, String issuedby) {
		this.documenttype = documenttype;
		this.number = number;
		this.issuedby = issuedby;
	}

	public DocumentTransport(){this("",0,"");}

	public void setExpiredate(String expiredate) {this.expiredate = LocalDate.parse(expiredate);}

}
