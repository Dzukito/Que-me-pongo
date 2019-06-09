package ar.utn.dds.modelo;

import java.util.List;

public class Pronostico {
	private float temperatura;
	private List<TipoClima> clima;
	private float humedad;
	private String cod;

	public Pronostico(){};
	public Pronostico(Float temperatura, List<TipoClima> clima, Float humedad){
		this.temperatura = temperatura;
		this.clima = clima;
		this.humedad = humedad;
	}

	public float temperatura() {
		return temperatura;
	}

	public List<TipoClima> clima() {
		return clima;
	}

	public float humedad() {
		return humedad;
	}

	public String cod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

}
