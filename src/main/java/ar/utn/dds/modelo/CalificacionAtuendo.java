package ar.utn.dds.modelo;

public class CalificacionAtuendo {
    private Pronostico pronostico;
    private Evento evento;
    private Usuario usuario;
    private int calificacion;

    CalificacionAtuendo(){}
    CalificacionAtuendo(Pronostico pronostico, Evento evento, Usuario usuario, int calificacion){
        this.pronostico = pronostico;
        this.evento = evento;
        this.calificacion = calificacion;
        this.usuario = usuario;
    }
}
