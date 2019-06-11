package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Pronostico {
	private float temperatura;
	private List<TipoClima> clima;
	private float humedad;
	protected Date fecha;
	protected float nubosidad;
	protected float viento;
	protected float precipitacion;



	private boolean esUnaPrendaNegadaDeAlgunoDeMisClimas(String prenda){
		return this.clima.stream().anyMatch(clima -> clima.esUnaPrendaNegada(prenda));
	}
	public ArrayList<String> prendasNegadas(){
		ArrayList<String> prendasNegadas = new ArrayList<String>();
		this.clima.forEach(tipoClima -> tipoClima.prendasNegadas().forEach(prenda -> prendasNegadas.add(prenda)));
		return prendasNegadas;
	}
	public ArrayList<String> prendasSatisfacen() {
		ArrayList<String> prendasSatisfacen = new ArrayList<String>();
		ArrayList<String> prendasSatisfacen2;
		this.clima.forEach(tipoClima -> tipoClima.prendasSatisfacen().forEach(prenda -> prendasSatisfacen.add(prenda)));
		prendasSatisfacen2 = (ArrayList<String>) prendasSatisfacen.stream().filter(prenda-> !this.esUnaPrendaNegadaDeAlgunoDeMisClimas(prenda)).distinct().collect(Collectors.toList());
		return prendasSatisfacen2;
	}


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
	public void agregarClimatologia(TipoClima tipoClima) {
		this.clima.add(tipoClima);
	}
	public void setHumerdad(float humidity) {
		this.humedad = humidity;
	}
}
