package ar.utn.dds.modelo;
import javax.persistence.*;

@Entity
@Table(name="estilo")
public enum Estilo {
    ELEGANTE,
    ELEGANTSPORT,
    DEPORTIVO,
    ENTRECASA,
    NAVIDENIO,
    NORMAL,
    PLAYERO;
	
	
	//lo transformo en clase porque prenda necesita un array de estilos, pero hibernate no permite del todo mapear array de enums
	@Id
	@GeneratedValue
	private long id_estilo;

	@Column(name = "estilo")
    private Estilo estilo;
	
	 public Estilo getEstilo(){
	        return this.estilo;}
	
}
