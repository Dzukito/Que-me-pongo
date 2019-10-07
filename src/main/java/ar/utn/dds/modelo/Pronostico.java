package ar.utn.dds.modelo;

import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.*;

@Entity
@Table(name="pronostico")
public class Pronostico extends EntidadPersistente {
	
	@Id
	@GeneratedValue
	private long id_pronostico;
	
	@Column(name = "temperatura")
	private double temperatura;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="id_clima",referencedColumnName= "id")
	private List<TipoClima> clima;
	
	@Column(name = "humedad")
	private float humedad;
	
	@Column(name = "fecha")
	protected Calendar fecha;
	
	@Column(name = "nubosidad")
	protected float nubosidad;
	
	@Column(name = "viento")
	protected double viento;
	
	@Column(name = "precipitacion")
	protected float precipitacion;

/*
	muchoFrio = templado +2
	frio = templado +1
	templado = la mitad del nivel de calorMaximo
	caluroso = templado -1
	muchoCalor = templado -2
*/



	public ArrayList<NivelDeCalor> nivelesDeCalorRequeridos(){
		ArrayList<NivelDeCalor> nivelesDeCalor;
		if (this.muchoFrio()){
			nivelesDeCalor = new ArrayList<NivelDeCalor>(Arrays.asList(new NivelDeCalor(Categoria.TORSO,4),new NivelDeCalor(Categoria.PARTEINFERIOR,2),new NivelDeCalor(Categoria.CALZADO,2),new NivelDeCalor(Categoria.ACCESORIOS,5),new NivelDeCalor(Categoria.CUELLO,1),new NivelDeCalor(Categoria.MANOS,1),new NivelDeCalor(Categoria.CABEZA,1)));
			return nivelesDeCalor;
		}
		if (this.frio()){
			nivelesDeCalor = new ArrayList<NivelDeCalor>(Arrays.asList(new NivelDeCalor(Categoria.TORSO,3),new NivelDeCalor(Categoria.PARTEINFERIOR,1),new NivelDeCalor(Categoria.CALZADO,2),new NivelDeCalor(Categoria.ACCESORIOS,5),new NivelDeCalor(Categoria.CUELLO,1),new NivelDeCalor(Categoria.MANOS,1),new NivelDeCalor(Categoria.CABEZA,1)));
			return nivelesDeCalor;
		}
		if (this.templado()){
			nivelesDeCalor = new ArrayList<NivelDeCalor>(Arrays.asList(new NivelDeCalor(Categoria.TORSO,2),new NivelDeCalor(Categoria.PARTEINFERIOR,1),new NivelDeCalor(Categoria.CALZADO,2),new NivelDeCalor(Categoria.ACCESORIOS,5),new NivelDeCalor(Categoria.CUELLO,0),new NivelDeCalor(Categoria.MANOS,0),new NivelDeCalor(Categoria.CABEZA,0)));
			return nivelesDeCalor;
		}
		if (this.muyCaluroso()){
			nivelesDeCalor = new ArrayList<NivelDeCalor>(Arrays.asList(new NivelDeCalor(Categoria.TORSO,1),new NivelDeCalor(Categoria.PARTEINFERIOR,1),new NivelDeCalor(Categoria.CALZADO,1),new NivelDeCalor(Categoria.ACCESORIOS,5),new NivelDeCalor(Categoria.CUELLO,0),new NivelDeCalor(Categoria.MANOS,0),new NivelDeCalor(Categoria.CABEZA,0)));
			return nivelesDeCalor;
		}
		if (this.caluroso()){
			nivelesDeCalor = new ArrayList<NivelDeCalor>(Arrays.asList(new NivelDeCalor(Categoria.TORSO,1),new NivelDeCalor(Categoria.PARTEINFERIOR,1),new NivelDeCalor(Categoria.CALZADO,1),new NivelDeCalor(Categoria.ACCESORIOS,5),new NivelDeCalor(Categoria.CUELLO,0),new NivelDeCalor(Categoria.MANOS,0),new NivelDeCalor(Categoria.CABEZA,0)));
			return nivelesDeCalor;
		}
		return null;
	}


	public boolean muchoFrio(){
		return this.temperatura <= 5;
	}
	public boolean frio(){
		return this.temperatura <= 15;
	}
	public boolean templado(){
		return this.temperatura <= 25 && this.temperatura > 15;
	}
	public boolean caluroso(){
		return this.temperatura <= 32;
	}
	public boolean muyCaluroso(){
		return this.temperatura > 32;
	}

	@Override
	public int hashCode() { return Objects.hash(clima); }
	public boolean somosSimilares(Pronostico pronostico){
		return pronostico.hashCode() == this.hashCode();
	}
	private boolean esUnaPrendaNegadaDeMisClimas(String prenda){
		return this.clima.stream().anyMatch(clima -> clima.esUnaPrendaNegada(prenda));
	}
	ArrayList<String> prendasNegadas(){
		ArrayList<String> prendasNegadas = new ArrayList<String>();
		this.clima.forEach(tipoClima -> tipoClima.prendasNegadas().forEach(prenda -> prendasNegadas.add(prenda)));
		return prendasNegadas;
	}
	public ArrayList<String> prendasSatisfacen() {
		ArrayList<String> prendasSatisfacen = new ArrayList<String>();
		ArrayList<String> prendasSatisfacen2;
		this.clima.forEach(tipoClima -> tipoClima.prendasSatisfacen().forEach(prenda -> prendasSatisfacen.add(prenda)));
		prendasSatisfacen2 = (ArrayList<String>) prendasSatisfacen.stream().filter(prenda-> !this.esUnaPrendaNegadaDeMisClimas(prenda)).distinct().collect(Collectors.toList());
		return prendasSatisfacen2;
	}

	//Getters-y-Setters-------------------------------------------
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
	public double getTemperatura() {
		return temperatura;
	}
	public List<TipoClima> clima() {
		return clima;
	}
	public float humedad() {
		return humedad;
	}
	public void setHumerdad(float humidity) {
		this.humedad = humidity;
	}
	//Constructores------------------------------------------------
	public Pronostico(){};
	public Pronostico(Float temperatura, List<TipoClima> clima, Float humedad){
		this.temperatura = temperatura;
		this.clima = clima;
		this.humedad = humedad;
	}
}
