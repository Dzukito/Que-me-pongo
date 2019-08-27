package ar.utn.dds.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;



public class RetrofitTest {
	Ubicacion buenosAires;
	@Before
	public void setup(){
		buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
		//consultar api weather: https://api.openweathermap.org/data/2.5/forecast?q=Buenos%20Aires,ar&APPID=09f619848d8f17af4cdfc78973198187
		//consultar api accuWeather:http://dataservice.accuweather.com/forecasts/v1/daily/5day/7894?apikey=KJ3wprAzf3OnuS9ACuOsWpe1eFDerpQG&details=true
		
	}

	@Test
	public void test1() {
		Meteorologo meteorologo = new MeteorologoAccuWeatherAdapter();
		meteorologo.getPronosticos(buenosAires);
		Long fechaFormatoJson=(long) 1566939600;
		//x1000  para instanciar fecha de UNIX TimeStamp a fecha
		Date fecha1 = new Date(fechaFormatoJson*1000);
		Calendar fecha = Calendar.getInstance();
    	fecha.setTime(fecha1);
    	Pronostico pronostico = meteorologo.getPronosticoTiempoYUbicacion(fecha, buenosAires);
    	
		System.out.println("test 1");
		System.out.println(fecha1);
		System.out.println(pronostico.getNubosidad());
		System.out.println(pronostico.getViento());
		System.out.println(pronostico.getPrecipitacion());
		System.out.println(pronostico.getTemperatura());
		System.out.println(pronostico.getFecha().getTime());

		assertEquals(67.0,pronostico.getNubosidad(), 30);
		assertEquals(7.5,pronostico.getViento(), 10);
		assertEquals(6.0,pronostico.getPrecipitacion(), 5);
		assertEquals(15,pronostico.getTemperatura(), 10);
//		assertEquals( fecha1,pronostico.getFecha().getTime());
	}
	
	
	@Test
	public void test2() {
		System.out.println("test 2");
		Meteorologo meteorologo = new MeteorologoWeatherAdapter();
		meteorologo.getPronosticos(buenosAires);
		Long fechaFormatoJson=(long) 1566939600;
		Date fecha1 = new Date(fechaFormatoJson*1000);
		Calendar fecha = Calendar.getInstance();
    	fecha.setTime(fecha1);
    	Pronostico pronostico = meteorologo.getPronosticoTiempoYUbicacion(fecha, buenosAires);
    	
		System.out.println(fecha1);
		System.out.println(pronostico.getNubosidad());
		System.out.println(pronostico.getViento());
		System.out.println(pronostico.getPrecipitacion());
		System.out.println(pronostico.getTemperatura());
		System.out.println(pronostico.getFecha().getTime());
		
		assertEquals(80,pronostico.getNubosidad(), 30);
		assertEquals(3,pronostico.getViento(), 10);
		assertEquals(2,pronostico.getPrecipitacion(), 5);
		assertEquals(17,pronostico.getTemperatura(), 10);
//		assertEquals( fecha1,pronostico.getFecha().getTime());
	}

}
