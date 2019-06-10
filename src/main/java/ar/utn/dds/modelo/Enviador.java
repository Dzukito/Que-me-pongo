package ar.utn.dds.modelo;

import java.util.ArrayList;

public abstract class Enviador {
    private String direccion;
    private String asunto;
    private String mensaje;

    public abstract void enviar(String direccion,String asunto, String mensaje);
    public abstract void enviar(String direccion, String asunto, String mensaje, ArrayList<String> image);
}
