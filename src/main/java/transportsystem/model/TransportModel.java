package transportsystem.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name="transportmodel")
public class TransportModel {

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
	private String color;

    @Column
    @Getter
    @Setter
	private String producer;

    @Column
    @Getter
    @Setter
	private Integer maxweight;

    @Column
    @Getter
    @Setter
	private Integer engineid;

    @Column
    @Getter
    @Setter
	private String enginepower;

    @Getter
    @Setter
    @OneToMany(mappedBy = "transportmodel", fetch = FetchType.EAGER)
    private List<Transport> transports;
	
	public TransportModel(String name, String color, String producer, Integer maxweight, Integer engineid, String enginepower)
    {
		this.name = name;
		this.color = color;
		this.producer = producer;
		this.maxweight = maxweight;
		this.engineid = engineid;
		this.enginepower = enginepower;
	}

    public TransportModel() {
        this("","","",0,0,"");
    }

}
