package ar.utn.dds.modelo;

import ar.utn.dds.Constantes;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Evento implements AceptarSuegerenciaObservador, RechazarSugerenciaObservador{
    private Calendar horaComienzo;
    private Calendar horaTermino;
    private Ubicacion ubicacion;
    private Estilo estilo;
    private Atuendo atuendo;
    private ArrayList<Atuendo> atuendosSugeridos;
    private int tiempoAviso;


    public void alertaMeteorologica(Usuario usuario, Guardaropa guardaropa, Pronostico pronostico){
        usuario.getEnviadores().forEach(enviador -> enviador.enviarAlertaMeteorologica(
                enviador.getDireccion(),
                "Alerta meteorologica",
                "Desea cambiar su atuendo a uno mas adecuado a las nuevas condiciones meteorologicas, le sugerimos:",
                guardaropa.sugerirAtuendo(pronostico,this,usuario).getImagenes()));
    }
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
    //Constructores---------------------------------------------------
    Evento(Calendar horaComienzo, Calendar horaTermino, Ubicacion ubicacion, Estilo estilo){
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
    public ArrayList<Atuendo> sugerencias() { return this.atuendosSugeridos; }
    @Override
    public void updateRechazarSugerencia(Atuendo atuendo) { }
    @Override
    public void downdateRechazarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) { }

}
