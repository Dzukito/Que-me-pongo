package ar.utn.dds.modelo;

import java.util.Calendar;

public class EventoRepetitivo extends Evento {
    int cicloRepeticion;

    EventoRepetitivo(Calendar horaComienzo, Calendar horaTermino, Ubicacion ubicacion, Estilo estilo, int cicloRepeticion) {
        super(horaComienzo, horaTermino, ubicacion, estilo);
        this.cicloRepeticion = cicloRepeticion;
    }

}
