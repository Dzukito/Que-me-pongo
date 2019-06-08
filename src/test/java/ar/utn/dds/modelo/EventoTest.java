package ar.utn.dds.modelo;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;

public class EventoTest {
    Ubicacion buenosAires;
    Calendar fechaDeHoy, fechaDeHoyMas1Hora, fechaDeHoyMas2Hora, fechaDeHoyMenos1Hora, fechaDeHoyMenos2Hora,pasadomaniana,maniana,ayer,anteAyer;
    Evento irATrabajar, irAlGym, entreCasa, irALaFacu;
    @Before
    public void setup(){
        buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
        pasadomaniana = Calendar.getInstance();
        maniana = Calendar.getInstance();
        ayer = Calendar.getInstance();
        anteAyer = Calendar.getInstance();
        pasadomaniana.add(Calendar.DAY_OF_YEAR,2);
        maniana.add(Calendar.DAY_OF_YEAR,1);
        anteAyer.add(Calendar.DAY_OF_YEAR,-2);
        ayer.add(Calendar.DAY_OF_YEAR,-1);
        fechaDeHoyMas1Hora =  Calendar.getInstance();
        fechaDeHoyMas2Hora = Calendar.getInstance();
        fechaDeHoyMenos1Hora =  Calendar.getInstance();
        fechaDeHoyMenos2Hora =  Calendar.getInstance();
        fechaDeHoyMas1Hora.add(Calendar.HOUR, 1);
        fechaDeHoyMas2Hora.add(Calendar.HOUR, 2);
        fechaDeHoyMenos1Hora.add(Calendar.HOUR, -1);
        fechaDeHoyMenos2Hora.add(Calendar.HOUR,-2);
        fechaDeHoy = Calendar.getInstance();
        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires,Estilo.DEPORTIVO);
        irATrabajar = new Evento(fechaDeHoyMenos1Hora,fechaDeHoyMas1Hora,buenosAires,Estilo.ELEGANTE);
        irALaFacu = new Evento(pasadomaniana,pasadomaniana,buenosAires,Estilo.ELEGANTSPORT);
        entreCasa = new Evento(fechaDeHoyMenos2Hora,fechaDeHoy,buenosAires,Estilo.NORMAL);
    }

    @Test
    public void duracion() {
        setup();
        assertEquals(2,irAlGym.duracion());
    }

    @Test
    public void estoyEnEseHorario() {
        setup();
        assertTrue(irAlGym.estoyEnEseHorario(irATrabajar));
    }

    @Test
    public void diasRestantesParaElEvento() {
        setup();
        assertEquals(2,irALaFacu.diasRestantesParaElEvento());
    }
    @Test
    public void esHoy(){
        setup();
        assertEquals(0,irAlGym.diasRestantesParaElEvento());
    }
}