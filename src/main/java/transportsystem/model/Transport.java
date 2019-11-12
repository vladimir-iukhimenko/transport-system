package transportsystem.model;

import java.time.LocalDate;
import java.util.*;

public class Transport {
	private Integer id;
	private String number;
	private String vin;
	private Integer transportmodelid;
	private ArrayList<TransportDocument> documents;
	private int producedyear;
	private LocalDate startupdate;
	private LocalDate writeoffdate;
	
	public Transport(String number, String vin, Integer transportmodelid, int producedyear, String startupdate, Integer id) {
		this.number = number;
		this.vin = vin;
		this.transportmodelid = transportmodelid;
		this.producedyear = producedyear;
		this.startupdate = LocalDate.parse(startupdate);
        this.id = id;
	}
	public String getNumber() {return this.number;}
	public String getVin() {return this.vin;}
	public int getProducedYear() {return this.producedyear;}
	public LocalDate getStartUpDate() {return this.startupdate;}
	public LocalDate getWriteOffDate() {return this.writeoffdate;}
	//TODO :implement this method 
	public boolean setWriteOffDate(LocalDate writeoffdate) {return false;}
    public Integer getId() {return this.id;}
	public Integer getTransportModelId() {return this.transportmodelid;}
}
