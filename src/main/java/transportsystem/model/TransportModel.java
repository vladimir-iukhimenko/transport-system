package transportsystem.model;

import java.util.concurrent.atomic.AtomicInteger;

public class TransportModel {
	private Integer id;
	private String name;
	private String color;
	private String producer;
	private int maxweight;
	private Engine engine;
	private String enginepower;
	
	public TransportModel(String name, String color, String producer, int maxweight, Engine engine, String enginepower, Integer id)
    {
		this.name = name;
		this.color = color;
		this.producer = producer;
		this.maxweight = maxweight;
		this.engine = engine;
		this.enginepower = enginepower;
		this.id = id;
	}
	public String getName() {return this.name;}
	public String getColor() {return this.color;}
	public String getProducer() {return this.producer;}
	public int getMaxWeight() {return this.maxweight;}
	public String getEnginePower() {return this.enginepower;}
	public Integer getId() {return this.id;}
}
