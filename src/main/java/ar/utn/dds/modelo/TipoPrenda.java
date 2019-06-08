package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class TipoPrenda {
    private String tipo;
    private Categoria categoria;
    private Set<Material> materiales;
    private Set<TipoPrenda> superponibles;
    //private NivelDeCalor nivelDeCalor;
    private String imagen;//path




    @Override
    public int hashCode(){
        return Objects.hash(this.tipo, this.categoria, this.materiales, this.superponibles);
    }
    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = new HashSet<TipoPrenda>();
    }
    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales,Set<TipoPrenda> superponibles,String pathImg){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = superponibles;
        this.imagen=pathImg;
    }
    public boolean esSuperponible(TipoPrenda prenda){
        return superponibles.contains(prenda);
    }
    public String categoria(){
        return this.categoria.getCategoria();
    }
    public String tipo(){
        return this.tipo;
    }
    
    public Set<Material> materiales() {
        return materiales;
    }

    public Boolean perteneceMaterial(Material material) {
        if(!this.materiales().contains(material)) {
            throw new ElMaterialNoPerteneceALaPrenda();
        }
        return true;
    }
    
    
    public String imagen(){
        return this.imagen;
    }
    
    
    
    public void cargarImagen(String path) {
    	//String destino= "Imagenes/"+this.tipo()+".jpg";
    	normalizarImagen(path, 600,600,"jpg");
        this.imagen= path;
    }
    
    public  void normalizarImagen(String path,  int width, int hight, String format) {
    	
    	File pathOrigen= new File(path);
    	
    	try {
    		BufferedImage original= ImageIO.read(pathOrigen);
    		BufferedImage normalizada = new BufferedImage(width, hight, original.getType()); 
    		
    		Graphics2D g2= normalizada.createGraphics(); 
    		g2.drawImage(original, 0, 0, width, hight, null);  
    		g2.dispose();
    		
    		ImageIO.write(normalizada, format, pathOrigen);  
    	}
    	catch(IOException ex) {
    		ex.printStackTrace();}
    	}
    
}
