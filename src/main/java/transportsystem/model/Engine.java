package transportsystem.model;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="engine")
public class Engine {

    @Id
    @Column(name-"id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column=(name="name")
	private String name;

    @Column=(name="volume")
	private Double volume;

    @Column(name="fuel")
	private String fuel;
	
	public Engine(String name, Double volume, String fuel) {
		this.name = name;
		this.volume = volume;
		this.fuel = fuel;
	}
	public String getName() {return this.name;}
	public Double getVolume() {return this.volume;}
	public String getFuel() {return this.fuel;}
    public Integer getId() {return this.id;}
    public void setName(String name) {this.name = name;}
    public void setVolume(String volume) {this.volume = volume;}
    public void setFuel(String fuel) {this.fuel = fuel;}
}
