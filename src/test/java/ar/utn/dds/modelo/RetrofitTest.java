package ar.utn.dds.modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class RetrofitTest {

	@Test
	public void test() {
		Meteorologo meteorologo = new ServicioWeather();
		Ubicacion ubicacion= new Ubicacion();
//		Pronostico prono = meteorologo.getPronostico("Buenos Aires", "ar");
		Pronostico pronostico = meteorologo.getPronostico(ubicacion.ciudad, ubicacion.pais);
		assertEquals(pronostico.cod, "200");
		
	}

}
