package ar.utn.dds.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PedidoPronosticoAccuWeather {
	
	@GET("/forecasts/v1/daily/5day/{claveUbicacion}")
    Call<RespuestaAccuWeather> getPronosticoClima(@Path("claveUbicacion") String claveUbicacion,@Query("apikey") String key);

}
