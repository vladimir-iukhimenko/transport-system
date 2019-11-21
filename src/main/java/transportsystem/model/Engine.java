package transportsystem.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="engine")
public class Engine {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Integer id;

    @Column
    @Getter
    @Setter
	private String name;

    @Column
    @Getter
    @Setter
	private Double volume;

    @Column
    @Getter
    @Setter
	private String fuel;
	
	public Engine(String name, Double volume, String fuel) {
		this.name = name;
		this.volume = volume;
		this.fuel = fuel;
	}
	public Engine () {}
}
