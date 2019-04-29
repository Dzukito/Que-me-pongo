package ar.utn.dds.excepciones;

public class SoloTieneUnColor extends RuntimeException {
    public SoloTieneUnColor(){
        super("Solo tiene un color");
    }
}
