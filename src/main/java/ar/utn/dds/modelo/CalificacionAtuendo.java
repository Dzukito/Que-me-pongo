package ar.utn.dds.modelo;

public class CalificacionAtuendo {
    private Pronostico pronostico;
    private Atuendo atuendo;
    private Evento evento;
    private Usuario usuario;
    private int calificacion;
    //Metodos-que-no-se-usan---------------------------------
    public void ajustarPreferencia(Usuario usuario) {

        usuario.getSensibilidad().ajustarPreferencias(this.atuendo.);
    }
    //Metodos-privados---------------------------------------
    //Metodos-publicos---------------------------------------
    //Getters-y-Setters--------------------------------------

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
