package ar.utn.dds.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import ar.utn.dds.services.ServicioWeather;
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
		Pronostico pronostico = meteorologo.getPronostico(buenosAires.ciudad(), buenosAires.pais());
		Long fechaFormatoJson=(long) 1560146400;
		//x1000  para instanciar fecha de UNIX TimeStamp a fecha
		Date fecha1 = new Date(fechaFormatoJson*1000);
		System.out.println(pronostico.getFecha());
		System.out.println(fecha1);
		assertEquals(pronostico.getFecha(), fecha1);
		assertEquals(pronostico.temperatura(), 289.63, 289.63);
	}

}
