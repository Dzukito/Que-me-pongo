package ar.utn.dds.modelo;

import java.util.Calendar;
import java.util.TimerTask;

public class TareaProgramada extends TimerTask  {
	private Usuario usuario;

	
	public TareaProgramada(Usuario usuario){
		this.usuario= usuario;
		
	}
	@Override
	public void run() {
		this.encontrarSugerenciasParaEventosDelDia();
	}
	
	
	private void encontrarSugerenciasParaEventosDelDia() {
		
		for (int i = 0; i <usuario.getEventos().size(); i++) {
			if(usuario.getEventos().get(i).diasRestantesParaElEvento()==0 || usuario.getEventos().get(i).estoyEnEvento(Calendar.getInstance())){
				usuario.updateEventoProximo(usuario.getEventos().get(i));
	        }
		}
		
	}

}
