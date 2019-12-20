package ar.utn.dds.spark.utils;

import java.util.Calendar;
import java.util.Date;

import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.ropa.Estilo;

public class EventoVista {
	private long id_evento;
	private long usuarioid;
	private String nombre;
	private String lugar;
	private int anioInicio;
	private int mesInicio;
	private int diaInicio;
	private int anioFin;
	private int mesFin;
	private int diaFin;
	private Estilo estilo;
	
	public EventoVista(Evento evento, Usuario usuario) {
		this.id_evento = evento.getId_evento();
		this.usuarioid = usuario.getId_usuario();
		this.nombre=evento.getNombre();
		this.lugar=evento.getLugar();
		this.estilo=evento.getEstilo();
		this.anioInicio=evento.getHoraComienzo().get(Calendar.YEAR);
		this.mesInicio=evento.getHoraComienzo().get(Calendar.MONTH);
		this.diaInicio=evento.getHoraComienzo().get(Calendar.DATE);
		this.anioFin=evento.getHoraTermino().get(Calendar.YEAR);
		this.mesFin=evento.getHoraTermino().get(Calendar.MONTH);
		this.diaFin=evento.getHoraTermino().get(Calendar.DATE);
		
	}

	public long getId_evento() {
		return id_evento;
	}

	public void setId_evento(long id_evento) {
		this.id_evento = id_evento;
	}

	public long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public int getAnioInicio() {
		return anioInicio;
	}

	public void setAnioInicio(int anioInicio) {
		this.anioInicio = anioInicio;
	}

	public int getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}

	public int getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(int diaInicio) {
		this.diaInicio = diaInicio;
	}

	public int getAnioFin() {
		return anioFin;
	}

	public void setAnioFin(int anioFin) {
		this.anioFin = anioFin;
	}

	public int getMesFin() {
		return mesFin;
	}

	public void setMesFin(int mesFin) {
		this.mesFin = mesFin;
	}

	public int getDiaFin() {
		return diaFin;
	}

	public void setDiaFin(int diaFin) {
		this.diaFin = diaFin;
	}
	
	

}
