package ar.utn.dds.modelo;

import java.time.Duration;
import java.util.Calendar;

public class Evento {
    private Calendar horaComienzo;
    private Calendar horaTermino;
    private Ubicacion ubicacion;
    private Estilo estilo;

    public long duracion(){
        return Duration.between(horaComienzo.toInstant(),horaTermino.toInstant()).toHours();
    }

    public Estilo estilo(){
        return this.estilo;
    }

    public Calendar horaTermino(){
        return this.horaTermino;
    }

    public Calendar horaComienzo(){
        return this.horaComienzo;
    }

    public boolean estoyEnEseHorario(Evento evento) { return (this.horaComienzo.after(evento.horaTermino()) && this.horaTermino.before(evento.horaComienzo())); }

    public Calendar getHoraComienzo(){
        return this.horaComienzo;
    }
    public Calendar getHoraTermino(){
        return this.horaTermino;
    }
}
