package ar.utn.dds.modelo;

import java.util.Calendar;

public interface Meteorologo {
    public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion);
    public Pronostico getPronostico(String ciudad, String Pais);
    public void getPronosticos(Ubicacion ubicacion);
}
