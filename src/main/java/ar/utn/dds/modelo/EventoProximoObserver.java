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
    public void notifyEventoProximoObserver(Evento evento){
    //    try {
            this.observadores.forEach(observador -> observador.generaAtuendosParaEvento(evento));
    //    }catch (Exception e){}
    }
    EventoProximoObserver(){
        this.observadores = new ArrayList<EventoProximoObservador>(){};
    }
}
