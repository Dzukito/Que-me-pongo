package ar.utn.dds.modelo;

import ar.utn.dds.Constantes;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

public class Evento implements AceptarSuegerenciaObservador{
    private Calendar horaComienzo;
    private Calendar horaTermino;
    private Ubicacion ubicacion;
    private Estilo estilo;
    private Atuendo atuendo;
    private ArrayList<Atuendo> atuendosSugeridos;
    private int tiempoAviso;


    public long duracion(){ return Duration.between(horaComienzo.toInstant(),horaTermino.toInstant()).toHours(); }
    public Estilo estilo(){
        return this.estilo;
    }
    public Calendar horaTermino(){
        return this.horaTermino;
    }
    public Calendar horaComienzo(){
        return this.horaComienzo;
    }
    public boolean estoyEnEseHorario(Evento evento) { return (this.horaComienzo.before(evento.horaTermino()) && this.horaTermino.after(evento.horaComienzo())); }
    public long diasRestantesParaElEvento(){ return Duration.between(Calendar.getInstance().toInstant(),horaComienzo.toInstant()).toDays(); }
    public Ubicacion ubicacion(){ return this.ubicacion; }
    Evento(Calendar horaComienzo, Calendar horaTermino, Ubicacion ubicacion, Estilo estilo){
        this.horaComienzo= horaComienzo;
        this.horaTermino = horaTermino;
        this.ubicacion = ubicacion;
        this.estilo = estilo;
        this.tiempoAviso = Constantes.proximidadEstandarEventoEnDiaz;
    }
    Evento(Calendar horaComienzo, Calendar horaTermino, Ubicacion ubicacion, Estilo estilo, int tiempoAviso){
        this.horaComienzo= horaComienzo;
        this.horaTermino = horaTermino;
        this.ubicacion = ubicacion;
        this.estilo = estilo;
        this.tiempoAviso = tiempoAviso;
    }
    Evento(Calendar horaComienzo, int duracion, Ubicacion ubicacion, Estilo estilo, int tiempoAviso){
        Calendar horaTermino2 = Calendar.getInstance();
        horaTermino2.add(Calendar.HOUR, duracion);
        this.horaComienzo= horaComienzo;
        this.horaTermino = horaTermino2;
        this.ubicacion = ubicacion;
        this.estilo = estilo;
        this.tiempoAviso = tiempoAviso;
    }

    @Override
    public void updateAceptarSugerencia(Atuendo atuendo) {
        this.atuendo = atuendo;
    }
}
