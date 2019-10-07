package ar.utn.dds.modelo;

import javax.persistence.*;


@Entity
@Table(name="calificacionAtuendo")
public class CalificacionAtuendo {
	
	@Id
	@GeneratedValue
	private long id_calificacionAtuendo;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_pronostico")
    private Pronostico pronostico;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_atuendo")
    private Atuendo atuendo;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_evento")
    private Evento evento;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @Column(name = "calificacion")
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
