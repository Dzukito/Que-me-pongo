package ar.utn.dds.modelo;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import ar.utn.dds.services.PedidoPronosticoAccuWeather;
import ar.utn.dds.services.RespuestaAccuWeather;
import ar.utn.dds.services.ServicioAccuWeather;
import ar.utn.dds.services.CabeceraAccuWeather;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeteorologoAccuWeatherAdapter extends ServicioAccuWeather implements Meteorologo {
	
	MeteorologoAccuWeatherAdapter(){
        super();
    }
	
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
            System.out.print("hola");
            System.out.print(pronosticoAccuWeather.Headline.Text);
            /*
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
    public Pronostico getPronostico(String ciudad, String Pais) {
        return null;
    }

}
