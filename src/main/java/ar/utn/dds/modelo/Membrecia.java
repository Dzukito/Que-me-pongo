package ar.utn.dds.modelo;

import javax.persistence.*;

@Entity
@Table(name="membrecia")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoMembrecia",discriminatorType = DiscriminatorType.STRING)
public abstract class Membrecia {
	@Id
	@GeneratedValue
	private long id_membrecia;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	public long getId_membrecia() {
		return id_membrecia;
	}
	public void setId_membrecia(long id_membrecia) {
		this.id_membrecia = id_membrecia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void agregarPrenda(Prenda prenda, Usuario usuario, int i){
	   usuario.agregarPreda(prenda,usuario.getGuardaropa(i));
	}
	public void agregarRopero(Guardaropa guardaropa, Usuario usuario){
       usuario.agregarRopero(guardaropa);
	}
	public abstract void cambiarAPremium(Usuario usuario);
	public abstract void cambiarAGratuito(Usuario usuario);
}

