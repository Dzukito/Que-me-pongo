package ar.utn.dds.modelo;

import org.junit.Test;

import ar.utn.dds.excepciones.AlMenosUnColor;
import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;
import ar.utn.dds.excepciones.SoloTieneUnColor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;

public class ExcepcionesTest {
	  Prenda prenda1, prenda2;
	  TipoPrenda pantalon, remera, zapatillas;
	  ArrayList<Color> blancoYNegro, azul, vacio;
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
         
         vacio = new ArrayList<Color>();
         
         
         
        prenda1 = new Prenda(pantalon, "PantalonAzulado", azul, Material.PLASTICO);
        prenda2 = new Prenda(zapatillas, "ZapatillasDC", vacio, Material.CUERO);
         
    }

    @Test(expected=SoloTieneUnColor.class) 
    public void soloUnColor() {
    	this.init();
    	prenda1.colorSecundario();
    	
    
    }

    @Test(expected=ElMaterialNoPerteneceALaPrenda.class)
    public void materialEquivocado() { 	
    }

    @Test(expected=AlMenosUnColor.class)
    public void ningunColor() {
    }
    
    
}
