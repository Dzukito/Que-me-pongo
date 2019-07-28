package ar.utn.dds.modelo;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;

//Para las imagenes:
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//

public class ImagenTest {
	  Prenda prenda1;
	  TipoPrenda remera;
	  ArrayList<Color> blancoYNegro;
	  HashSet<Material> materialRemera;
	  String standar, zebra;
	  AdministradorDeImagenes normaliz;
	   
	
    @Before
    public void init(){
    	   	    	
    	 materialRemera = new HashSet<Material>();
         materialRemera.add(Material.LINO);
         
         remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera,null,"Imagenes/defaultTorso.jpg");
               
         blancoYNegro = new ArrayList<Color>();
         blancoYNegro.add(Color.Blanco);
         blancoYNegro.add(Color.Negro);
        
         prenda1 = new Prenda(remera, "RemeraZebra", blancoYNegro, Material.LINO);
         
         standar= "Imagenes/defaultTorso.jpg";
         zebra= "Imagenes/test.jpg";
         normaliz= new AdministradorDeImagenes();
         
         
    }


    @Test
    public void sinImagen(){
    	
    	this.init();
    	Assert.assertEquals(0,prenda1.imagenes().size());
    	
    }
    
    @Test
    public void agregarImagenPrenda(){ 
    	this.init();
    	prenda1.cargarImagen(zebra,normaliz);
        
        System.out.print(prenda1.imagenes());
       
       Assert.assertTrue(prenda1.imagenes().contains("Imagenes/Imagenes-test.jpg"));
      

    }
}
