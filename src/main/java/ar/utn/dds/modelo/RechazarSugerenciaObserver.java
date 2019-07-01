package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class RechazarSugerenciaObserver implements OpcionPorSugerenciaCommand{
    private List<RechazarSugerenciaObservador> observadores;

    public void attach(RechazarSugerenciaObservador observador) {
        this.observadores.add(observador);
    }

    public void deattach(RechazarSugerenciaObservador observador) {
        this.observadores.remove(observador);
    }

    public void notifyRechazarSugerencia(Atuendo atuendo) {
        this.observadores.forEach(observador -> observador.updateRechazarSugerencia(atuendo));
    }
    public void notifyDesRechazarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) {
        this.observadores.forEach(observador -> observador.downdateRechazarSugerencia(atuendo,atuendoViejo));
    }
    RechazarSugerenciaObserver(){
        this.observadores = new ArrayList<RechazarSugerenciaObservador>(){};
    }

	@Override
	public void ejecutar(Atuendo atuendo) {
		this.notifyRechazarSugerencia(atuendo);
	
	}
	
}

