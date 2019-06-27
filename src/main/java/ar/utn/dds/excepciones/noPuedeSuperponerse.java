package ar.utn.dds.excepciones;

public class noPuedeSuperponerse extends RuntimeException {
    public noPuedeSuperponerse(){
        super("Esa prenda no puede superponerse con las que ya tengo");
    }
}
