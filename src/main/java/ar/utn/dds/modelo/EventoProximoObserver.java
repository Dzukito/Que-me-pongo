package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class EventoProximoObserver {
    private List<EventoProximoObservador> observadores;
    public  void attach(EventoProximoObservador observador){
        this.observadores.add(observador);
    }
    public  void deattach(EventoProximoObservador observador){
        this.observadores.remove(observador);
    }
    public void notifyEventoProximoObserver(Pronostico pronostico, Evento evento, Usuario usuario){
        this.observadores.forEach(observador -> observador.updateEventoProximo(pronostico, evento, usuario) );
    }
    EventoProximoObserver(){
        this.observadores = new ArrayList<EventoProximoObservador>(){};
    }
}
