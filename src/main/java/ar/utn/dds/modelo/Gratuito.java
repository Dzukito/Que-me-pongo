package ar.utn.dds.modelo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import ar.utn.dds.Constantes;
import ar.utn.dds.excepciones.CantidadDeArmariosMaximo;
import ar.utn.dds.excepciones.CantidadDePrendasMaxima;

public class Gratuito extends Membrecia {

    @Override
    public void agregarPrenda(Prenda prenda, Usuario usuario, int i) {
    	
    	Properties constantes = new Properties(); /*variable que lee las propiedades*/
    	try { constantes.load(new FileReader("src/main/java/ar/utn/dds/config.properties"));
		}catch (IOException e) {
			 System.out.println("Error al abrir archvio de configuracion");
		} 
    	
    	int maxPrendasGuarda= Integer.valueOf(constantes.getProperty("maximoPrendasGuardaropa"));
    	int maxPrendasTipo= Integer.valueOf(constantes.getProperty("maximoPrendasPorTipo"));
    	//hay que pasarlos a int porque los archivos properties siempre te devuelven Strings :P
    	
    	/*mi duda es: creamos una clase auxiliar que cargue todos los properties en variables
    	 * o hacemos asi de llamar al properties aca adentro y declaramos las variables que necesitamos?
    	 */
    	
    	
        if (usuario.cantidadPrendas(i) < maxPrendasGuarda){ //uso las propiedades
            if (usuario.cantidadDePrendasPorCategoria(prenda,i) < maxPrendasTipo){
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
    	
    	Properties constantes = new Properties(); /*variable que lee las propiedades*/
    	try { constantes.load(new FileReader("src/main/java/ar/utn/dds/config.properties"));
		}catch (IOException e) {
			 System.out.println("Error al abrir archvio de configuracion");
		} 
    	int maxGuarda= Integer.valueOf(constantes.getProperty("maximoGuardaropa"));
    	
    	
        if (usuario.cantidadDeRoperos() < maxGuarda){ 
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
