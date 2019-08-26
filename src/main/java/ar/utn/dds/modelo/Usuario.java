package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.HorarioYaOcupado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario implements EventoProximoObservador{
    private List<Guardaropa> roperos;
    private String userName;
    private Membrecia membrecia;
    private ArrayList<Evento> eventos;
    private Meteorologo meteorologo;
    private List<Atuendo> atuendosUsados;
    private List<Meteorologo> meteorologos;
    private String sexo;
    private ArrayList<Enviador> enviadores;
    private int sensacionTermica; //empieza en 5 por defecto (ni muy frio ni muy caluroso)

    public void agregarMeteorolo(Meteorologo meteorologo){
        if(this.meteorologo == null){
            this.meteorologo = meteorologo;
        }
        this.meteorologos.add(meteorologo);
    }
    
    
   public void calificar(Atuendo atuendo, CalificacionAtuendo calif) {
    	atuendo.agregarCalificacion(calif); 
    	calif.ajustarPreferencia(this);
    }
    
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
    public void compartirGuardaropas(int i, Usuario usuario){
        usuario.agregarRopero(this.guardaropa(i));
    }
    public void cambiarMeteorologo(){
        this.meteorologo = this.meteorologos.stream().filter(meteorologo1 -> meteorologo1 !=this.meteorologo).collect(Collectors.toList()).get((int) (Math.random() * this.meteorologos.size()-1));
    }
    public Pronostico obtenerPronostico(Calendar tiempo, Ubicacion lugar){
        return meteorologo.getPronosticoTiempoYUbicacion(tiempo,lugar);
    }
    public void usarAtuendo(Atuendo atuendo){
        this.atuendosUsados.add(atuendo);
    }
    public void agregarEvento(Evento evento){
        if (this.puedoAsistir(evento)){
            this.eventos.add(evento);
        }else{
            throw new HorarioYaOcupado();
        }
    }
    public boolean puedoAsistir(Evento evento){ return !eventos.stream().anyMatch(evento1 -> evento1.estoyEnEseHorario(evento)); }
   
    Usuario(String userName, ArrayList<Guardaropa> roperos){
        this.userName = userName;
        this.roperos = roperos;
        this.membrecia = new Gratuito();
        this.eventos = new ArrayList<Evento>();
        this.meteorologos = new ArrayList<Meteorologo>();
        this.sensacionTermica=5;
    }
    public int cantidadPrendas(int guardaropa){
        return this.roperos.get(guardaropa).cantidadDePrendas();
    }
    public Guardaropa guardaropa(int i){
        return this.roperos.get(i);
    }
    public ArrayList<Atuendo>  atuendosGuardaropa(Guardaropa ropero){
        return ropero.atuendosGenerados();
    }
    public int cantidadDePrendasPorCategoria(Prenda prenda, int i){ return this.guardaropa(i).cantidadDePrendasEnCategoria(prenda.categoria()); }
    public int cantidadAtuendos(int i){
        return roperos.get(i).cantidadAtuendosGenerados();
    }
    public void agregarPreda(Prenda prenda, Guardaropa ropero){ ropero.agregarPrenda(prenda); }
    public void agregarRopero(Guardaropa ropero){
        this.roperos.add(ropero);
    }
    public Atuendo pedirAtuendo(int i){
        return this.roperos.get(i).sugerirAtuendo();
    }
    public int cantidadDeAtuendosDisponibles(int i){ return this.roperos.get(i).cantidadDeAtuendosPosibles(); }
    public void cambiarMembrecia(Membrecia membrecia) {
        this.membrecia = membrecia;
    }
    public int cantidadDeRoperos() { return this.roperos.size(); }
    public boolean tengoGuardaropas(Guardaropa ropero){
        return this.roperos.contains(ropero);
    }
    public List<Guardaropa> misRoperos(){ return this.roperos;}
    public Membrecia miMembrecia(){ return this.membrecia;}
    
    @Override
    public void updateEventoProximo(Evento evento) {
        List<Atuendo> atuendos = this.roperos.stream()
                .map(ropero -> ropero.sugerirAtuendo(this.meteorologo.getPronosticoTiempoYUbicacion(evento.horaComienzo(),evento.ubicacion()),evento,this))
                .collect(Collectors.toList());
        evento.agregarSugerencias(atuendos);
    }
    public int getSensacionTermica() {
         return this.sensacionTermica;
    }
    public void setSensacionTermica(int sensacion) {
        this.sensacionTermica = sensacion;
    }
    
}
