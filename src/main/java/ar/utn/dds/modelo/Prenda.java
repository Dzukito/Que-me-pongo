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
    private File imagenPrenda;


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
        if(colores.isEmpty()){
            throw new AlMenosUnColor();
        }
    }
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> colores, Material material, Estilo estilo){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.disponibilidad = true;
        this.estilo = estilo;
        this.tipoPrenda.perteneceMaterial(material);
            if(colores.isEmpty()){
                throw new AlMenosUnColor();
            }
        this.imagenPrenda= new File("2019-ma-ma-group-07\\Imagenes\\default.jpg"); 
        //Prenda es generico, no puede tener una img default para las distintas prendas. Default.jpg sirve para darle un file con que empezar a todas 

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
	
    public File imagen(){
        return this.imagenPrenda;
    }
    
    
    
    public void cargarImagen(File nuevaImagen) {
    	normalizarImagen(nuevaImagen,600,600,"jpg");
        this.imagenPrenda= nuevaImagen;
    }
    
    public  void normalizarImagen(File imagenOriginal, int width, int hight, String format) {
    	try {
    		BufferedImage original= ImageIO.read(imagenOriginal); //Lee la imagen que ya tengo, por parametro
    		BufferedImage normalizada = new BufferedImage(width, hight, original.getType()); //Prepara la normalizada
    		Graphics2D g2= normalizada.createGraphics(); 
    		g2.drawImage(original, 0, 0, width, hight, null);  //Modifica la original
    		g2.dispose();
    		ImageIO.write(normalizada, format, imagenOriginal); //Reescribe la original por la normalizada 
    	}
    	catch(IOException ex) {
    		ex.printStackTrace();}
    	}
    
    
    
}
