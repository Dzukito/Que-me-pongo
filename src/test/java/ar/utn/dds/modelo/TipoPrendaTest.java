package ar.utn.dds.modelo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TipoPrendaTest {
    Prenda prenda1, prenda2, prenda3, prenda4;
    TipoPrenda pantalon, remera, zapatillas,campera;
    ArrayList<Color> blancoYNegro, azul, rojoYVerde;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado;
    Set<TipoPrenda> superponiblesRemera;

    @Before
    public void init(){
        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialPantalon = new HashSet<Material>();
        materialPantalon.add(Material.JEAN);
        materialCalzado = new HashSet<Material>();
        materialCalzado.add(Material.CUERO);
        pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
        campera = new TipoPrenda(Categoria.TORSO, "campera", materialPantalon);
        superponiblesRemera = new HashSet<TipoPrenda>();
        superponiblesRemera.add(campera);
        remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera,superponiblesRemera,null);
        zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        blancoYNegro = new ArrayList<Color>();
        blancoYNegro.add(Color.Blanco);
        blancoYNegro.add(Color.Negro);
        azul = new ArrayList<Color>();
        azul.add(Color.Azul);
        rojoYVerde = new ArrayList<Color>();
        rojoYVerde.add(Color.Rojo);
        rojoYVerde.add(Color.Verde);
        prenda1 = new Prenda(remera, "RemeraZebra", Color.Blanco,Color.Negro, Material.LINO, Estilo.NORMAL);
        prenda2 = new Prenda(pantalon, "PantalonX", Color.Azul, Material.JEAN);
        prenda3 = new Prenda(zapatillas, "ZapatillasRaras", Color.Rojo,Color.Verde, Material.CUERO, Estilo.ELEGANTSPORT);
        prenda4 = new Prenda(campera, "Campera", Color.Azul, Material.JEAN);
    }
    @Test
    public void esSuperponible() {
        assertTrue(prenda1.esSuperponible(prenda4));
    }
    @Test
    public void noEsSuperponible() {
        assertFalse(prenda2.esSuperponible(prenda3));
    }


}
