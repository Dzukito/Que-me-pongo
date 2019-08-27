package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ar.utn.dds.services.apiAccuWeather.PedidoPronosticoAccuWeather;
import ar.utn.dds.services.apiAccuWeather.PronosticoAccuWeather;
import ar.utn.dds.services.apiAccuWeather.RespuestaAccuWeather;
import ar.utn.dds.services.apiAccuWeather.TemperaturaAccuWeather;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeteorologoAccuWeatherAdapter implements Meteorologo {
	
	public List<Pronostico> pronosticosPorCincoDias;
	
	MeteorologoAccuWeatherAdapter(){
        super();
    }
	
	public List<Pronostico> getPronosticosPorCincoDias() {
		return pronosticosPorCincoDias;
	}
	
	@Override
    public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
		
		 try{	
		    	
		    Optional<Pronostico> pronosticoOpcional = this.getPronosticosPorCincoDias().stream().filter(pronostico->pronostico.getFecha().get(Calendar.YEAR) == tiempo.get(Calendar.YEAR)
		    			&& pronostico.getFecha().get(Calendar.MONTH)==tiempo.get(Calendar.MONTH) &&
		    			pronostico.getFecha().get(Calendar.DATE)==tiempo.get(Calendar.DATE)).findFirst();
		    	
		    	if(pronosticoOpcional.isPresent()) {
		    		return pronosticoOpcional.get();
		    	}
		    	
		    	return null;
		    	       
		    }
		    catch (Exception ex){
		    	System.out.println("Error al obtener pronostico");
		        System.out.println(ex.getMessage());
		    }
			
			return null;
    }

	@Override
	public void getPronosticos(Ubicacion ubicacion) {
		
		if(this.getPronosticosPorCincoDias()==null) {
			this.pronosticosPorCincoDias=new ArrayList<Pronostico>();
		}else {
			this.getPronosticosPorCincoDias().clear();
		}
		
		Retrofit retrofit = new Retrofit.Builder()
	            .baseUrl("http://dataservice.accuweather.com")
	            .addConverterFactory(GsonConverterFactory.create())
	            .build();
	
		PedidoPronosticoAccuWeather service = retrofit.create(PedidoPronosticoAccuWeather.class);
	
	    Call<RespuestaAccuWeather> call = service.getPronosticoClima("7894", "KJ3wprAzf3OnuS9ACuOsWpe1eFDerpQG", true);
	   
	    try{
	        Response<RespuestaAccuWeather> response = call.execute();
	        RespuestaAccuWeather pronosticoAccuWeather = response.body();
	        
	        for(int i = 0;i< pronosticoAccuWeather.DailyForecasts.size();i=i+1) {
	        	Pronostico pronostico = new Pronostico();
	        	Date fechaAux = new Date(pronosticoAccuWeather.DailyForecasts.get(i).EpochDate*1000);
	        	Calendar fecha = Calendar.getInstance();
	        	fecha.setTime(fechaAux);
		        pronostico.setFecha(fecha);		     
		        
		        double temperatura=calcularTemperaturaPromedio(pronosticoAccuWeather.DailyForecasts.get(i).Temperature);
		        pronostico.setTemperatura(temperatura);
		        
		        int clouds= calcularNubosidadPromedio(pronosticoAccuWeather.DailyForecasts.get(i));
		        pronostico.setNubosidad(clouds);
		        
		        double viento=calcularVientoPromedio(pronosticoAccuWeather.DailyForecasts.get(i));
		        pronostico.setViento(viento);
		        
		        int rain=calcularLluviaPromedio(pronosticoAccuWeather.DailyForecasts.get(i));
		        pronostico.setPrecipitacion(rain);
/*		        
		        System.out.println(pronostico.getFecha().getTime());
		        System.out.println(pronostico.getTemperatura());
		        System.out.println(pronostico.getNubosidad());
		        System.out.println(pronostico.getViento());
		        System.out.println(pronostico.getPrecipitacion());
*/		        
		        this.pronosticosPorCincoDias.add(pronostico);
		        
	        }
	        
	       
	    }
	    catch (Exception ex){
	    	System.out.println("Error al llamar a la api");
	        System.out.println(ex.getMessage());
	    }
	}

	@Override
	public boolean alertaMeteorologica(Pronostico pronosticoEvento, Pronostico nuevoPronostico) {
		return !pronosticoEvento.somosSimilares(nuevoPronostico);
	}


	private int calcularLluviaPromedio(PronosticoAccuWeather pronosticoAccuWeather) {
		int rainDay=pronosticoAccuWeather.Day.RainProbability;
		int rainNight=pronosticoAccuWeather.Night.RainProbability;
		return (rainDay+rainNight)/2;
	}
	
	private double calcularVientoPromedio(PronosticoAccuWeather pronosticoAccuWeather) {
		double vientoDiurno=pronosticoAccuWeather.Day.Wind.Speed.Value;
		double vientoNocturno=pronosticoAccuWeather.Night.Wind.Speed.Value;
		return (vientoDiurno+vientoNocturno)/2;
	}
	
	private int calcularNubosidadPromedio(PronosticoAccuWeather pronosticoAccuWeather) {
		int cloudsDiurno=pronosticoAccuWeather.Day.CloudCover;
		int cloudsNocturno=pronosticoAccuWeather.Night.CloudCover;
		return (cloudsDiurno+cloudsNocturno)/2;
	}
	
	private double calcularTemperaturaPromedio(TemperaturaAccuWeather temperatura) {
		double temperaturaMax=temperatura.Maximum.Value;
		double temperaturaMin=temperatura.Minimum.Value;
		if(temperatura.Maximum.Unit.equalsIgnoreCase("F")) {
			temperaturaMax=(temperatura.Maximum.Value-32)*(5.0/9.0);
			temperaturaMin=(temperatura.Minimum.Value-32)*(5.0/9.0);
		}
		return (temperaturaMax+temperaturaMin)/2;
	}
	
    @Override
    public Pronostico getPronostico(String ciudad, String Pais) {
        return null;
    }

}
