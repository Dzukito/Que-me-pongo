package ar.utn.dds.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RetrofitTest {
	Ubicacion buenosAires;
	@Before
	public void setup(){
		buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
	}
	@Test
	public void test() {
		Meteorologo meteorologo = new ServicioWeather();
//		Pronostico prono = meteorologo.getPronostico("Buenos Aires", "ar");
		Pronostico pronostico = meteorologo.getPronostico(buenosAires.ciudad(), buenosAires.pais());
		assertEquals(pronostico.cod, "200");
		
	}

}
