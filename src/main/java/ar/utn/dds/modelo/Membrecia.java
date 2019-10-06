package ar.utn.dds.modelo;

import javax.persistence.*;

@Entity
@Table(name = "membrecia")
public abstract class Membrecia {
	@Id
	private long id_membrecia;
	
   public void agregarPrenda(Prenda prenda, Usuario usuario, int i){
       usuario.agregarPreda(prenda,usuario.getGuardaropa(i));
   }
   public void agregarRopero(Guardaropa guardaropa, Usuario usuario){
       usuario.agregarRopero(guardaropa);
   }
   public abstract void cambiarAPremium(Usuario usuario);
   public abstract void cambiarAGratuito(Usuario usuario);
}
