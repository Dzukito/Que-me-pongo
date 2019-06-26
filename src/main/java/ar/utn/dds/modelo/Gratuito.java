package ar.utn.dds.modelo;

import ar.utn.dds.Constantes;
import ar.utn.dds.excepciones.CantidadDeArmariosMaximo;
import ar.utn.dds.excepciones.CantidadDePrendasMaxima;

public class Gratuito extends Membrecia {

    @Override
    public void agregarPrenda(Prenda prenda, Usuario usuario, int i) {
        if (usuario.cantidadPrendas(i) < Constantes.maximoPrendasGuardaropa){
            if (usuario.cantidadDePrendasPorCategoria(prenda,i) < Constantes.maximoPrendasPorTipo){
              super.agregarPrenda(prenda,usuario,i);
            }else{
                throw new CantidadDePrendasMaxima();
            }
        }else{
            throw new CantidadDePrendasMaxima();
        }
    }

    @Override
    public void agregarRopero(Guardaropa guardaropa, Usuario usuario) {
        if (usuario.cantidadDeRoperos() < Constantes.maximoGuardaropa){
           super.agregarRopero(guardaropa,usuario);
        }else{
            throw new CantidadDeArmariosMaximo();
        }
    }

    @Override
    public void cambiarAPremium(Usuario usuario) {
    	usuario.cambiarMembrecia(new Premium());
    	usuario.misRoperos().forEach(ropero->ropero.desbloquearTodo());
    }

    @Override
    public void cambiarAGratuito(Usuario usuario) {
    	//tiene sentido?
    }

    Gratuito(){}
}
