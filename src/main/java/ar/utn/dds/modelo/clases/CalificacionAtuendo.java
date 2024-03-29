package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.clima.Pronostico;

import javax.persistence.*;


@Entity
@Table(name="calificacionAtuendo")
public class CalificacionAtuendo {
	
	@Id
	@GeneratedValue
	private long id_calificacionAtuendo;
	
//	@Column(name = "id_pronostico")
    @Transient
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
    public void setCalificacion(int calificacion){ this.calificacion=calificacion; }
    public Usuario getUsuario(){ return this.usuario; }
    public void setUsuario(Usuario usuario){ this.usuario=usuario; }
    public Evento getEvento(){ return this.evento; }
    public Pronostico getPronostico(){ return this.pronostico; }
    
    public CalificacionAtuendo(){}
    public Atuendo getAtuendo() {
		return atuendo;
	}
	public void setAtuendo(Atuendo atuendo) {
		this.atuendo = atuendo;
	}
	
	public long getId_calificacionAtuendo() {
		return id_calificacionAtuendo;
	}
	public void setId_calificacionAtuendo(long id_calificacionAtuendo) {
		this.id_calificacionAtuendo = id_calificacionAtuendo;
	}
    //Constructores------------------------------------------
    
	
	public CalificacionAtuendo(Pronostico pronostico, Evento evento, Usuario usuario, Atuendo atuendo){
        this.pronostico = pronostico;
        this.atuendo = atuendo;
        this.evento = evento;
        this.usuario = usuario;
        this.calificacion =0;
    }
    CalificacionAtuendo(int calificacon, Usuario usuario){
        this.usuario = usuario;
        this.calificacion =calificacon;
    }
    CalificacionAtuendo(Pronostico pronostico, Evento evento, Usuario usuario, Atuendo atuendo, int calificacon){
        this.pronostico = pronostico;
        this.atuendo = atuendo;
        this.evento = evento;
        this.usuario = usuario;
        this.calificacion =calificacon;
    }
}
