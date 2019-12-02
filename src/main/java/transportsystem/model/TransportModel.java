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

    @Getter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "engineid")
	private Engine engine;

    @Column
    @Getter
    @Setter
	private String enginepower;

    @Getter
    @OneToMany(mappedBy = "transportmodel", fetch = FetchType.EAGER)
    private List<Transport> transports;


    public void addEngine(Engine engine) {
        this.engine = engine;
        engine.getTransportmodels().add(this);
    }

}
