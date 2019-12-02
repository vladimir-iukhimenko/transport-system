package transportsystem.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
	private Integer id;


    @Getter
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "nomenclatureid")
	private Nomenclature nomenclature;

    @Column
    @Getter
    @Setter
	private Integer amount;

    @Column
    @Getter
    @Setter
	private Double length;

    @Column
    @Getter
    @Setter
	private Double height;

    @Column
    @Getter
    @Setter
	private Double weight;

    @Getter
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "transportorderid")
    private TransportOrder transportorder;
	
	public void addNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
        nomenclature.getGoods().add(this);
    }

    public void addTransportorder(TransportOrder transportorder) {
        this.transportorder = transportorder;
        transportorder.getGoods().add(this);
    }

}
