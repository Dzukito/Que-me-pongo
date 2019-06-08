package ar.utn.dds.services;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface PedidoPronosticoWeather {
	
	
	@GET("/data/2.5/forecast")
    Call<RespuestaWeather> getPronosticoClima(@Query("q") String ciudadPais,@Query("APPID") String key);
}
