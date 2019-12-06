package ar.utn.dds.modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.persistence.*;

@Entity
@Table(name="fotografo")
public class Fotografo {
	
	@Id
	@GeneratedValue
	private long id_fotografo;
	
	//Si la imagen no se encarga de normalizar, que haya una entidad que lo haga. 
	//Crear esta entidad podria hacer más cosas con las imagenes si el sistema lo necesita (de ahi que no se llame solo 'normalizador')...
	@ElementCollection
	@CollectionTable(name="foto", joinColumns=@JoinColumn(name="id_fotografo"))
	@Column(name="fotoURL")
	private List<String> imagenes;

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
	
	public void cargarUnicaImagen(String path) {
		String destino= path.replace('/', '-').replaceAll(".jpg","");//nombre para la imagen en la carpeta donde se almacena
		destino= path.replace('/', '-').replaceAll(".png","");
		destino=destino+".png";
//		this.normalizarUnaImagen("/home/dds/"+path, "/img/"+destino, 600, 600, ".png"); //el adminImg normaliza la imagen
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

	public List<String> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}
	public long getId_fotografo() {
		return id_fotografo;
	}
	public void setId_fotografo(long id_fotografo) {
		this.id_fotografo = id_fotografo;
	}
	
	
	Fotografo(){
		this.imagenes = new ArrayList<String>();
	}
	Fotografo(List<String> imagenes){
		this.imagenes = imagenes;
	}
}
