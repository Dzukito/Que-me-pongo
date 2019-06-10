package ar.utn.dds.modelo;

public interface EventoProximoObservador {
    void updateEventoProximo(Pronostico pronostico, Evento evento, Usuario usuario);
}
