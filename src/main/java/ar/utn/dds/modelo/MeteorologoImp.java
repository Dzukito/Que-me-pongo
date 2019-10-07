package ar.utn.dds.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="meteorologo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoMeteorologo",discriminatorType = DiscriminatorType.INTEGER)
public abstract class MeteorologoImp extends EntidadPersistente{
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_meteorologo",referencedColumnName = "id")
	public List<Pronostico> pronosticosPorCincoDias;

}
