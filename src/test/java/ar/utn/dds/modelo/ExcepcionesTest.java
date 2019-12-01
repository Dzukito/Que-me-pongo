package ar.utn.dds.modelo;

import org.junit.Test;

import ar.utn.dds.excepciones.AlMenosUnColor;
import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;
import ar.utn.dds.excepciones.SoloTieneUnColor;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;

public class ExcepcionesTest {
	  Prenda prenda1, prenda2;
	  TipoPrenda pantalon, remera, zapatillas;
	  ArrayList<Color> blancoYNegro, azul;
	  Color vacio;
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

         vacio = null;
    }

    @Test(expected=SoloTieneUnColor.class) 
    public void soloUnColor() {
    	this.init();
    	prenda1 = new Prenda(pantalon, "PantalonAzulado", Color.Azul, Material.JEAN); //(solo tiene azul)
    	prenda1.getColorSecundario();
    	
    
    }

    //REVISAR TRY-CATCH del constructor Prenda. Para que funcione, el catch tiene que throwear la excepcion
    @Test(expected=ElMaterialNoPerteneceALaPrenda.class)
    public void materialEquivocado() { 	
    	this.init();
    	prenda2 = new Prenda(pantalon, "PantalonAzulado", Color.Azul, Material.PLASTICO); //(Plastico no es material)
    }

    @Test(expected=AlMenosUnColor.class)
    public void ningunColor() {
    	this.init();
    	new Prenda(zapatillas, "ZapatillasDC", vacio, Material.CUERO); //(sin colores)
    }
    
    

   
}
