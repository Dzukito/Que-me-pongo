package ar.utn.dds.modelo;

import java.util.Date;
import java.util.List;

public class Pronostico {
	private float temperatura;
	private List<TipoClima> clima;
	private float humedad;
	protected Date fecha;
	protected float nubosidad;
	protected float viento;
	protected float precipitacion;
	
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

	public Date getFecha() {
		return this.fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
	public void setNubosidad(float nubosidad) {
		this.nubosidad = nubosidad;
		
	}
	public float nubosidad() {
		return this.nubosidad;
	}
	public void setViento(float viento) {
		this.viento = viento;
		
	}
	
	public float getViento() {
		return viento;
	}
	
	public float getPrecipitacion() {
		return precipitacion;
	}
	public void setPrecipitacion(float precipitacion) {
		this.precipitacion = precipitacion;
	}

}
