package transportsystem.model;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Transport {
	private Integer id;
	private String number;
	private String vin;
	private Integer transportmodelid;
	private ArrayList<TransportDocument> documents;
	private int producedyear;
	private LocalDate startupdate;
	private LocalDate writeoffdate;
	private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
	
	public Transport(String number, String vin, Integer transportmodelid, int producedyear, String startupdate, Integer id)
	{
		this.number = number;
		this.vin = vin;
		this.transportmodelid = transportmodelid;
		this.producedyear = producedyear;
		this.startupdate = LocalDate.parse(startupdate);
        this.id = id;
	}

	public Transport(String number,String vin,Integer transportmodelid,int producedyear)
	{
		this(number,vin,transportmodelid,producedyear,LocalDate.now().toString(),AUTO_ID.getAndIncrement());
	}

	public Transport(String number,String vin,Integer transportmodelid,int producedyear,String startupdate)
	{
		this(number,vin,transportmodelid,producedyear,startupdate,AUTO_ID.getAndIncrement());
	}

	public Transport()
	{}

	public String getNumber() {return this.number;}
	public String getVin() {return this.vin;}
	public int getProducedyear() {return this.producedyear;}
	public LocalDate getStartupdate() {return this.startupdate;}
	public LocalDate getWriteoffdate() {return this.writeoffdate;}
    public Integer getId() {return this.id;}
	public Integer getTransportmodelid() {return this.transportmodelid;}
    public void setNumber(String number) {this.number = number;}
    public void setVin(String vin) {this.vin = vin;}
    public void setProducedyear(int producedyear) {this.producedyear = producedyear;}
    public void setStartupdate(String startupdate) {this.startupdate = LocalDate.parse(startupdate);}
    public void setWriteoffdate(LocalDate writeoffdate) {}
    public void setId(Integer id) {this.id = id;}
    public void setTransportmodelid(Integer transportmodelid) {this.transportmodelid = transportmodelid;}

}
