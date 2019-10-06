package ar.utn.dds.modelo;
import javax.persistence.*;

@Entity
@Table(name="id_estilo")
public enum Estilo {
    ELEGANTE,
    ELEGANTSPORT,
    DEPORTIVO,
    ENTRECASA,
    NAVIDENIO,
    NORMAL,
    PLAYERO;
}
