package ar.utn.dds.modelo;

public abstract class Membrecia {
   public void cambiarMembrecia(Membrecia membrecia, Usuario usuario){
       usuario.cambiarMembrecia(membrecia);
   }
   public void agregarPrenda(Prenda prenda, Usuario usuario, int i){
       usuario.agregarPreda(prenda,usuario.guardaropa(i));
   }
   public void agregarRopero(Guardaropa guardaropa, Usuario usuario){
       usuario.agregarRopero(guardaropa);
   }
   public abstract void cambiarAPremium(Usuario usuario);
   public abstract void cambiarAGratuito(Usuario usuario);
}
