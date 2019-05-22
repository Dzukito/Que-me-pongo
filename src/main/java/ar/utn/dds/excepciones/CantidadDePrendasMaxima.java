package ar.utn.dds.excepciones;

public class CantidadDePrendasMaxima extends RuntimeException{
    public CantidadDePrendasMaxima(){
        super("Alcanzo la cantidad maxima de prendas");
    }
}
