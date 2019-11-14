package transportsystem.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Engine {
	private Integer id;
	private String name;
	private Double volume;
	private String fuel;
	
	public Engine(String name, Double volume, String fuel, Integer id) {
		this.name = name;
		this.volume = volume;
		this.fuel = fuel;
        this.id = id;
	}
	public String getName() {return this.name;}
	public Double getVolume() {return this.volume;}
	public String getFuel() {return this.fuel;}
    public Integer getId() {return this.id;}
}
