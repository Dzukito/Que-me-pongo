package ar.utn.dds.modelo.sugerencia;

import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.modelo.interfaces.AceptarSuegerenciaObservador;
import ar.utn.dds.modelo.interfaces.OpcionPorSugerenciaCommand;

import java.util.ArrayList;
import java.util.List;

public class AceptarSugerenciaObserver implements OpcionPorSugerenciaCommand {
    private List<AceptarSuegerenciaObservador> observadores;

    public void attach(AceptarSuegerenciaObservador observador) {
        this.observadores.add(observador);
    }
    public void deattach(AceptarSuegerenciaObservador observador) {
        this.observadores.remove(observador);
    }
    public void notifyAceptarSugerencia(Atuendo atuendo) {
        this.observadores.forEach(observador -> observador.updateAceptarSugerencia(atuendo));
    }
    public void notifyDesAceptarSugerencia(Atuendo atuendo, Atuendo atuendoViejo){
        this.observadores.forEach(observador -> observador.downdateAceptarSugerencia(atuendo, atuendoViejo));
    }
	@Override
	public void ejecutar(Atuendo atuendo) {
		this.notifyAceptarSugerencia(atuendo);
	}
    //Constructores-------------------------------------------
    public AceptarSugerenciaObserver(){
        this.observadores = new ArrayList<AceptarSuegerenciaObservador>(){};
    }
}