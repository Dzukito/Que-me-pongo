package ar.utn.dds.modelo.tarea;

import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.clases.Usuario;

import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

public class TareaProgramada extends TimerTask  {
	private List<Usuario> usuarios;

//despues sacar esta lista de usuarios y agregar un metodo de buscar usuarios en la base
	
	@Override
	public void run() {
		this.encontrarSugerenciasParaEventosDelDia();
	}
	private void encontrarSugerenciasParaEventosDelDia() {
		for (Usuario usuario : usuarios) {
			for (Evento evento : usuario.getEventos()) {
				if(evento.diasRestantesParaElEvento()==0 || evento.estoyEnEvento(Calendar.getInstance())){

		        }
			}
		}
		
		
	}
	public TareaProgramada(List<Usuario> usuarios){
		this.usuarios= usuarios;

	}
}
