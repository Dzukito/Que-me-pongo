package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.Inicializador;
import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.modelo.clima.Pronostico;
import ar.utn.dds.modelo.ropa.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;
@DisplayName("Test de la clase Atuendos")
public class AtuendoTest {
    Atuendo atuendo1, atuendo2, atuendo3, atuendo4;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2;
    ArrayList<Prenda> prendas1, prendas2, prendas3;
    Evento irATrabajar, irAlGym, entregaDDS;
    Usuario usuario;
    Pronostico pronostico;
    Calendar fechaDeHoyMas2Hora = Calendar.getInstance();
    Calendar fechaDeHoyMenos1Hora =  Calendar.getInstance();


    @Before
    public void setup() {
        materialRemera = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.LINO,Material.FRANELA,Material.ALGODON)));
        materialPantalon = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.MALLA,Material.JEAN,Material.CUERO)));
        materialCalzado = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.CUERO,Material.PLASTICO)));
        materialAccesorio = new HashSet<Material>(new ArrayList<Material>(Arrays.asList(Material.PLASTICO,Material.ACEROINOXIDABLE,Material.CUERO)));
        remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
        pantalonCorto = new TipoPrenda(Categoria.PARTEINFERIOR, "PantalonCorto", materialPantalon);
        top = new TipoPrenda(Categoria.TORSO, "Top", materialRemera);
        zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        zapatos = new TipoPrenda(Categoria.CALZADO, "Zapatos", materialCalzado);
        accesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
        bufanda = new TipoPrenda(Categoria.ACCESORIOS, "Bufanda", materialAccesorio);
        remera1 = new Prenda(remera, "RemeraDePandas", Color.Blanco,Color.Negro , Material.LINO);
        remera2 = new Prenda(top, "Top", Color.Blanco,Color.Negro, Material.ALGODON);
        pantalon1 = new Prenda(pantalon, "Pantalon1", Color.Azul, Material.JEAN);
        pantalon2 = new Prenda(pantalonCorto, "PantalonCorto", Color.Amarillo, Material.JEAN);
        zapatillas1 = new Prenda(zapatillas, "Zapatillas1",Color.Rojo,Color.Verde, Material.CUERO);
        zapatillas2 = new Prenda(zapatos, "Zapatos", Color.Blanco,Color.Negro, Material.CUERO);
        accesorio1 = new Prenda(accesorio, "Gorra", Color.Azul, Material.PLASTICO);
        accesorio2 = new Prenda(bufanda, "Bufanda", Color.Amarillo, Material.ACEROINOXIDABLE);
        prendas1 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1));
        prendas2 = new ArrayList<Prenda>(Arrays.asList(remera2,accesorio2,zapatillas2,pantalon2));
        prendas3 = new ArrayList<Prenda>(Arrays.asList(zapatillas1,pantalon1,remera2));
        fechaDeHoyMas2Hora = Calendar.getInstance();
        fechaDeHoyMenos1Hora =  Calendar.getInstance();
        fechaDeHoyMas2Hora.add(Calendar.HOUR, 2);
        fechaDeHoyMenos1Hora.add(Calendar.HOUR, -1);
        irATrabajar = new Evento(
                fechaDeHoyMenos1Hora,
                fechaDeHoyMas2Hora,
                new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar"),
                Estilo.ELEGANTE
        );
        irAlGym = new Evento(
                fechaDeHoyMenos1Hora,
                fechaDeHoyMas2Hora,
                new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar"),
                Estilo.DEPORTIVO
        );
        entregaDDS = new Evento(
                fechaDeHoyMenos1Hora,
                fechaDeHoyMas2Hora,
                new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar"),
                Estilo.ENTRECASA
        );
        usuario = new Usuario();
        pronostico = new Pronostico();
        atuendo1 = new Atuendo(prendas1);
        atuendo2 = new Atuendo(prendas1);
        atuendo3 = new Atuendo(prendas2);
        atuendo4 = new Atuendo(prendas3);
    }
    @Test
    @DisplayName("Test para verificar la creacion de un Atuendo")
    public void atuendoCreadoCorrectamente() {
        Atuendo atuendo = new Atuendo(prendas1);
        Assert.assertEquals(atuendo.getClass(), Atuendo.class);
    }
    @Test
    @DisplayName("Test para verificar el agregado de una prenda")
    public void agregarPrenda() {
        Atuendo atuendo = new Atuendo(prendas3);
        atuendo.agregarPrenda(accesorio1);
        assertTrue(atuendo.tengoPrenda(accesorio1));
    }
    @Test
    @DisplayName("Test para verificar el cambio de una prenda por otra")
    public void cambiarPrenda() {
        Atuendo atuendo = new Atuendo(prendas2);
        assertEquals(remera2,atuendo.getPrenda(remera2.getCategoria()));
        atuendo.cambiarPrenda(remera1);
        assertEquals(remera1,atuendo.getPrenda(remera1.getCategoria()));
    }
    @Test
    @DisplayName("Test para verificar que dos atuendos tienen exactamente las mismas prendas")
    public void somosIguales() {
        assertTrue(atuendo1.somosIguales(atuendo2));
    }
    @Test
    @DisplayName("Test para verificar el caso negativo del test anterior")
    public void noSomosIguales(){
        assertFalse(atuendo1.somosIguales(atuendo3));
    }
    @Test
    @DisplayName("Test para verificar que una prenda no pueda agregarse en un atuendo si alguna prenda no lo tiene de superponible")
    public void noPuedoAgregarPrenda(){

    }
    @Test
    @DisplayName("Test para verificar la superposición de prendas")
    public void agregarPrendaTeniendoEseTipoDePrenda(){
        atuendo1.agregarPrenda(accesorio2);
        assertTrue(atuendo1.tengoPrenda(accesorio2));
    }
    @Test
    @DisplayName("Test para verificar el nivel de calor de un atuendo")
    public void nivelDeCalor(){
        System.out.println("sdfasdfadsfsdf");
        Assert.assertEquals(atuendo1.nivelDeCalor(Categoria.TORSO),1);
    }
    @Test
    @DisplayName("Test para verificar que un atuendo se califique,(que se agrega la calificación al atuendo)")
    public void calificarAtuendoSinDatos() {
        CalificacionAtuendo calificacionAtuendo = new CalificacionAtuendo();
        atuendo1.addCalificacion(calificacionAtuendo);
        Assert.assertEquals(atuendo1.getCalificaciones().get(0).getClass(), calificacionAtuendo.getClass());
    }
    @Test
    @DisplayName("Test para verificar que un atuendo se califique correctamente")
    public void calificarAtuendoConDatos(){
        CalificacionAtuendo calificacionAtuendo = new CalificacionAtuendo(pronostico,irATrabajar,usuario,atuendo1);
        atuendo1.addCalificacion(calificacionAtuendo);
        Assert.assertEquals(atuendo1.getCalificaciones().get(0).getClass(), calificacionAtuendo.getClass());
    }
    @Test
    @DisplayName("Test para verificar que un atuendo se califique correctamente")
    public void calificarAtuendoConDatosConValor(){
        CalificacionAtuendo calificacionAtuendo = new CalificacionAtuendo(pronostico,irATrabajar,usuario,atuendo1);
        atuendo1.addCalificacion(calificacionAtuendo);
        Assert.assertEquals(atuendo1.getCalificaciones().get(0).getClass(), calificacionAtuendo.getClass());
    }
    @Test
    @DisplayName("Test para verificar el promedio de las calificaciones de atuendo")
    public void promedioCalificacionesAtuendo(){

    }

}