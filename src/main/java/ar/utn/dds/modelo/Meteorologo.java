package ar.utn.dds.modelo;

import java.util.Calendar;
import java.util.List;

public interface Meteorologo {
    public List<Pronostico> getPronosticosPorCincoDias();
	public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion);
    public void getPronosticos(Ubicacion ubicacion);
    public boolean alertaMeteorologica(Pronostico pronosticoEvento, Pronostico nuevoPronostico);
}
