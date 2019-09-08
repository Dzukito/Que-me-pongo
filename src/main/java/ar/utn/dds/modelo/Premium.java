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
    	
    	LectorConfig lc= new LectorConfig();
    	Properties constantes=lc.leerConfig();
    	
    	int maxPrendasGuarda= Integer.valueOf(constantes.getProperty("maximoPrendasGuardaropa"));
    	//hay que pasarlo a int porque los archivos properties siempre te devuelven Strings :P
       	 
    	
        usuario.cambiarMembrecia(new Gratuito());
        usuario.misRoperos().forEach(ropero->ropero.bloquearExcedente(maxPrendasGuarda)); //uso la propiedad 
        }

    Premium(){}
}
