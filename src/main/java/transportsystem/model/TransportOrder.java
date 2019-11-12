package transportsystem.model;

import java.time.LocalDate;

public class TransportOrder {
	private Goods goods;
	private LocalDate orderdate;
	private LocalDate transportpresentingdate;
	private Transport transport;
	private String loadingplace;
	private String unloadingplace;
	private String placemethod;
	private Employee responsible;
	private int telephonenumber;
	private Employee customer;
	private String comment;
	private String declinereason;
	
	public TransportOrder(Goods goods, LocalDate orderdate, LocalDate transportpresentingdate, Transport transport, String loadingplace,
						String unloadingplace, String placemethod, Employee responsible, int telephonenumber, Employee customer) {
		this.goods = goods;
		this.orderdate = orderdate;
		this.transportpresentingdate = transportpresentingdate;
		this.transport = transport;
		this.loadingplace = loadingplace;
		this.unloadingplace = unloadingplace;
		this.placemethod = placemethod;
		this.responsible = responsible;
		this.telephonenumber = telephonenumber;
		this.customer = customer;
	}
	public LocalDate getOrderDate() {return this.orderdate;}
	public LocalDate getTransportPresentingDate() {return this.transportpresentingdate;}
	public String getLoadingPlace() {return this.loadingplace;}
	public String getUnloadingPlace() {return this.unloadingplace;}
	public String getPlaceMethod() {return this.placemethod;}
	public Employee getResponsible() {return this.responsible;}
	public int getTelephoneNumber() {return this.telephonenumber;}
	public Employee getCustomer() {return this.customer;}
	public String getComment() {return this.comment;}
	// TODO: implement this method
	public boolean setComment(String comment) {return false;}
	public String getDeclineReason() {return this.declinereason;}
	// TODO: Implement this method
	public boolean setDeclineReason(String declineReason) {return false;}
}
