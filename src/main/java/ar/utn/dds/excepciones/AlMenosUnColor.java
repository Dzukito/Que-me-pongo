package ar.utn.dds.excepciones;

public class AlMenosUnColor extends RuntimeException {
    public AlMenosUnColor(){
        super("Al menos tiene que tener un color");
    }
}
