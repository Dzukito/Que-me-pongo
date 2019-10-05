package ar.utn.dds.modelo;

import ar.utn.dds.services.PedidoPronosticoWeather;
import ar.utn.dds.services.RespuestaWeather;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MeteorologoWeatherAdapter implements Meteorologo {

	public List<Pronostico> pronosticosPorCincoDias;
	
    MeteorologoWeatherAdapter(){
        super();
    }

    public List<Pronostico> getPronosticosPorCincoDias() {
		return pronosticosPorCincoDias;
	}
    
	@Override
	public boolean alertaMeteorologica(Pronostico pronosticoEvento, Pronostico nuevoPronostico) {
		return !pronosticoEvento.somosSimilares(nuevoPronostico);
	}
	
    @Override
    public Pronostico getPronosticoTiempoYUbicacion(Calendar tiempo, Ubicacion ubicacion) {
    	
    	try{
			
	    	List<Pronostico> pronosticosDelDia =(List<Pronostico>) this.getPronosticosPorCincoDias().stream().filter(pronostico->pronostico.getFecha().get(Calendar.YEAR) == tiempo.get(Calendar.YEAR)
	    			&& pronostico.getFecha().get(Calendar.MONTH)+1==tiempo.get(Calendar.MONTH)+1 &&
	    			pronostico.getFecha().get(Calendar.DATE)==tiempo.get(Calendar.DATE)).collect(Collectors.toList());
	    	
	    	if(pronosticosDelDia.equals(null)|| pronosticosDelDia.isEmpty()) {
	    		System.out.println("No se encontro pronostico");
	    		return null;
	    	}
	    	
	    	int i=0;
	    	
	    	for(i=0;pronosticosDelDia.size()>i && pronosticosDelDia.get(i).getFecha().before(tiempo);i++);
	    	
	    	if(i==0) {
	    		i=i+1; 
	    	}

	    	return pronosticosDelDia.get(i-1);
	    }
	    catch (Exception ex){
	    	System.out.println("Error al obtener pronostico");
	        System.out.println(ex.getMessage());
	        throw ex;
	    }
    	
    }
    
    @Override
	public void getPronosticos(Ubicacion ubicacion) {
		
		if(this.getPronosticosPorCincoDias()==null) {
			this.pronosticosPorCincoDias=new ArrayList<Pronostico>();
		}else {
			this.getPronosticosPorCincoDias().clear();
		}
		
		
		Retrofit retrofit = new Retrofit.Builder()
	            .baseUrl("https://api.openweathermap.org")
	            .addConverterFactory(GsonConverterFactory.create())
	            .build();
	
		PedidoPronosticoWeather service = retrofit.create(PedidoPronosticoWeather.class);
	
	    Call<RespuestaWeather> call = service.getPronosticoClima(ubicacion.ciudad()+","+ubicacion.pais(), "09f619848d8f17af4cdfc78973198187");
	   
	    try{
	        Response<RespuestaWeather> response = call.execute();
	        RespuestaWeather pronosticoWeather = response.body();
	        
	        for(int i = 0;i< pronosticoWeather.list.size();i=i+1) {
	        	
	        	Pronostico pronostico = new Pronostico();
	        	
	        	Date fechaAux = new Date(pronosticoWeather.list.get(i).dt*1000);
	        	
	        	Calendar fecha = Calendar.getInstance();
	        	
	        	fecha.setTime(fechaAux);
		        pronostico.setFecha(fecha);
		        
		        pronostico.setTemperatura(pronosticoWeather.list.get(i).main.temp-273);
		        
		        pronostico.setNubosidad(pronosticoWeather.list.get(i).clouds.all);
		       
		        pronostico.setViento(pronosticoWeather.list.get(i).wind.speed);
		        
		      //este if es xq el json puede no traer la cantidad de agua al llover
		        if(pronosticoWeather.list.get(i).rain==null) {
		        	pronostico.setPrecipitacion(0);
		        }
		        else {
		        	pronostico.setPrecipitacion((float) (pronosticoWeather.list.get(i).rain.precititacion));
		        }
		        
		        if(pronostico.getNubosidad()> 50.0){
					pronostico.agregarClimatologia(TipoClima.NUBLADO);
				}
		        
				if(pronostico.getNubosidad()<= 50.0){
					pronostico.agregarClimatologia(TipoClima.SOLEADO);
				}
				
				if(pronostico.getViento() > 40.0){
					pronostico.agregarClimatologia(TipoClima.VENTOSO);
				}
				
				if(pronostico.getPrecipitacion()> 0) {
					pronostico.agregarClimatologia(TipoClima.LLUVIOSO);
				}
				
				
		        this.pronosticosPorCincoDias.add(pronostico);

//		        System.out.println(pronostico.getFecha().getTime());
//		        System.out.println(pronostico.getTemperatura());
//		        System.out.println(pronostico.getNubosidad());
//		        System.out.println(pronostico.getViento());
//		        System.out.println(pronostico.getPrecipitacion());
		        
	        }
	       
	    }
	    catch (Exception ex){
	    	System.out.println("Error al llamar a la API");
	        System.out.println(ex.getMessage());
	    }
		
	}
    
}
