package ar.utn.dds.excepciones;

public class ElMaterialNoPerteneceALaPrenda extends RuntimeException {
    public ElMaterialNoPerteneceALaPrenda(){
        super("El material no pertenece a la prenda");
    }
}
