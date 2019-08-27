package ar.utn.dds.modelo;

import java.util.Calendar;

public class EventoRepetitivo extends Evento {

    EventoRepetitivo(Calendar horaComienzo, Calendar horaTermino, Ubicacion ubicacion, Estilo estilo) {
        super(horaComienzo, horaTermino, ubicacion, estilo);

    }
}
