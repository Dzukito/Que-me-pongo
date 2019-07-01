package ar.utn.dds.excepciones;

public class NivelDeCalorIncorrecto extends RuntimeException {
    public NivelDeCalorIncorrecto(){ super("El nivel de calor esta fuera de los parametros."); }
}
