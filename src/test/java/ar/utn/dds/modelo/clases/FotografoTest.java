package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.clases.Fotografo;
import ar.utn.dds.modelo.ropa.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test de la clase Fotografo")
public class FotografoTest {
	  Prenda prenda1;
	  TipoPrenda remera;
	  String standar, zebra;
	  Fotografo normaliz;
    @Before
    public void init(){
         remera = new TipoPrenda(Categoria.TORSO, "Remera", new HashSet<Material>(Arrays.asList(Material.LINO)),null,"Imagenes/defaultTorso.jpg");
         prenda1 = new Prenda(remera, "RemeraZebra", Color.Blanco, Color.Negro, Material.LINO);
         standar= "Imagenes/defaultTorso.jpg";
         zebra= "Imagenes/test.jpg";
         normaliz= new Fotografo();
    }
    @Test
    @DisplayName("Verifica que se muestre la imagen de por defecto si una prenda no tiene ninguna fotografia")
    public void sinImagen(){
    	this.init();
    	Assert.assertEquals(0,prenda1.getFotografo().imagenes().size());
    }
    @Test
    @DisplayName("Verifica que se agregue una foto a la prenda")
    public void agregarImagenPrenda(){ 
    	this.init();
    	prenda1.getFotografo().cargarImagen(zebra,normaliz);
        System.out.print(prenda1.getFotografo().imagenes());
       Assert.assertTrue(prenda1.getFotografo().imagenes().contains("Imagenes/Imagenes-test.jpg"));
    }
}
