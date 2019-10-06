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
	
	@Id
	@GeneratedValue
	private long id_estilo;
}
