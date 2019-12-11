package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.ropa.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;

public class PrendaTest {
	  Prenda prenda1, prenda2, prenda3;
	  TipoPrenda pantalon, remera, zapatillas;
	  HashSet<Material> materialRemera, materialPantalon, materialCalzado;

    @Before
    public void init(){
         //Materiales
    	 materialRemera = new HashSet<Material>(Arrays.asList(Material.LINO));
         materialPantalon = new HashSet<Material>(Arrays.asList(Material.JEAN));
         materialCalzado = new HashSet<Material>(Arrays.asList(Material.CUERO));
         //Tipo de prenda
         pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
         remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
         zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
         //Prenda
         prenda1 = new Prenda(remera, "RemeraZebra", Color.Blanco, Color.Negro, Material.LINO, Estilo.NORMAL);
         prenda2 = new Prenda(pantalon, "PantalonX", Color.Azul, Material.JEAN);
         prenda3 = new Prenda(zapatillas, "ZapatillasRaras", Color.Rojo, Color.Verde, Material.CUERO, Estilo.ELEGANTSPORT);
    }

    @Test
    public void cambiarABloqueado(){
        assertTrue(prenda1.disponible());
        prenda1.bloquearse();
        assertFalse(prenda1.disponible());
    }
    @Test
    public void cambiarADesbloqueado(){
        assertTrue(prenda1.disponible());
        prenda1.bloquearse();
        assertFalse(prenda1.disponible());
        prenda1.desbloquearse();
        assertTrue(prenda1.disponible());
    }
    @Test
    public void getMaterial() {
    	this.init();
    	Assert.assertEquals(Material.JEAN, prenda2.getMaterial());
    }
    @Test
    public void colorPrimario() {
    	this.init();
    	Assert.assertEquals(Color.Blanco, prenda1.getColorPrimario());
    }
    @Test
    public void colorSecundario() {
    	this.init();
    	Assert.assertEquals(Color.Negro, prenda1.getColorSecundario());
    }
    @Test
    public void categoria() {
    	this.init();
    	Assert.assertEquals("Zapatillas", prenda3.tipo());
    }

}
