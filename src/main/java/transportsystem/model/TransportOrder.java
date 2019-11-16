package transportsystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transportorders")
public class TransportOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "goodsid")
	private Integer goodsid;

    @Column(name = "orderdate")
	private LocalDate orderdate;

    @Column(name = "transportpresentingdate")
	private LocalDate transportpresentingdate;

    @Column(name = "transportid")
	private Integer transportid;

    @Column(name = "loadingplace")
	private String loadingplace;

    @Column(name = "unloadingplace")
	private String unloadingplace;

    @Column(name = "placemethod")
	private String placemethod;

    @Column(name = "responsibleemployeeid")
	private Integer responsibleemployeeid;

    @Column(name = "telephonenumber")
	private Integer telephonenumber;

    @Column(name = "customeremployeeid")
	private Integer customeremployeeid;

    @Column(name = "comment")
	private StringBuilder comment;

    @Column(name = "declinereason")
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
    public Integer getId() {return this.id;}
    public Integer getGoodsid() {return goodsid;}
	public LocalDate getOrderdate() {return this.orderdate;}
	public LocalDate getTransportpresentingdate() {return this.transportpresentingdate;}
    public Integer getTransportid() {return this.transportid;}
	public String getLoadingplace() {return this.loadingplace;}
	public String getUnloadingplace() {return this.unloadingplace;}
	public String getPlacemethod() {return this.placemethod;}
	public Integer getResponsibleemployeeid() {return this.responsibleemployeeid;}
	public Integer getTelephonenumber() {return this.telephonenumber;}
	public Integer getCustomeremployeeid() {return this.customeremployeeid;}
	public String getComment() {return this.comment.toString();}
    public String getDeclinereason() {return this.declinereason;}
    public void setId(Integer id) {this.id = id;}
    public void setGoodsid(Integer id) {this.goodsid = goodsid;}
    public void setOrderdate(String orderdate) {this.orderdate = LocalDate.parse(orderdate);}
    public void setTransportpresentingdate(String transportpresentingdate) {this.transportpresentingdate = LocalDate.parse(transportpresentingdate);}
    public void setTransportid(Integer id) {this.transportid = id;}
    public void setLoadingplace(String loadingplace) {this.loadingplace = loadingplace;}
    public void setUnloadingplace(String unloadingplace) {this.unloadingplace = unloadingplace;}
    public void setPlacemethod(String placemethod) {this.placemethod = placemethod;}
    public void setResponsibleemployeeid(Integer responsibleemployeeid) {this.responsibleemployeeid = responsibleemployeeid;}
    public void setTelephonenumber(Integer telephonenumber) {this.telephonenumber = telephonenumber;}
    public void getCustomeremployeeid(Integer customeremployeeid) {this.customeremployeeid = customeremployeeid;}
    public void setComment(String comment) {
        this.comment.append(LocalDateTime.now().toString() + " " + comment + "\r\n");
    }
	public void setDeclinereason(String declinereason) {this.declinereason = declinereason;}
}
