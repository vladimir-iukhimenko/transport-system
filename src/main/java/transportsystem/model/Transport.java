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
	private Integer producedyear;
	private LocalDate startupdate;
	private LocalDate writeoffdate;
	private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
	
	public Transport(String number, String vin, Integer transportmodelid, Integer producedyear, String startupdate, Integer id)
	{
		this.number = number;
		this.vin = vin;
		this.transportmodelid = transportmodelid;
		this.producedyear = producedyear;
		this.startupdate = LocalDate.parse(startupdate);
        this.id = id;
	}

	public Transport(String number,String vin,Integer transportmodelid,Integer producedyear)
	{
		this(number,vin,transportmodelid,producedyear,LocalDate.now().toString(),AUTO_ID.getAndIncrement());
	}

	public Transport(String number,String vin,Integer transportmodelid,Integer producedyear,String startupdate)
	{
		this(number,vin,transportmodelid,producedyear,startupdate,AUTO_ID.getAndIncrement());
	}

    public Transport(String number,String vin,Integer transportmodelid)
    {
        this(number,vin,transportmodelid,LocalDate.now().getYear(),LocalDate.now().toString(),AUTO_ID.getAndIncrement());
    }

	public Transport()
	{this("","",null);}

	public String getNumber() {return this.number;}
	public String getVin() {return this.vin;}
	public Integer getProducedyear() {return this.producedyear;}
	public LocalDate getStartupdate() {return this.startupdate;}
	public LocalDate getWriteoffdate() {return this.writeoffdate;}
    public Integer getId() {return this.id;}
	public Integer getTransportmodelid() {return this.transportmodelid;}
    public void setNumber(String number) {this.number = number;}
    public void setVin(String vin) {this.vin = vin;}
    public void setProducedyear(Integer producedyear) {this.producedyear = producedyear;}
    public void setStartupdate(String startupdate) {this.startupdate = LocalDate.parse(startupdate);}
    public void setWriteoffdate(LocalDate writeoffdate) {}
    public void setId(Integer id) {this.id = id;}
    public void setTransportmodelid(Integer transportmodelid) {this.transportmodelid = transportmodelid;}

}
