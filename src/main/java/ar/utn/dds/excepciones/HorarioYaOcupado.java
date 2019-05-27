package ar.utn.dds.excepciones;

public class HorarioYaOcupado extends RuntimeException {
    public HorarioYaOcupado(){
        super("En ese horario, ya prefijo otro evento.");
    }
}
