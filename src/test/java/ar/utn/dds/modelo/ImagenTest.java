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
	   
	  
	
	
    @Before
    public void init(){
    	
    	standar="Imagenes/default.jpg"; 
    	zebra= "Imagenes/test.jpg";
    	
    	 materialRemera = new HashSet<Material>();
         materialRemera.add(Material.LINO);
         
         remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera,null,standar);
               
         blancoYNegro = new ArrayList<Color>();
         blancoYNegro.add(Color.Blanco);
         blancoYNegro.add(Color.Negro);
        
         prenda1 = new Prenda(remera, "RemeraZebra", blancoYNegro, Material.LINO, Estilo.NORMAL);
         
         
    }


    @Test
    public void imagenStandar(){
    	
    	this.init();
    	Assert.assertEquals(standar,remera.imagen());
    }
    
    @Test
    public void cambiarImagenPrenda(){ 
    	this.init();
    	remera.cargarImagen(zebra);
        
        System.out.print(remera.imagen());
        
       Assert.assertNotEquals(standar,remera.imagen());
       //Assert.assertEquals(zebra,remera.imagen());

    }
}
