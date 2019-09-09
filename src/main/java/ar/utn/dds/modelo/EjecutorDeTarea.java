package ar.utn.dds.modelo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class EjecutorDeTarea {
	
	private TimerTask tarea;
	private Long delayInicial;
	private Long intervalo;
	private Timer timer;
	
	public EjecutorDeTarea(TimerTask tarea, Integer delayInicial, Integer  intervalo, TimeUnit unidad) {
		this.tarea = tarea;
		this.delayInicial = unidad.toMillis(delayInicial);
		this.intervalo = unidad.toMillis(intervalo);		
		this.timer = new Timer();
	}
	
	public void stop() {
		this.timer.cancel();
	}
	
	public void start() {
		timer.scheduleAtFixedRate(tarea, delayInicial, intervalo);
	}

}
