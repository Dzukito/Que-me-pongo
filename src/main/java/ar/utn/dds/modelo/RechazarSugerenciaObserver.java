package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class RechazarSugerenciaObserver {
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
    RechazarSugerenciaObserver(){
        this.observadores = new ArrayList<RechazarSugerenciaObservador>(){};
    }
}

