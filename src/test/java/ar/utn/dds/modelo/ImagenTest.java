package ar.utn.dds.modelo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;

public class ImagenTest {
	  Prenda prenda1;
	  TipoPrenda remera;
	  ArrayList<Color> blancoYNegro;
	  HashSet<Material> materialRemera;
	  String standar, zebra;
	  Fotografo normaliz;

    @Before
    public void init(){
    	   	    	
    	 materialRemera = new HashSet<Material>();
         materialRemera.add(Material.LINO);
         
         remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera,null,"Imagenes/defaultTorso.jpg");
               
         blancoYNegro = new ArrayList<Color>();
         blancoYNegro.add(Color.Blanco);
         blancoYNegro.add(Color.Negro);
        
         prenda1 = new Prenda(remera, "RemeraZebra", Color.Blanco, Color.Negro, Material.LINO);
         
         standar= "Imagenes/defaultTorso.jpg";
         zebra= "Imagenes/test.jpg";
         normaliz= new Fotografo();
         
         
    }
    @Test
    public void sinImagen(){
    	this.init();

    	Assert.assertEquals(0,prenda1.getFotografo().imagenes().size());
    }
    
    @Test
    public void agregarImagenPrenda(){ 
    	this.init();
    	prenda1.getFotografo().cargarImagen(zebra,normaliz);
        
        System.out.print(prenda1.getFotografo().imagenes());
       
       Assert.assertTrue(prenda1.getFotografo().imagenes().contains("Imagenes/Imagenes-test.jpg"));
      

    }
}
