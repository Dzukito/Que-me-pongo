package ar.utn.dds.services;

import ar.utn.dds.modelo.Meteorologo;
import ar.utn.dds.modelo.Pronostico;
import ar.utn.dds.modelo.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


public class ServicioWeather implements Meteorologo {

	@Override
	public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
		return null;
	}

	@Override
	public Pronostico getPronostico(String ciudad, String pais) {
		Retrofit retrofit = new Retrofit.Builder()
	            .baseUrl("https://api.openweathermap.org")
	            .addConverterFactory(GsonConverterFactory.create())
	            .build();
	
		PedidoPronosticoWeather service = retrofit.create(PedidoPronosticoWeather.class);
	
	    Call<RespuestaWeather> call = service.getPronosticoClima(ciudad+","+pais, "09f619848d8f17af4cdfc78973198187");
	 
	    try{
	        Response<RespuestaWeather> response = call.execute();
	        RespuestaWeather pronosticoWeather = response.body();
	        Pronostico pronostico = new Pronostico();
	       	Date fechaAux = new Date(pronosticoWeather.list.get(0).dt*1000);    
	        pronostico.setFecha(fechaAux);
	        System.out.println(pronostico.getFecha());
	        pronostico.setTemperatura(pronosticoWeather.list.get(0).main.temp);
	        System.out.println(pronostico.temperatura());
	        pronostico.setNubosidad(pronosticoWeather.list.get(0).clouds.all);
	        System.out.println(pronostico.nubosidad());
	        pronostico.setViento(pronosticoWeather.list.get(0).wind.speed);
	        System.out.println(pronostico.getViento());
//este if es xq el json puede no traer la cantidad de agua al llover
	        if(pronosticoWeather.list.get(0).rain==null) {
	        	pronostico.setPrecipitacion(0);
	        }
	        else {
	        	pronostico.setPrecipitacion(pronosticoWeather.list.get(0).rain.precititacion);
	        }
	        System.out.println(pronostico.getPrecipitacion());
	        return pronostico;
	       
	    }
	    catch (Exception ex){
	    	System.out.println("Error");
	        System.out.println(ex.getMessage());
	    }
		
		return null;
	}

}
