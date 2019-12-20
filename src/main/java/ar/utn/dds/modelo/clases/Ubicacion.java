package ar.utn.dds.modelo.clases;
import javax.persistence.*;


@Entity
@Table(name="ubicacion")
public class Ubicacion {
	
	@Id
	@GeneratedValue
	private long id_ubicacion;
	@Column(name = "id")
	private String id;
	@Column(name = "ciudad")
	private String ciudad;
	@Column(name = "estado")
	private String estado;
	@Column(name = "pais")
	private String pais;

	public long getId_ubicacion() {
		return id_ubicacion;
	}
	
	public void setId_ubicacion(long id_ubicacion) {
		this.id_ubicacion = id_ubicacion;
	}
	
	public String ciudad(){
		return this.ciudad;
	}
	
	public String estado(){
		return this.estado;
	}
	public String id(){
		return this.id;
	}
	public String pais(){
		return this.pais;
	}

	public Ubicacion(String id, String ciudad, String estado, String pais){
		this.id = id;
		this.ciudad = ciudad;
		this.estado = estado;
		this.pais = pais;
	}
	public Ubicacion() {
		
	}
}
