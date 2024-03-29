package ar.utn.dds.modelo.clases;

import java.util.Properties;

import ar.utn.dds.excepciones.CantidadDeArmariosMaximo;
import ar.utn.dds.excepciones.CantidadDePrendasMaxima;
import ar.utn.dds.excepciones.LectorConfig;
import ar.utn.dds.modelo.ropa.Prenda;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Gratuito")
public class Gratuito extends Membrecia {

    @Override
    public void agregarPrenda(Prenda prenda, Usuario usuario, int i) {
    	
    	LectorConfig lc= new LectorConfig();
    	Properties constantes=lc.leerConfig();
    	int maxPrendasGuarda= Integer.valueOf(constantes.getProperty("maximoPrendasGuardaropa"));
    	int maxPrendasTipo= Integer.valueOf(constantes.getProperty("maximoPrendasPorTipo"));
    	
        if (usuario.cantidadPrendas(i) < maxPrendasGuarda){ //uso las propiedades
            if (usuario.cantidadDePrendasPorCategoria(prenda.getCategoria(),i) < maxPrendasTipo){
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
    	
    	LectorConfig lc= new LectorConfig();
    	Properties constantes=lc.leerConfig();
    	
    	int maxGuarda= Integer.valueOf(constantes.getProperty("maximoGuardaropa"));
    	
    	
        if (usuario.cantidadDeRoperos() < maxGuarda){ 
           super.agregarRopero(guardaropa,usuario);
        }else{
            throw new CantidadDeArmariosMaximo();
        }
    }

    @Override
    public void cambiarAPremium(Usuario usuario) {
    	usuario.setMembrecia(new Premium());
    	usuario.getRoperos().forEach(ropero->ropero.desbloquearTodo());
    }

    @Override
    public void cambiarAGratuito(Usuario usuario) {
    	//tiene sentido?
    }

    public Gratuito(){}
}