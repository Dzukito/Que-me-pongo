package ar.utn.dds.modelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import static org.junit.Assert.*;

public class EventoProximoObserverTest {
    Ubicacion buenosAires;
    Calendar fechaDeHoy, fechaDeHoyMas2Hora;
    Evento irATrabajar, irAlGym, irALaFacu;
    Usuario usuario1;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos;
    ArrayList<Color> blancoYNegro, azul, amarillo, rojoYVerde;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2;
    ArrayList<Prenda> prendas1, prendas2, prendas3;
    Guardaropa ropero1, ropero2, ropero3;
    Meteorologo meteorologo;
    @Before
    public void setup(){
        buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
        fechaDeHoyMas2Hora = Calendar.getInstance();
        fechaDeHoyMas2Hora.add(Calendar.HOUR, 2);
        fechaDeHoy = Calendar.getInstance();
        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires,Estilo.DEPORTIVO);
        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialPantalon = new HashSet<Material>();
        materialPantalon.add(Material.LINO);
        materialCalzado = new HashSet<Material>();
        materialCalzado.add(Material.LINO);
        materialAccesorio = new HashSet<Material>();
        materialAccesorio.add(Material.LINO);
        pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
        pantalonCorto = new TipoPrenda(Categoria.PARTEINFERIOR, "PantalonCorto", materialPantalon);
        remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        top = new TipoPrenda(Categoria.TORSO, "Top", materialRemera);
        zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        zapatos = new TipoPrenda(Categoria.CALZADO, "Zapatos", materialCalzado);
        accesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
        bufanda = new TipoPrenda(Categoria.ACCESORIOS, "Bufanda", materialAccesorio);
        blancoYNegro = new ArrayList<Color>();
        blancoYNegro.add(Color.Blanco);
        blancoYNegro.add(Color.Negro);
        azul = new ArrayList<Color>();
        azul.add(Color.Azul);
        amarillo = new ArrayList<Color>();
        amarillo.add(Color.Amarillo);
        rojoYVerde = new ArrayList<Color>();
        rojoYVerde.add(Color.Rojo);
        rojoYVerde.add(Color.Verde);
        remera1 = new Prenda(remera, "RemeraDePandas", blancoYNegro, Material.LINO);
        remera2 = new Prenda(top, "Top", blancoYNegro, Material.LINO);
        pantalon1 = new Prenda(pantalon, "Pantalon1", azul, Material.LINO);
        pantalon2 = new Prenda(pantalonCorto, "PantalonCorto", amarillo, Material.LINO);
        zapatillas1 = new Prenda(zapatillas, "Zapatillas1", rojoYVerde, Material.LINO);
        zapatillas2 = new Prenda(zapatos, "Zapatos", blancoYNegro, Material.LINO);
        accesorio1 = new Prenda(accesorio, "Gorra", azul, Material.LINO);
        accesorio2 = new Prenda(bufanda, "Bufanda", amarillo, Material.LINO);
        prendas1 = new ArrayList<Prenda>();
        prendas1.add(remera1);
        prendas1.add(accesorio1);
        prendas1.add(zapatillas1);
        prendas1.add(pantalon1);
        prendas2 = new ArrayList<Prenda>();
        prendas2.add(remera1);
        prendas2.add(accesorio1);
        prendas2.add(zapatillas1);
        prendas2.add(pantalon1);
        prendas2.add(remera2);
        prendas2.add(accesorio2);
        prendas2.add(zapatillas2);
        prendas2.add(pantalon2);
        prendas3 = new ArrayList<Prenda>();
        prendas3.add(remera1);
        prendas3.add(accesorio1);
        prendas3.add(zapatillas1);
        prendas3.add(pantalon1);
        prendas3.add(remera2);
        ropero1 = new Guardaropa();
        ropero1.agregarPrendas(prendas1);
        ropero2 = new Guardaropa();
        ropero2.agregarPrendas(prendas2);
        ropero3 = new Guardaropa();
        ropero3.agregarPrendas(prendas3);
        meteorologo = new MeteorologoWeatherAdapter();
        usuario1 = new Usuario("Martin", new ArrayList<Guardaropa>());
        usuario1.agregarMeteorolo(meteorologo);
        usuario1.agregarRopero(ropero1);
        usuario1.agregarRopero(ropero2);
        usuario1.agregarRopero(ropero3);
        usuario1.agregarEvento(irAlGym);
      }
    @Test
    public void notifyEventoProximoObserver() {
        EventoProximoObserver observer = new EventoProximoObserver();
        observer.attach(usuario1);
        observer.notifyEventoProximoObserver(irAlGym);
        Assert.assertTrue(irAlGym.tengoSugerencias());
    }
}