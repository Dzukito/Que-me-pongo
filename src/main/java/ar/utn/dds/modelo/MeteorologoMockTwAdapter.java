package ar.utn.dds.modelo;

import java.util.Calendar;
import java.util.List;

public class MeteorologoMockTwAdapter implements Meteorologo{
    @Override
    public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
        return null;
    }
    
    @Override
    public void getPronosticos(Ubicacion ubicacion) {     
    }	
    
    @Override
    public boolean alertaMeteorologica(Pronostico pronosticoEvento, Pronostico nuevoPronostico) {
        return !pronosticoEvento.somosSimilares(nuevoPronostico);
    }

	@Override
	public List<Pronostico> getPronosticosPorCincoDias() {
		return null;
	}

}
