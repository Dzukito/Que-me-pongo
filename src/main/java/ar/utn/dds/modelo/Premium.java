package ar.utn.dds.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import ar.utn.dds.Constantes;

public class Premium extends Membrecia{

    @Override
    public void cambiarAPremium(Usuario usuario) {
    }

    @Override
    public void cambiarAGratuito(Usuario usuario) {
    	
    	Properties constantes = new Properties(); /*variable que lee las propiedades*/
    	try { constantes.load(new FileReader("src/main/java/ar/utn/dds/config.properties"));
		}catch (IOException e) {
			 System.out.println("Error al abrir archivo de configuracion");
		} 
    	
    	int maxPrendasGuarda= Integer.valueOf(constantes.getProperty("maximoPrendasGuardaropa"));
    	//hay que pasarlo a int porque los archivos properties siempre te devuelven Strings :P
       	 
    	
        usuario.cambiarMembrecia(new Gratuito());
        usuario.misRoperos().forEach(ropero->ropero.bloquearExcedente(maxPrendasGuarda)); //uso la propiedad 
        }

    Premium(){}
}
