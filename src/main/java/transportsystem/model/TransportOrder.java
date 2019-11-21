package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transportorders")
public class TransportOrder {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
	private Integer id;

    @Column
    @Getter
    @Setter
	private Integer goodsid;

    @Column
    @Getter
	private LocalDate orderdate;

    @Column
    @Getter
	private LocalDate transportpresentingdate;

    @Column
    @Getter
    @Setter
	private Integer transportid;

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

    @Column
    @Getter
    @Setter
	private Integer responsibleemployeeid;

    @Column
    @Getter
    @Setter
	private Integer telephonenumber;

    @Column
    @Getter
    @Setter
	private Integer customeremployeeid;

    @Column
	private StringBuilder comment;

    @Column
    @Getter
    @Setter
	private String declinereason;
	
	public TransportOrder(Integer goodsid, String orderdate, String transportpresentingdate, Integer transportid, String loadingplace,
						String unloadingplace, String placemethod, Integer responsibleemployeeid, Integer telephonenumber, Integer customeremployeeid) {
		this.goodsid = goodsid;
		this.orderdate = LocalDate.parse(orderdate);
		this.transportpresentingdate = LocalDate.parse(transportpresentingdate);
		this.transportid = transportid;
		this.loadingplace = loadingplace;
		this.unloadingplace = unloadingplace;
		this.placemethod = placemethod;
		this.responsibleemployeeid = responsibleemployeeid;
		this.telephonenumber = telephonenumber;
		this.customeremployeeid = customeremployeeid;
	}

    public TransportOrder() {this(0,LocalDate.now().toString(),LocalDate.now().plusDays(7).toString(),
                            0,"","","",0,0,0);}

    public String getComment() {return this.comment.toString();}
    public void setOrderdate(String orderdate) {this.orderdate = LocalDate.parse(orderdate);}
    public void setTransportpresentingdate(String transportpresentingdate) {this.transportpresentingdate = LocalDate.parse(transportpresentingdate);}
    public void setComment(String comment) {
        this.comment.append(LocalDateTime.now().toString() + " " + comment + "\r\n");
    }

}
