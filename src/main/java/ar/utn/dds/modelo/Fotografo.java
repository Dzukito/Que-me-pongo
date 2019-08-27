package ar.utn.dds.modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fotografo {
	
	//Si la imagen no se encarga de normalizar, que haya una entidad que lo haga. 
	//Crear esta entidad podria hacer más cosas con las imagenes si el sistema lo necesita (de ahi que no se llame solo 'normalizador')...
	
	public  void normalizarUnaImagen(String path,String destino, int width, int hight, String format) {
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
