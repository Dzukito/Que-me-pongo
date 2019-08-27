package ar.utn.dds.modelo;

import java.util.*;
import java.util.stream.Collectors;

public class Pronostico {
	private double temperatura;
	private List<TipoClima> clima;
	private float humedad;
	protected Calendar fecha;
	protected float nubosidad;
	protected double viento;
	protected float precipitacion;

	@Override
	public int hashCode() { return Objects.hash(clima); }

	public boolean somosSimilares(Pronostico pronostico){
		return pronostico.hashCode() == this.hashCode();
	}
	public boolean haceFrio() {	return this.temperatura<16; }
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
	public double getTemperatura() {
		return temperatura;
	}
	public List<TipoClima> clima() {
		return clima;
	}
	public float humedad() {
		return humedad;
	}


	public Calendar getFecha() {
		return this.fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public void setNubosidad(float nubosidad) {
		this.nubosidad = nubosidad;
		
	}
	public float getNubosidad() {
		return this.nubosidad;
	}
	public void setViento(double viento) {
		this.viento = viento;
		
	}
	public double getViento() {
		return viento;
	}
	public float getPrecipitacion() {
		return precipitacion;
	}
	public void setPrecipitacion(float precipitacion) {
		this.precipitacion = precipitacion;
	}
	public void agregarClimatologia(TipoClima tipoClima) {
		if(this.clima==null) {
			this.clima=new ArrayList<TipoClima>();
		}
		this.clima.add(tipoClima);
	}
	public void setHumerdad(float humidity) {
		this.humedad = humidity;
	}
}
