package ar.utn.dds.excepciones;

public class CantidadDeArmariosMaximo extends RuntimeException {
    public CantidadDeArmariosMaximo(){
        super("Alcanzo la cantidad maxima de armarios posibles. Si queres más armarios subscribite a Spotify premium");
    }
}
