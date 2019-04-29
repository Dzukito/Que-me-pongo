package ar.utn.dds.modelo;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;

public class PrendaTest {
	  Prenda prenda1, prenda2, prenda3;
	  TipoPrenda pantalon, remera, zapatillas;
	  ArrayList<Color> blancoYNegro, azul, rojoYVerde;
	  HashSet<Material> materialRemera, materialPantalon, materialCalzado;
	   
	  
	
	
    @Before
    public void init(){
    	 materialRemera = new HashSet<Material>();
         materialRemera.add(Material.LINO);
         
         
         materialPantalon = new HashSet<Material>();
         materialPantalon.add(Material.JEAN);
         
         
         materialCalzado = new HashSet<Material>();
         materialCalzado.add(Material.CUERO);
         
         
         pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
         remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
         zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        
         
         blancoYNegro = new ArrayList<Color>();
         blancoYNegro.add(Color.Blanco);
         blancoYNegro.add(Color.Negro);
         azul = new ArrayList<Color>();
         azul.add(Color.Azul);
         rojoYVerde = new ArrayList<Color>();
         rojoYVerde.add(Color.Rojo);
         rojoYVerde.add(Color.Verde);
         
         prenda1 = new Prenda(remera, "RemeraZebra", blancoYNegro, Material.LINO);
         prenda2 = new Prenda(pantalon, "PantalonX", azul, Material.JEAN);
         prenda3 = new Prenda(zapatillas, "ZapatillasRaras", rojoYVerde, Material.CUERO);
         
    }

    @Test
    public void getMaterial() {
    	this.init();
    	Assert.assertEquals(Material.JEAN, prenda2.getMaterial());
    
    }

    @Test
    public void colorPrimario() {
    	this.init();
    	Assert.assertEquals(Color.Blanco, prenda1.colorPrimario());
    }

    @Test
    public void colorSecundario() {
    	this.init();
    	Assert.assertEquals(Color.Negro, prenda1.colorSecundario());
    }
    

    @Test
    public void categoria() {
    	this.init();
    	Assert.assertEquals("Zapatillas", prenda3.tipo());
    }
}
