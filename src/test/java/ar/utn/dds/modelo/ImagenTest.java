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
	  File standar; 
	   
	  
	
	
    @Before
    public void init(){
    	 materialRemera = new HashSet<Material>();
         materialRemera.add(Material.LINO);
         
         remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
               
         blancoYNegro = new ArrayList<Color>();
         blancoYNegro.add(Color.Blanco);
         blancoYNegro.add(Color.Negro);
        
         prenda1 = new Prenda(remera, "RemeraZebra", blancoYNegro, Material.LINO, Estilo.NORMAL);
         
         standar= new File("2019-ma-ma-group-07\\Imagenes\\default.jpg"); //imagen que el constructor pone por defecto en prenda1
    }


    @Test
    public void imagenStandar(){
    	this.init();
    	Assert.assertEquals(standar,prenda1.imagen());
    }

    @Test
    public void cambiarImagenPrenda(){ 
    	this.init();
    	File zebra= new File("2019-ma-ma-group-07\\Imagenes\\test.jpg");
        prenda1.cargarImagen(zebra);
        
        Assert.assertNotEquals(standar,prenda1.imagen());
        Assert.assertEquals(zebra,prenda1.imagen());

    }
}
