package ar.utn.dds.services;

import ar.utn.dds.modelo.Meteorologo;
import ar.utn.dds.modelo.Pronostico;
import ar.utn.dds.modelo.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Calendar;


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
			pronostico.setCod(pronosticoWeather.cod);
	        System.out.print(pronosticoWeather.cod);
	        return pronostico;
	    }
	    catch (Exception ex){
	        System.out.print(ex.getMessage());
	    }
		
		return null;
	}

}
