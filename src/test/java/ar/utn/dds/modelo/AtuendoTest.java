package ar.utn.dds.modelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class AtuendoTest {
    Atuendo atuendo1, atuendo2, atuendo3, atuendo4;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos;
    ArrayList<Color> blancoYNegro, azul, amarillo, rojoYVerde;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2;
    ArrayList<Prenda> prendas1, prendas2, prendas3;

    @Before
    public void setup() {
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
        remera2 = new Prenda(top, "Top", blancoYNegro, Material.ALGODON);
        pantalon1 = new Prenda(pantalon, "Pantalon1", azul, Material.JEAN);
        pantalon2 = new Prenda(pantalonCorto, "PantalonCorto", amarillo, Material.JEAN);
        zapatillas1 = new Prenda(zapatillas, "Zapatillas1", rojoYVerde, Material.CUERO);
        zapatillas2 = new Prenda(zapatos, "Zapatos", blancoYNegro, Material.CUERO);
        accesorio1 = new Prenda(accesorio, "Gorra", azul, Material.PLASTICO);
        accesorio2 = new Prenda(bufanda, "Bufanda", amarillo, Material.ACEROINOXIDABLE);
        prendas1 = new ArrayList<Prenda>();
        prendas1.add(remera1);
        prendas1.add(accesorio1);
        prendas1.add(zapatillas1);
        prendas1.add(pantalon1);
        prendas2 = new ArrayList<Prenda>();
        prendas2.add(remera2);
        prendas2.add(accesorio2);
        prendas2.add(zapatillas2);
        prendas2.add(pantalon2);
        prendas3 = new ArrayList<Prenda>();
        prendas3.add(zapatillas1);
        prendas3.add(pantalon1);
        prendas3.add(remera2);
        atuendo1 = new Atuendo(prendas1);
        atuendo2 = new Atuendo(prendas1);
        atuendo3 = new Atuendo(prendas2);
        atuendo4 = new Atuendo(prendas3);
    }
    @Test
    public void nivelDeCalor(){
        Assert.assertEquals(atuendo1.nivelDeCalor(Categoria.TORSO),1);
    }

    @Test
    public void cambiarPrenda() {
        this.setup();
        assertEquals(remera2,atuendo3.getPrenda(remera2.categoria()));
        atuendo3.cambiarPrenda(remera1);
        assertEquals(remera1,atuendo1.getPrenda(remera1.categoria()));
    }

    @Test
    public void somosIguales() {
        this.setup();
        assertTrue(atuendo1.somosIguales(atuendo2));
    }
    @Test
    public void noSomosIguales(){
        this.setup();
        assertFalse(atuendo1.somosIguales(atuendo3));
    }
    @Test
    public void agregarPrenda() {
        this.setup();
        atuendo4.agregarPrenda(accesorio1);
        assertTrue(atuendo4.tengoPrenda(accesorio1));
    }
    @Test
    public void agregarPrendaTeniendoEseTipoDePrenda(){
        this.setup();
        atuendo1.agregarPrenda(accesorio2);
        assertTrue(atuendo1.tengoPrenda(accesorio2));
    }
}