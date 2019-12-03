package ar.utn.dds.modelo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;

public class EventoTest {
    Ubicacion buenosAires;
    Calendar fechaDeHoy, fechaDeHoyMas1Hora, fechaDeHoyMas2Hora, fechaDeHoyMenos1Hora, fechaDeHoyMenos2Hora,pasadomaniana,maniana,ayer,anteAyer;
    Evento irATrabajar, irAlGym, entreCasa, irALaFacu;
    Atuendo atuendo1;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda pantalon, remera, zapatillas, accesorio;
    ArrayList<Color>  azul;
    Prenda remera1,pantalon1,zapatillas1, accesorio1;
    ArrayList<Prenda> prendas1;
    @Before
    public void setup(){
        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialRemera.add(Material.FRANELA);
        materialRemera.add(Material.ALGODON);
        materialPantalon = new HashSet<Material>();
        materialPantalon.add(Material.MALLA);
        materialPantalon.add(Material.JEAN);
        materialPantalon.add(Material.CUERO);
        materialCalzado = new HashSet<Material>();
        materialCalzado.add(Material.CUERO);
        materialCalzado.add(Material.PLASTICO);
        materialAccesorio = new HashSet<Material>();
        materialAccesorio.add(Material.PLASTICO);
        materialAccesorio.add(Material.ACEROINOXIDABLE);
        materialAccesorio.add(Material.CUERO);
        remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
        zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        accesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
        azul = new ArrayList<Color>();
        azul.add(Color.Azul);
        remera1 = new Prenda(remera, "RemeraDePandas", Color.Azul, Material.LINO);
        pantalon1 = new Prenda(pantalon, "Pantalon1", Color.Azul, Material.JEAN);
        zapatillas1 = new Prenda(zapatillas, "Zapatillas1", Color.Azul, Material.CUERO);
        accesorio1 = new Prenda(accesorio, "Gorra", Color.Azul, Material.PLASTICO);
        prendas1 = new ArrayList<Prenda>();
        prendas1.add(remera1);
        prendas1.add(accesorio1);
        prendas1.add(zapatillas1);
        prendas1.add(pantalon1);
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
        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires,Estilo.DEPORTIVO);
        irATrabajar = new Evento(fechaDeHoyMenos1Hora,fechaDeHoyMas1Hora,buenosAires,Estilo.ELEGANTE);
        irALaFacu = new Evento(pasadomaniana,pasadomaniana,buenosAires,Estilo.ELEGANTSPORT);
        entreCasa = new Evento(fechaDeHoyMenos2Hora,fechaDeHoy,buenosAires,Estilo.NORMAL);
    }

    @Test
    public void duracion() {
        setup();
        //pongo un delta porque segun lo que tarden en correr los otros test, no toma que hay 2 horas de diferencia
        assertEquals(2,irAlGym.duracion(),1);
    }

    @Test
    public void estoyEnEseHorario() {
        setup();
        assertTrue(irAlGym.estoyEnEseHorario(irATrabajar));
    }

    @Test
    public void diasRestantesParaElEvento() {
        setup();
        //pongo un delta porque segun lo que tarden en correr los otros test, no toma que hay 2 dias de diferencia
        assertEquals(2,irALaFacu.diasRestantesParaElEvento(),1);
    }
    @Test
    public void esHoy(){
        setup();
        assertEquals(0,irAlGym.diasRestantesParaElEvento());
    }
    @Test
    public void agregarSugerencia(){
        setup();
        irAlGym.agregarSugerencia(atuendo1);
        Assert.assertTrue(irAlGym.tengoSugerencias());
    }
}