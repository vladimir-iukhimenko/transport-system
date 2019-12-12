package transportsystem.model;

import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import transportsystem.embeddable.Comment;

@Entity
@Table(name = "nomenclatures")
public class Nomenclature {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column
    @Getter
    @Setter
    private String number;

    @Column
    @Getter
    @Setter
	private Double length;

    @Column
    @Getter
    @Setter
	private Double width;

    @Column
    @Getter
    @Setter
	private Double height;

    @Column
    @Getter
    @Setter
	private Double weight;

    @Getter
    @Setter
    private Comment comments;

    @Getter
    @OneToMany(mappedBy = "nomenclature", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Goods> goods;

}
