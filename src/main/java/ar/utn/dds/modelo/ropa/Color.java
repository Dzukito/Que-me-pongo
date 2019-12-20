package ar.utn.dds.modelo.ropa;
import javax.persistence.*;


public enum Color {
    Rojo("Rojo"),
    Celeste("Celeste"),
    Azul("Azul"),
    Amarillo("Amarillo"),
    Blanco("Blanco"),
    Negro("Negro"),
    Violeta("Violeta"),
    Verde("Verde"),
    Gris("Gris"),
    Bordo("Bordo"),
    Naranja("Naranja");
	
	
	private long id_color;
	
	
    private final String color;

    Color(String color){
        this.color = color;
    }
    
}
