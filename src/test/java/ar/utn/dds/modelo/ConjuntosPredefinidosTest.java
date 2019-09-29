package ar.utn.dds.modelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ConjuntosPredefinidosTest {
    TipoPrenda remeraTop, remeraNoTop;
    HashSet<Material> materialRemera;
    ConjuntosPredefinidos remeraTop1, remeraNoTop1;
    ArrayList<ConjuntosPredefinidos> conjuntosGuardaRopa;
    ArrayList<Color> blancoYNegro;
    Prenda prenda;
    ArrayList<Prenda> prendas1;
    Atuendo atuendo;
    @Before
    public void setup(){
        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialRemera.add(Material.FRANELA);
        materialRemera.add(Material.ALGODON);
        remeraTop = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        remeraNoTop = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        remeraTop1 = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(remeraTop)));
        remeraNoTop1 = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(remeraNoTop)));
        conjuntosGuardaRopa = new ArrayList<ConjuntosPredefinidos>(Arrays.asList(remeraTop1,remeraNoTop1));
        blancoYNegro = new ArrayList<Color>();
        blancoYNegro.add(Color.Blanco);
        blancoYNegro.add(Color.Negro);
        prenda = new Prenda(remeraTop, "RemeraDePandas", blancoYNegro, Material.LINO,Estilo.NORMAL);
        prendas1 = new ArrayList<Prenda>();
        prendas1.add(prenda);
        atuendo = new Atuendo(prendas1);
    }
    @Test
    public void mismoConjunto() {
        setup();
        Assert.assertTrue(remeraTop1.mismoConjunto(remeraNoTop1));
    }
    @Test
    public void yaExisteConjunto() {
        setup();
        Assert.assertTrue(remeraTop1.yaExisteConjunto(conjuntosGuardaRopa));
    }
    @Test
    public void esteAtuendoEsMiConjunto(){
        setup();
        Assert.assertTrue(remeraTop1.esteAtuendoEsmiConjunto(atuendo));
    }
}