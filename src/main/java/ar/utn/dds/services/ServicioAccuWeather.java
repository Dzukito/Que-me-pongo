package ar.utn.dds.services;

import ar.utn.dds.modelo.Meteorologo;
import ar.utn.dds.modelo.Pronostico;
import ar.utn.dds.modelo.TipoClima;
import ar.utn.dds.modelo.Ubicacion;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class ServicioAccuWeather implements Meteorologo{
	
	@Override
	public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
		int hora = (int)(Duration.between(Calendar.getInstance().toInstant(),tiempo.toInstant()).toHours()/3);
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://dataservice.accuweather.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		PedidoPronosticoAccuWeather service = retrofit.create(PedidoPronosticoAccuWeather.class);
		Call<RespuestaAccuWeather> call = service.getPronosticoClima("7894", "G39EfxP92l61brpxjQUED4oQdpodARKH");
		try{
			Response<RespuestaAccuWeather> response = call.execute();
			RespuestaAccuWeather pronosticoAccuWeather = response.body();
			Pronostico pronostico = new Pronostico();
			System.out.print("hii");
			System.out.print(pronosticoAccuWeather.Headline.Text);
			/*
			Date fechaAux = new Date(pronosticoWeather.list.get(hora).dt*1000);
			pronostico.setTemperatura(pronosticoAccuWeather.list.get(hora).main.temp);
			pronostico.setHumerdad(pronosticoWeather.list.get(hora).main.humidity);
			if(pronosticoWeather.list.get(hora).clouds.all > 50){
				pronostico.agregarClimatologia(TipoClima.NUBLADO);
			}
			if(pronosticoWeather.list.get(hora).clouds.all <= 50){
				pronostico.agregarClimatologia(TipoClima.SOLEADO);
			}
			if(pronosticoWeather.list.get(hora).wind.speed > 40){
				pronostico.agregarClimatologia(TipoClima.VENTOSO);
			}
			if(pronosticoWeather.list.get(hora).rain!=null) {
				if(pronosticoWeather.list.get(hora).main.temp < 273){
					pronostico.agregarClimatologia(TipoClima.NEVANDO);
				}else {
					pronostico.agregarClimatologia(TipoClima.LLUVIOSO);
				}
			}
			*/
			return pronostico;
		}
		catch (Exception ex){
			System.out.println("Error");
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public Pronostico getPronostico(String ciudad, String pais) {
		Retrofit retrofit = new Retrofit.Builder()
	            .baseUrl("http://dataservice.accuweather.com")
	            .addConverterFactory(GsonConverterFactory.create())
	            .build();
	
		PedidoPronosticoAccuWeather service = retrofit.create(PedidoPronosticoAccuWeather.class);
	
	    Call<RespuestaAccuWeather> call = service.getPronosticoClima("7894", "G39EfxP92l61brpxjQUED4oQdpodARKH");
	 
	    try{
	        Response<RespuestaAccuWeather> response = call.execute();
	        RespuestaAccuWeather pronosticoAccuWeather = response.body();
	        Pronostico pronostico = new Pronostico();
			System.out.print(pronosticoAccuWeather.Headline.Text);
			/*
			System.out.println(pronosticoWeather.list);
	       	Date fechaAux = new Date(pronosticoWeather.list.get(0).dt*1000);
	        pronostico.setFecha(fechaAux);
			System.out.println("Fecha");
			System.out.println( new Date(pronosticoWeather.list.get(0).dt*1000));
	        System.out.println(pronostico.getFecha());
	        pronostico.setTemperatura(pronosticoWeather.list.get(0).main.temp);
			System.out.println("Temperatura");
	        System.out.println(pronostico.temperatura());
	        pronostico.setNubosidad(pronosticoWeather.list.get(0).clouds.all);
			System.out.println("nubosidad");
	        System.out.println(pronostico.nubosidad());
	        pronostico.setViento(pronosticoWeather.list.get(0).wind.speed);
			System.out.println("Viento");
	        System.out.println(pronostico.getViento());
	        
//este if es xq el json puede no traer la cantidad de agua al llover
	        if(pronosticoWeather.list.get(0).rain==null) {
	        	pronostico.setPrecipitacion(0);
	        }
	        else {
	        	pronostico.setPrecipitacion(pronosticoWeather.list.get(0).rain.precititacion);
	        }
	        */
//	        System.out.println(pronostico.getPrecipitacion());
	        return pronostico;
	       
	    }
	    catch (Exception ex){
	    	System.out.println("ErrorX");
	        System.out.println(ex.getMessage());
	    }
		
		return null;
	}

}
