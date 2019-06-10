package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class AceptarSugerenciaObserver {
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
    AceptarSugerenciaObserver(){
        this.observadores = new ArrayList<AceptarSuegerenciaObservador>(){};
    }
}