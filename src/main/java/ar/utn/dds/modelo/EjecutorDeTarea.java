package ar.utn.dds.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class EjecutorDeTarea {
	
	private TimerTask tarea;
	private Date fechaInicial;
	private Long intervalo;
	private Timer timer;
	
	
	public EjecutorDeTarea(TimerTask tarea, Date fechaInicial, Integer  intervalo, TimeUnit unidad) {
		this.tarea = tarea;
		this.fechaInicial = fechaInicial;
		this.intervalo = unidad.toMillis(intervalo);		
		this.timer = new Timer();
	} 
	
	public void stop() {
		this.timer.cancel();
	}
	
	public void start() {
		timer.scheduleAtFixedRate(tarea, fechaInicial, intervalo);
	}

}
