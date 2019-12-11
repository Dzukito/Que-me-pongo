package ar.utn.dds.modelo.clases;

import ar.utn.dds.excepciones.HorarioYaOcupado;
import ar.utn.dds.modelo.sugerencia.AceptarSugerenciaObserver;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.interfaces.Sensibilidad;
import ar.utn.dds.modelo.clima.CentroClimatologico;
import ar.utn.dds.modelo.sensibilidades.Ermitanio;
import ar.utn.dds.modelo.interfaces.AceptarSuegerenciaObservador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Entity
@Table(name = "usuario")
public class Usuario{
	
	@Id
	@GeneratedValue
	private long id_usuario;

	@ManyToMany
    @JoinTable(name="map_usuario_guardaropa", joinColumns={@JoinColumn(name="id_usuario")}, inverseJoinColumns={@JoinColumn(name="id_guardaropa")})
    private List<Guardaropa> roperos;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombreUsuario")
    private String userName;
    
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_membrecia")
    private Membrecia membrecia;
	
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
    private List<Evento> eventos;
    
    @Column(name = "sexo")
    private String sexo;
    
//    @OneToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "id_sensibilidad")
    @Transient
    private Sensibilidad sensibilidad;

    @Column(name = "password")
    private String password;
    
    @Column(name = "mail")
    private String mail;
    
	@Column(name = "telefono")
    private int telefono;
	
    public void aceptarAtuendo(ArrayList<AceptarSuegerenciaObservador> observadores, Atuendo atuendo, CalificacionAtuendo calificacion){
        AceptarSugerenciaObserver observer = new AceptarSugerenciaObserver();
        observadores.forEach(observador -> observer.attach(observador));
        observer.ejecutar(atuendo);
        this.calificar(atuendo, calificacion); //Cuando acepto la sugerencia la califico

    }
    public void rechazarAtuendo(ArrayList<AceptarSuegerenciaObservador> observadores,Atuendo atuendo, Evento evento){
        AceptarSugerenciaObserver observer = new AceptarSugerenciaObserver();
        observadores.forEach(observador -> observer.attach(observador));
        observer.notifyAceptarSugerencia(atuendo);
    }
    public int cantidadAtuendos(int i){ return roperos.get(i).cantidadAtuendosGenerados(); }
    public Membrecia getMembrecia(){ return this.membrecia; }
    public ArrayList<Atuendo>  atuendosGuardaropa(Guardaropa ropero){ return ropero.atuendosGenerados(); }
    //Metodos-privados--------------------------------------
    //Metedos-publicos--------------------------------------
    @Override
    public int hashCode(){ return Objects.hash(this.roperos, this.userName, this.membrecia, this.eventos, this.sexo, this.sensibilidad); }
    public boolean mismoUsuario(Usuario usuario) { return this.hashCode() == usuario.hashCode(); }
    public void calificar(Atuendo atuendo, CalificacionAtuendo calif) {
        atuendo.addCalificacion(calif);
//        calif.ajustarPreferencia(this);
    }
    public void compartirGuardaropas(int i, Usuario usuario){ usuario.agregarRopero(this.getGuardaropa(i)); }
    public boolean puedoAsistir(Evento evento){ return !eventos.stream().anyMatch(evento1 -> evento1.estoyEnEseHorario(evento)); }
    public int cantidadPrendas(int guardaropa){ return this.roperos.get(guardaropa).cantidadDePrendas(); }
    public int cantidadDePrendasPorCategoria(String categoria, int i){ return this.getGuardaropa(i).cantidadDePrendasEnCategoria(categoria); }
    public int cantidadDeRoperos() { return this.roperos.size(); }
    public boolean tengoGuardaropas(Guardaropa ropero){ return this.roperos.contains(ropero); }


    //Getters-y-Setters--------------------------------------------
    public Guardaropa getGuardaropa(int i){ return this.roperos.get(i); }
    public void setMembrecia(Membrecia membrecia) { this.membrecia = membrecia; }
    public List<Guardaropa> getRoperos(){ return this.roperos;}
    public void agregarEvento(Evento evento){
        if (this.puedoAsistir(evento)){
            this.eventos.add(evento);
        }else{
            throw new HorarioYaOcupado();
        }
    }
    public void agregarPreda(Prenda prenda, Guardaropa ropero){ ropero.agregarPrenda(prenda); }
    public void agregarRopero(Guardaropa ropero){ this.roperos.add(ropero); }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public List<Guardaropa> misRoperos(){ return this.roperos;}
    public Membrecia miMembrecia(){ return this.membrecia;}
    public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
    public Sensibilidad getSensibilidad() {
        return this.sensibilidad;
    }
    public void setSensibilidad(Sensibilidad sens) {
        this.sensibilidad = sens;
    }
    public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public long getId_usuario() {
		return id_usuario;
	}
	
	public void quitarGuardaropa(Guardaropa guardaropa1) { this.roperos = this.roperos.stream().filter(guardaropa -> guardaropa != guardaropa1).collect(Collectors.toList()); }

    //Constructores------------------------------------------------
    public Usuario(String userName, ArrayList<Guardaropa> roperos){
        this.userName = userName;
        this.roperos = roperos;
        this.membrecia = new Gratuito();
        this.eventos = new ArrayList<Evento>();
        this.sensibilidad = new Ermitanio();
    }
    
    
    public Usuario(){
        this.roperos = new ArrayList<Guardaropa>();
//        this.membrecia = new Gratuito();
        this.eventos = new ArrayList<Evento>();
        this.sensibilidad = new Ermitanio();
    }
    //Metodos-por-patrones------------------------------------------
    public void updateEventoProximo(Evento evento) {
        CentroClimatologico centroClimatologico = new CentroClimatologico();
        List<Atuendo> atuendos = this.roperos.stream()
                .map(ropero -> ropero.sugerirAtuendo(centroClimatologico.getMeteorologo().getPronosticoTiempoYUbicacion(evento.getHoraComienzo(),evento.getUbicacion()),evento,this))
                .collect(Collectors.toList());
        evento.agregarSugerencias(atuendos);
    }
    
}
