package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.HorarioYaOcupado;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Entity
@Table(name = "Usuario")
public class Usuario{
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private List<Guardaropa> roperos;
    @Column(name = "nombreUsuario")
    private String userName;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_membrecia")
    private Membrecia membrecia;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_centroClimatologico")
    private ArrayList<Evento> eventos;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_sexo")
    private String sexo;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_sensibilidad")
    private Sensibilidad sensibilidad;


    public void aceptarAtuendo(ArrayList<AceptarSuegerenciaObservador> observadores,Atuendo atuendo, CalificacionAtuendo calificacion){
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
        atuendo.setterCalificacion(calif);
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

    //Constructores------------------------------------------------
    Usuario(String userName, ArrayList<Guardaropa> roperos){
        this.userName = userName;
        this.roperos = roperos;
        this.membrecia = new Gratuito();
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
    
    public List<Guardaropa> misRoperos(){ return this.roperos;}
    public Membrecia miMembrecia(){ return this.membrecia;}
    public Sensibilidad getSensibilidad() {
        return this.sensibilidad;
    }
    public void setSensibilidad(Sensibilidad sens) {
        this.sensibilidad = sens;
    }
    public ArrayList<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}
}
