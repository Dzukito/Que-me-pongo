package ar.utn.dds.modelo;

import javax.persistence.*;


@Entity
@Table(name="calificacionAtuendo")
public class CalificacionAtuendo {
	
	@Id
	private long id_calificacionAtuendo;
	
    private Pronostico pronostico;
    private Atuendo atuendo;
    private Evento evento;
    private Usuario usuario;
    private int calificacion;


    //Metodos-que-no-se-usan---------------------------------
/*
    public void ajustarPreferencia(Usuario usuario) {
        usuario.getSensibilidad().ajustarPreferencias(this.atuendo.);
    }
*/
    //Metodos-privados---------------------------------------
    //Metodos-publicos---------------------------------------
    public boolean mismasCondiciones(Usuario usuario, Evento evento, Pronostico pronostico){
        return (this.usuario.mismoUsuario(usuario) && this.evento.eventoSimilar(evento) && this.pronostico.somosSimilares(pronostico));
    }
    //Getters-y-Setters--------------------------------------
    public int getCalificacion(){ return this.calificacion; }
    public Usuario getUsuario(){ return this.usuario; }
    public Evento getEvento(){ return this.evento; }
    public Pronostico getPronostico(){ return this.pronostico; }
    //Constructores------------------------------------------
    CalificacionAtuendo(){}
    CalificacionAtuendo(Pronostico pronostico, Evento evento, Usuario usuario, Atuendo atuendo){
        this.pronostico = pronostico;
        this.atuendo = atuendo;
        this.evento = evento;
        this.usuario = usuario;
        this.calificacion =0;
    }
}
