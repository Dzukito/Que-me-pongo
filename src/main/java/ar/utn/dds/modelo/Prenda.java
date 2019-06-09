package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.AlMenosUnColor;
import ar.utn.dds.excepciones.SoloTieneUnColor;
import java.util.ArrayList;
import java.util.Objects;

//Para las imagenes:

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//

public class Prenda{
    private ArrayList<Color> colores;
    private TipoPrenda tipoPrenda;
    private String nombrePrenda;
    private Material material;
    private Estilo estilo;
    private Boolean disponibilidad;
    private String imagen;
   


    public Estilo getEstilo(){
        return estilo;
    }
    public boolean disponible(){
       return this.disponibilidad;
    }
    public void bloquearse(){
        this.disponibilidad = false;
    }
    public void desbloquearse(){
        this.disponibilidad = true;
    }
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> colores, Material material){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.disponibilidad = true;
        this.estilo = Estilo.NORMAL;
        this.tipoPrenda.perteneceMaterial(material);
        this.imagen=this.tipoPrenda.imagen();
        if(colores.isEmpty()){
            throw new AlMenosUnColor();
        }
    }
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> colores, Material material, Estilo estilo,String path){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.disponibilidad = true;
        this.estilo = estilo;
        this.imagen=path;
        this.tipoPrenda.perteneceMaterial(material);
            if(colores.isEmpty()){
                throw new AlMenosUnColor();
            }
    }
   
    public String tipo(){
        return this.tipoPrenda.tipo();
    }
    public String getNombrePrenda(){
        return nombrePrenda;
    }
    public Material getMaterial(){
        return material;
    }
    public Color colorPrimario(){
        return this.colores.get(0);
    }
    public  Color colorSecundario(){
        if (this.colores.size() == 1) {
            throw new SoloTieneUnColor();
        }
        return this.colores.get(1);
    }    
    @Override
    public int hashCode(){
        return Objects.hash(colores, tipoPrenda, nombrePrenda, material);
    }
    public String categoria() {
        return this.tipoPrenda.categoria();
    }

    public boolean esSuperponible(Prenda prenda){
       return this.tipoPrenda.esSuperponible(prenda.tipoDePrenda());
    }

    public TipoPrenda tipoDePrenda() {
        return this.tipoPrenda;
    }
    
    
    
   //-------------------------
    
    public String imagen(){
        return this.imagen;
    }
    
    public void cargarImagen(String path) {
    	String destino= "Imagenes/"+path.replace('/', '-').replaceAll(".jpg","")+".jpg";//nombre para la imagen en la carpeta 
    	normalizarImagen(path,destino, 600,600,"jpg");
        this.imagen= destino;
    }
    
    public  void normalizarImagen(String path,String destino, int width, int hight, String format) {
    	
    	File pathOrigen= new File(path);
    	File pathDestino= new File(destino); 
    	
    	
    	try {
    		BufferedImage original= ImageIO.read(pathOrigen);
    		BufferedImage normalizada = new BufferedImage(width, hight, original.getType()); 
    		
    		Graphics2D g2= normalizada.createGraphics(); 
    		g2.drawImage(original, 0, 0, width, hight, null);  
    		g2.dispose();
    		
    		ImageIO.write(normalizada, format, pathDestino);  
    	}
    	catch(IOException ex) {
    		ex.printStackTrace();}
    	}
	
   
    
    
}
