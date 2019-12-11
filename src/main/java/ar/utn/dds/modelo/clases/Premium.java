package ar.utn.dds.modelo.clases;

import ar.utn.dds.excepciones.LectorConfig;

import java.util.Properties;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Premium")
public class Premium extends Membrecia {

    @Override
    public void cambiarAPremium(Usuario usuario) {
    }
    @Override
    public void cambiarAGratuito(Usuario usuario) {
    	LectorConfig lc= new LectorConfig();
    	Properties constantes=lc.leerConfig();
    	int maxPrendasGuarda= Integer.valueOf(constantes.getProperty("maximoPrendasGuardaropa"));
    	//hay que pasarlo a int porque los archivos properties siempre te devuelven Strings :P
        usuario.setMembrecia(new Gratuito());
        usuario.getRoperos().forEach(ropero->ropero.bloquearExcedente(maxPrendasGuarda)); //uso la propiedad
    }
    Premium(){}
}
