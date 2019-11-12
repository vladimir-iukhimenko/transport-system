package transportsystem.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Engine {
	private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
	private Integer id;
	private String name;
	private double volume;
	private String fuel;
	
	public Engine(String name, double volume, String fuel) {
		this.name = name;
		this.volume = volume;
		this.fuel = fuel;
        this.id = AUTO_ID.getAndIncrement();
	}
	public String getName() {return this.name;}
	public double getVolume() {return this.volume;}
	public String getFuel() {return this.fuel;}
    public Integer getId() {return this.id;}
}
