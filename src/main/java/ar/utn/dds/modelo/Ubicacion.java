package ar.utn.dds.modelo;

public class Ubicacion {
	private String id;
	private String ciudad;
	private String estado;
	private String pais;

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

	Ubicacion(String id, String ciudad, String estado, String pais){
		this.id = id;
		this.ciudad = ciudad;
		this.estado = estado;
		this.pais = pais;
	}
}