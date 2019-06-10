package ar.utn.dds.modelo;

import ar.utn.dds.services.PedidoPronosticoWeather;
import ar.utn.dds.services.RespuestaWeather;
import ar.utn.dds.services.ServicioWeather;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class MeteorologoWeatherAdapter extends ServicioWeather implements Meteorologo {


    MeteorologoWeatherAdapter(){
        super();
    }

    @Override
    public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
        int hora = (int)(Duration.between(Calendar.getInstance().toInstant(),tiempo.toInstant()).toHours()/3);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PedidoPronosticoWeather service = retrofit.create(PedidoPronosticoWeather.class);
        Call<RespuestaWeather> call = service.getPronosticoClima(ubicacion.ciudad()+","+ubicacion.pais(), "09f619848d8f17af4cdfc78973198187");
        try{
            Response<RespuestaWeather> response = call.execute();
            RespuestaWeather pronosticoWeather = response.body();
            Pronostico pronostico = new Pronostico();
            Date fechaAux = new Date(pronosticoWeather.list.get(hora).dt*1000);
            pronostico.setTemperatura(pronosticoWeather.list.get(hora).main.temp);
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
            return pronostico;
        }
        catch (Exception ex){
            System.out.println("Error");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Pronostico getPronostico(String ciudad, String Pais) {
        return null;
    }
}
