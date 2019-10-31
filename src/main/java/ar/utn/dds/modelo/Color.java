package ar.utn.dds.modelo;
import javax.persistence.*;

@Entity
@Table(name="color")
public enum Color {
    Rojo("Rojo"),
    Azul("Azul"),
    Amarillo("Amarillo"),
    Blanco("Blanco"),
    Negro("Negro"),
    Violeta("Violeta"),
    Verde("Verde"),
    Naranja("Naranja");
	
	@Id
	@GeneratedValue
	private long id_color;
	
	@Column(name = "color") 
    private final String color;

    Color(String color){
        this.color = color;
    }
    
}
