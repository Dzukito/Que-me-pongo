package ar.utn.dds.modelo.clases;

import ar.utn.dds.Constantes;
import ar.utn.dds.modelo.interfaces.AceptarSuegerenciaObservador;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.interfaces.RechazarSugerenciaObservador;

import javax.persistence.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Entity
@Table(name="evento")
public class Evento implements AceptarSuegerenciaObservador, RechazarSugerenciaObservador {
	
	@Id
	@GeneratedValue
	private long id_evento;
	
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "lugar")
    private String lugar;
	
	@Column(name = "horaComienzo")
    private Calendar horaComienzo;
	
	@Column(name = "horaFin")
    private Calendar horaTermino;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;

    @Column(name="estilo")
    @Enumerated(EnumType.STRING)
    private Estilo estilo;
    
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_atuendo")
    private Atuendo atuendo;
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name="atuendoSugerido", joinColumns={@JoinColumn(name="id_evento")}, inverseJoinColumns={@JoinColumn(name="id_atuendo")})
    private List<Atuendo> atuendosSugeridos;
    
    @Column(name = "tiempoAviso")
    private int tiempoAviso;



    /*
    public void alertaMeteorologica(Usuario usuario, Guardaropa guardaropa, Pronostico pronostico){
        usuario.getEnviadores().forEach(enviador -> enviador.enviarAlertaMeteorologica(
                enviador.getDireccion(),
                "Alerta meteorologica",
                "Desea cambiar su atuendo a uno mas adecuado a las nuevas condiciones meteorologicas, le sugerimos:",
                guardaropa.sugerirAtuendo(pronostico,this,usuario).getImagenes()));
    }
    */
    public void agregarSugerencias(List<Atuendo> atuendosSugeridos){
        atuendosSugeridos.stream()
                .filter(atuendo1 -> !this.atuendosSugeridos.stream()
                        .anyMatch(atuendo2 -> atuendo1.somosIguales(atuendo2)))
                .forEach(atuendo1 -> this.agregarSugerencia(atuendo1));
    }
    public void agregarSugerencia(Atuendo atuendo){
        this.atuendosSugeridos.add(atuendo);
    }
    public boolean tengoSugerencias(){
        return this.sugerencias().size() != 0;
    }
    public long duracion(){ return Duration.between(horaComienzo.toInstant(),horaTermino.toInstant()).toHours(); }
    public boolean estoyEnEseHorario(Evento evento) { return (this.horaComienzo.before(evento.getHoraTermino()) && this.horaTermino.after(evento.getHoraComienzo())); }
    public long diasRestantesParaElEvento(){ return Duration.between(Calendar.getInstance().toInstant(),horaComienzo.toInstant()).toDays(); }
    public boolean estoyEnEvento(Calendar fecha) { return (this.horaComienzo.before(fecha) && this.horaTermino.after(fecha)); }
    public boolean eventoSimilar(Evento evento) {
        return this.estilo == evento.getEstilo();
    }





    //Getters-y-Setters-----------------------------------------------
    public Calendar getHoraTermino(){
        return this.horaTermino;
    }
    public Calendar getHoraComienzo(){
        return this.horaComienzo;
    }
    public Ubicacion getUbicacion(){ return this.ubicacion; }
    public Estilo getEstilo(){
        return this.estilo;
    }
    public Atuendo getAtuendo(){ return this.atuendo; }
    public void setEstilo(Estilo estilo) { this.estilo = estilo; }
    public void setHoraComienzo(Calendar horaComienzo) { this.horaComienzo = horaComienzo; }
    public void setHoraTermino(Calendar horaTermino) { this.horaTermino = horaTermino; }
    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }
    public void setAtuendo(Atuendo atuendo) { this.atuendo = atuendo; }
    public void setTiempoAviso(int tiempoAviso) { this.tiempoAviso = tiempoAviso; }
    public long getId_evento() {
		return id_evento;
	}
	public void setId_evento(long id_evento) {
		this.id_evento = id_evento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Atuendo> getAtuendosSugeridos() {
		return atuendosSugeridos;
	}
	public void setAtuendosSugeridos(List<Atuendo> atuendosSugeridos) {
		this.atuendosSugeridos = atuendosSugeridos;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	//Constructores---------------------------------------------------
    public Evento() {
    	this.atuendosSugeridos = new ArrayList<Atuendo>();
    }
    public Evento(Calendar horaComienzo, Calendar horaTermino, Ubicacion ubicacion, Estilo estilo){
        this.horaComienzo= horaComienzo;
        this.horaTermino = horaTermino;
        this.ubicacion = ubicacion;
        this.estilo = estilo;
        this.tiempoAviso = Constantes.proximidadEstandarEventoEnDiaz;
        this.atuendosSugeridos = new ArrayList<Atuendo>();
    }
    Evento(Calendar horaComienzo, Calendar horaTermino, Ubicacion ubicacion, Estilo estilo, int tiempoAviso){
        this.horaComienzo= horaComienzo;
        this.horaTermino = horaTermino;
        this.ubicacion = ubicacion;
        this.estilo = estilo;
        this.tiempoAviso = tiempoAviso;
        this.atuendosSugeridos = new ArrayList<Atuendo>();
    }
    Evento(Calendar horaComienzo, int duracion, Ubicacion ubicacion, Estilo estilo, int tiempoAviso){
        Calendar horaTermino2 = Calendar.getInstance();
        horaTermino2.add(Calendar.HOUR, duracion);
        this.horaComienzo= horaComienzo;
        this.horaTermino = horaTermino2;
        this.ubicacion = ubicacion;
        this.estilo = estilo;
        this.tiempoAviso = tiempoAviso;
        this.atuendosSugeridos = new ArrayList<Atuendo>();
    }

    //Methods-of-patters----------------------------------------------
    @Override
    public void updateAceptarSugerencia(Atuendo atuendo) { this.atuendo = atuendo; }
    @Override
    public void downdateAceptarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) { }
    public List<Atuendo> sugerencias() { return this.atuendosSugeridos; }
    @Override
    public void updateRechazarSugerencia(Atuendo atuendo) { }
    @Override
    public void downdateRechazarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) { }

}
