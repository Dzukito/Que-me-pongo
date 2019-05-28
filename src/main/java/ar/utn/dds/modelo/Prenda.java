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
    //private File imagen;


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
       return this.tipoPrenda.esSuperponible(prenda);
    }

    public TipoPrenda tipoDePrenda() {
        return this.tipoPrenda;
    }
    
    
    
    //Normaliza la imagen que tenga mi prenda
    public static void normalizarMiImagen(File imagenOriginal,File imagenNormalizada, int width, int hight, String format) {
    	try {
    		BufferedImage original= ImageIO.read(imagenOriginal); //Lee la imagen que ya tengo, por parametro
    		BufferedImage normalizada = new BufferedImage(width, hight, original.getType()); //Prepara la normalizada
    		Graphics2D g2= normalizada.createGraphics(); 
    		g2.drawImage(original, 0, 0, width, hight, null);  //Modifica la original
    		g2.dispose();
    		ImageIO.write(normalizada, format, imagenNormalizada); //Reescribe la normalizada por parametro
    	}
    	catch(IOException ex) {
    		ex.printStackTrace(); //revisar
    	}
    	}
    /*Ejemplo de main(como funciona):
    	File pngOriginal= new File("C:\\Users\\Usuario\\Desktop\\miImagen.png");              //la imagen original que tengo
		File pngResize= new File("C:\\Users\\Usuario\\Desktop\\resized.png");                 //la futura imagen normalizada
		normalizarMiImagen(pngOriginal,pngResize,111,111,"png"); //aplico la funcion y se sobreescribe la normalizada como 111x111
	*/
    
    
}
