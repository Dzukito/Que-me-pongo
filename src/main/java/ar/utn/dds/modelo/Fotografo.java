package ar.utn.dds.modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

public class Fotografo {
	//Si la imagen no se encarga de normalizar, que haya una entidad que lo haga. 
	//Crear esta entidad podria hacer más cosas con las imagenes si el sistema lo necesita (de ahi que no se llame solo 'normalizador')...
	private ArrayList<String> imagenes;

	public String unaImagen(int path){ //devuelve una imagen
		return this.imagenes.get(path);
	}
	public List<String> imagenes(){ //devuelve todas las imagenes
		return this.imagenes.stream().collect(Collectors.toList());
	}
	public void cargarImagen(String path, Fotografo normalizador) {
		String destino= "Imagenes/"+path.replace('/', '-').replaceAll(".jpg","")+".jpg";//nombre para la imagen en la carpeta donde se almacena

		normalizador.normalizarUnaImagen(path, destino, 600, 600, ".jpg"); //el adminImg normaliza la imagen
		this.imagenes.add(destino);
	}
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

	Fotografo(){
		this.imagenes = new ArrayList<String>();
	}
	Fotografo(ArrayList<String> imagenes){
		this.imagenes = imagenes;
	}
}
