package ar.utn.dds.modelo;

import java.util.Calendar;

public class MeteorologoMockOneAdapter implements Meteorologo {
    @Override
    public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
        return null;
    }

    @Override
    public Pronostico getPronostico(String ciudad, String Pais) {
        return null;
    }
    
    @Override
    public void getPronosticos(Ubicacion ubicacion) {       
    }
    @Override
    public boolean alertaMeteorologica(Pronostico pronosticoEvento, Pronostico nuevoPronostico) {
        return !pronosticoEvento.somosSimilares(nuevoPronostico);
    }
}
