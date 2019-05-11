package ar.utn.dds.excepciones;

public class EsaPrendaYaLaTengo extends RuntimeException {
    public EsaPrendaYaLaTengo(){
        super("Ya tengo ese pito de prenda");
    }
}
