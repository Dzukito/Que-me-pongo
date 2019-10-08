package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Enviador {

    private String direccion;
    private String asunto;
    private String mensaje;

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public abstract void enviar(String direccion,String asunto, String mensaje);
    public abstract void enviar(String direccion, String asunto, String mensaje, ArrayList<String> image);
    public abstract void enviarSugerencia(String direccion, String asunto, String mensaje, ArrayList<String> image);
    public abstract void enviarAlertaMeteorologica(String direccion, String asunto, String mensaje, List<List<String>> image);
}
