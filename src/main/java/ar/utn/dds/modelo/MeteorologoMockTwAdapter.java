package ar.utn.dds.modelo;

import java.util.Calendar;

public class MeteorologoMockTwAdapter implements Meteorologo{
    @Override
    public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
        return null;
    }

    @Override
    public Pronostico getPronostico(String ciudad, String Pais) {
        return null;
    }
}
