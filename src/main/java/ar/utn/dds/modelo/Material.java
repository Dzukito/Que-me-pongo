package ar.utn.dds.modelo;
import javax.persistence.*;

@Entity
@Table(name="material")
public enum Material {
    PLASTICO("Plastico"),
    CUERO("Cuero"),
    ALGODON("Algodon"),
    ACEROINOXIDABLE("Acero Inoxidable"),
    MALLA("Malla"),
    LINO("Lino"),
    JEAN("JEAN"),
    GABARDINA("Lino"),
    FRANELA("Franela");
	
	@Id
	@GeneratedValue
	private long id_material;

	@Column(name = "nombre")
    private final String nombre;
	
    Material(String nombre) {
        this.nombre = nombre;
    }
}
