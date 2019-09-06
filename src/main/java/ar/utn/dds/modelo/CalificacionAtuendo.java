package ar.utn.dds.modelo;

public class CalificacionAtuendo {
    private Pronostico pronostico;
    private Evento evento;
    private Usuario usuario;
    private int calificacionSuperior;
    private int calificacionManos;
    private int calificacionInferior;
    
    CalificacionAtuendo(){}
    CalificacionAtuendo(Pronostico pronostico, Evento evento, Usuario usuario){
        this.pronostico = pronostico;
        this.evento = evento;
        this.calificacionSuperior = 0;
        this.calificacionInferior = 0;
        this.calificacionManos = 0;
        this.usuario = usuario;
    }
    
    public void ajustarPreferencia(Usuario usuario) {
    	
    	if(calificacionSuperior<0) 
    		usuario.getSensibilidad().setFriolentoSuperior(); 
    	else if(calificacionSuperior>0)
    		usuario.getSensibilidad().setCalurosoSuperior();
    	
    	//--------------
    	
    	if(calificacionInferior<0) 
    		usuario.getSensibilidad().setFriolentoInferior(); 
    	else if(calificacionInferior>0)
    		usuario.getSensibilidad().setCalurosoInferior(); 
    	
    	//------------
    	if(calificacionManos<0) 
    		usuario.getSensibilidad().setFriolentoManos();
    	else if(calificacionManos>0)
    		usuario.getSensibilidad().setCalurosoManos(); 
    	
    	
    }
}
