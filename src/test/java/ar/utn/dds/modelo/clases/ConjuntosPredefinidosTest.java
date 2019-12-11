package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.Inicializador;
import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.modelo.clases.ConjuntosPredefinidos;
import ar.utn.dds.modelo.ropa.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
@DisplayName("Test de la clase ConjuntosPredefinidos")
public class ConjuntosPredefinidosTest {
    TipoPrenda tipoRemera, tipoRemeraTop;
    HashSet<Material> materialRemera;
    ConjuntosPredefinidos conjuntoRemeraTop, conjuntoRemera;
    ArrayList<ConjuntosPredefinidos> conjuntosGuardaRopa;
    ArrayList<Color> blancoYNegro;
    Prenda prenda;
    ArrayList<Prenda> prendas1;
    Atuendo atuendo;
    @Before
    public void setup(){
        materialRemera = new HashSet<Material>(Arrays.asList(Material.LINO,Material.FRANELA,Material.ALGODON));
        tipoRemera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        tipoRemeraTop = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        conjuntoRemeraTop = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(tipoRemeraTop)));
        conjuntoRemera = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(tipoRemera)));
        conjuntosGuardaRopa = new ArrayList<ConjuntosPredefinidos>(Arrays.asList(conjuntoRemera,conjuntoRemeraTop));
        blancoYNegro = new ArrayList<Color>(Arrays.asList(Color.Blanco,Color.Negro));
        prenda = new Prenda(tipoRemeraTop, "RemeraDePandas", Color.Blanco,Color.Negro, Material.LINO,Estilo.NORMAL);
        prendas1 = new ArrayList<Prenda>(Arrays.asList(prenda));
        atuendo = new Atuendo(prendas1);
    }

    @Test
    @DisplayName("Test para verificar que el conjunto se cree adecuadamente")
    public void crearConjunto() {
        TipoPrenda remera = tipoRemera;
        ConjuntosPredefinidos conjuntoRemera = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(remera)));
        Assert.assertEquals(conjuntoRemera.getClass(), ConjuntosPredefinidos.class);
    }
    @Test
    @DisplayName("Test para verificar que dos conjuntos tienen los mismos tipos de prendas")
    public void mismoConjunto() {
        Assert.assertTrue(conjuntoRemeraTop.mismoConjunto(conjuntoRemera));
    }
    @Test
    @DisplayName("Test para verificar que el guardaropa ya tenga ese tipo de conjunto, con los mismos tipos de prendas")
    public void yaExisteConjunto() {
        Assert.assertTrue(conjuntoRemeraTop.yaExisteConjunto(conjuntosGuardaRopa));
    }
    @Test
    @DisplayName("Test para verificar que un atuendo pertenesca a un conjunto, quiere decir que ese atuendo tiene los tipos de prenda del conjunto")
    public void esteAtuendoEsMiConjunto(){
        Assert.assertTrue(conjuntoRemera.esteAtuendoEsmiConjunto(atuendo));
    }
    @Test
    @DisplayName("Test para verificar que dos conjuntos tienen los mismos niveles de calor por cada categoria")
    public void mismoNivelDeCalor() {
        Assert.assertTrue(conjuntoRemeraTop.mismoNivelDeCalor(conjuntoRemera.getNivelesDeCalor()));
    }

}