package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.ropa.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;
@DisplayName("Tests para los Eventos")
public class EventoTest {
    Ubicacion buenosAires;
    Calendar fechaDeHoy, fechaDeHoyMas1Hora, fechaDeHoyMas2Hora, fechaDeHoyMenos1Hora, fechaDeHoyMenos2Hora,pasadomaniana,maniana,ayer,anteAyer;
    Evento irATrabajar, irAlGym, entreCasa, irALaFacu, casorio;
    Atuendo atuendo1;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda tipoRemera, tipoPantalon, tipoZapatillas, tipoAccesorio;
    Prenda remera, pantalon, zapatillas, accesorio;
    ArrayList<Prenda> prendas1;
    @Before
    public void setup(){
        //Materiales prenda
        materialRemera = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.LINO,Material.FRANELA,Material.ALGODON)));
        materialPantalon = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.MALLA,Material.JEAN,Material.CUERO)));
        materialCalzado = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.PLASTICO)));
        materialAccesorio = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.PLASTICO,Material.ACEROINOXIDABLE,Material.CUERO)));
        //Tipos de prenda
        tipoRemera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        tipoPantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
        tipoZapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        tipoAccesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
        //Prendas
        remera = new Prenda(tipoRemera, "RemeraDePandas", Color.Azul, Material.LINO);
        pantalon = new Prenda(tipoPantalon, "Pantalon1", Color.Azul, Material.JEAN);
        zapatillas = new Prenda(tipoZapatillas, "Zapatillas1", Color.Azul, Material.CUERO);
        accesorio = new Prenda(tipoAccesorio, "Gorra", Color.Azul, Material.PLASTICO);
        //Lista de prendas
        prendas1 = new ArrayList<Prenda>(Arrays.asList(remera, accesorio, zapatillas, pantalon));
        atuendo1 = new Atuendo(prendas1);
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
        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.DEPORTIVO);
        casorio = new Evento(fechaDeHoyMenos1Hora,fechaDeHoyMas1Hora,buenosAires,Estilo.ELEGANTE);
        irATrabajar = new Evento(fechaDeHoyMenos1Hora,fechaDeHoyMas1Hora,buenosAires,Estilo.ELEGANTE);
        irALaFacu = new Evento(pasadomaniana,pasadomaniana,buenosAires,Estilo.ELEGANTSPORT);
        entreCasa = new Evento(fechaDeHoyMenos2Hora,fechaDeHoy,buenosAires,Estilo.NORMAL);
    }
    @Test
    @DisplayName("Test para verificar el correcto calculo de la duración de un evento")
    public void duracion() {
        setup();
        //pongo un delta porque segun lo que tarden en correr los otros test, no toma que hay 2 horas de diferencia
        assertEquals(2,irAlGym.duracion(),1);
    }
    @Test
    @DisplayName("Test para verificar hay dos eventos en la misma franja horaria")
    public void estoyEnEseHorario() {
        setup();
        assertTrue(irAlGym.estoyEnEseHorario(irATrabajar));
    }
    @Test
    @DisplayName("Test para verificar el correcto conteo de días que falta para un evento")
    public void diasRestantesParaElEvento() {
        setup();
        //pongo un delta porque segun lo que tarden en correr los otros test, no toma que hay 2 dias de diferencia
        assertEquals(2,irALaFacu.diasRestantesParaElEvento(),1);
    }
    @Test
    @DisplayName("Test para verificar hoy es la fecha del evento")
    public void esHoy(){
        setup();
        assertEquals(0,irAlGym.diasRestantesParaElEvento());
    }
    @Test
    @DisplayName("Test para verificar que se agregue un atuendo a un evento como sugerencia")
    public void agregarSugerencia(){
        setup();
        irAlGym.agregarSugerencia(atuendo1);
        Assert.assertTrue(irAlGym.tengoSugerencias());
    }
    @Test
    @DisplayName("Test para verificar que dos eventos tengan el mismo estilo")
    public void eventoSimilar(){
        Assert.assertTrue(irATrabajar.eventoSimilar(casorio));
    }
}