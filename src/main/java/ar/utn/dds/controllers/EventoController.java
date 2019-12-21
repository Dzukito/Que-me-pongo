package ar.utn.dds.controllers;
import ar.utn.dds.modelo.clases.*;
import ar.utn.dds.modelo.clima.MeteorologoAccuWeatherAdapter;
import ar.utn.dds.modelo.clima.Pronostico;
import ar.utn.dds.modelo.interfaces.Meteorologo;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.sensibilidades.Ermitanio;
import ar.utn.dds.repositories.*;
import ar.utn.dds.repositories.factories.*;
import ar.utn.dds.spark.utils.AtuendoCalifVista;
import ar.utn.dds.spark.utils.EventoVista;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class EventoController {
    private RepositorioEvento repo;
	private RepositorioOutfit repoOutfit;

    public EventoController(){
        this.repo = FactoryRepositorioEvento.get();
		this.repoOutfit = FactoryRepositorioAtuendo.get();
    }
	public Response seleccionarAtuendo(Request request, Response response) {
    	long id_evento = new Long(request.params("id_evento"));
		long id_atuendo = new Long(request.params("id_atuendo"));
		Evento evento =	repo.buscar(id_evento);
		Atuendo atuendo = repoOutfit.buscar(id_atuendo);
		evento.setAtuendo(atuendo);
		repo.modificar(evento);
		response.redirect("/events");
		return response;

	}

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        RepositorioUsuario repoUsuario=FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
        List<Evento> eventos = usuario.getEventos();
        List<Estilo> estilos= Arrays.asList(Estilo.values());
        List<EventoVista> eventosVista=eventos.stream().map(e->this.crearVistaE(e,usuario)).collect(Collectors.toList());
        parametros.put("eventos", eventosVista);
        parametros.put("estilos", estilos);
        parametros.put("login", LoginController.isUsuarioLogin(request));
//        return new ModelAndView(parametros, "eventos1.hbs");	
        return  new ModelAndView(parametros, "eventos.hbs");
    }
    public EventoVista crearVistaE(Evento evento,Usuario usuario) {
    	EventoVista eventoVista=new EventoVista(evento,usuario);
    	return eventoVista;
    }
    public ModelAndView mostrarAtuendo(Request request, Response response){
//		Map<String, Object> parametros = new HashMap<>();
		Guardaropa guardaropa = null;
        Long idEvento = new Long(request.params(":id_evento"));
        Evento evento = repo.buscar(idEvento);
////        List<AtuendoCalifVista> atuendosElegantes = evento.agregarSugerencias();
        RepositorioUsuario repoUsuario=FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
//
//        if(usuario.getEventos().contains(evento)) {
//        	parametros.put("evento", evento);
//        	parametros.put("login", LoginController.isUsuarioLogin(request));
//        	parametros.put("atuendo", evento.getAtuendo());
//        	parametros.put("atuendos", evento.sugerencias());
//        }
//
//        parametros.put("login", LoginController.isUsuarioLogin(request));

		Map<String, Object> parametros = new HashMap<>();

		Atuendo atuendoSugerido=null;
		Meteorologo meteorologo = new MeteorologoAccuWeatherAdapter();

		RepositorioUbicacion repoUbicacion=FactoryRepositorioUbicacion.get();
		Ubicacion buenosAires=repoUbicacion.buscarTodos().get(0);
		Pronostico pronostico;
		Estilo estilo=null;

		String estiloRecibido= evento.getEstilo().toString();
		meteorologo.getPronosticos(buenosAires);
		List<Atuendo> atuendos = new ArrayList<Atuendo>();
		/*1) Seteo el Estilo que quiere mi usuario para la sugerencia*/
		if(evento.getEstilo().toString() != null){

			switch(estiloRecibido) {
				case "ELEGANTE":
					estilo=(Estilo.ELEGANTE);
					break;
				case "ELEGANTSPORT":
					estilo=(Estilo.ELEGANTSPORT);
					break;
				case "DEPORTIVO":
					estilo=(Estilo.DEPORTIVO);
					break;
				case "ENTRECASA":
					estilo=(Estilo.ENTRECASA);
					break;
				case "NAVIDENIO":
					estilo=(Estilo.NAVIDENIO);
					break;
				case "NORMAL":
					estilo=(Estilo.NORMAL);
					break;
				case "PLAYERO":
					estilo=(Estilo.PLAYERO);
					break;}
		}

		/*2)Verifico guardaropa para ese usuario*/

		if(usuario.getGuardaropa(0) != null){

			usuario.setSensibilidad(new Ermitanio());


			guardaropa = usuario.getGuardaropa(0);


			if(guardaropa!=null) {
				Calendar fecha = Calendar.getInstance();
				fecha.add(Calendar.HOUR, 9);
				pronostico = meteorologo.getPronosticoTiempoYUbicacion(fecha, buenosAires); //seteo el pronostico
				if(evento.sugerencias().isEmpty()){
					for(int i = 0; i<5;i++){
						atuendoSugerido=new Atuendo();
						RepositorioPrenda repoPrenda= FactoryRepositorioPrenda.get();
						List<Prenda> ps= repoPrenda.buscarTodos();
						List<Prenda> prendasEstilo =ps.stream().filter(p->p.getEstilos().get(0).toString().compareTo(estiloRecibido)==0).filter(prenda -> prenda.tengoEstilo(evento.getEstilo())).collect(Collectors.toList());
						if(!prendasEstilo.isEmpty()) {
							Prenda p1 = prendasEstilo.get((int) (Math.random() * prendasEstilo.size() + 0));
							atuendoSugerido.agregarPrenda(p1);
							guardaropa.sugerirAtuendoSinEvento(estilo, atuendoSugerido);

							this.repoOutfit.agregar(atuendoSugerido);
							evento.agregarSugerencia(atuendoSugerido);
							atuendos.add(atuendoSugerido);
						}
					}
				}else{
					evento.sugerencias().forEach(atuendo -> atuendos.add(atuendo));
				}
			}

		}
		List<Atuendo> atuendoElegido = (List<Atuendo>) new ArrayList<Atuendo>(Arrays.asList(evento.getAtuendo()));
		if(!(evento.getAtuendo() == null)){
			parametros.put("atuendo", atuendoElegido);
			parametros.put("id_atuendo",evento.getAtuendo().getId_atuendo())	;
		}
		parametros.put("id_evento",evento.getId_evento());
		parametros.put("login", LoginController.isUsuarioLogin(request));
		parametros.put("guardaropaId", guardaropa.getId_guardaropa());
		parametros.put("estilo",request.params(":nombreEstilo"));
		parametros.put("atuendos", atuendos);
        return  new ModelAndView(parametros, "AtuendosDeEvento.hbs");
    }



    private void asignarAtributosA(Evento evento, Request request){
    	
    	if(request.queryParams("event-name") != null){
			evento.setNombre(request.queryParams("event-name"));
        }
        
        if(request.queryParams("event-location") != null){
			evento.setLugar(request.queryParams("event-location"));
        }
        
        if(request.queryParams("event-start-date") != null){
			try {
				String dateString=(request.queryParams("event-start-date"));
				DateFormat formato= new SimpleDateFormat("MM/dd/yyyy");
				Date date= (Date) formato.parse(dateString);
				Calendar dateCal = Calendar.getInstance();
				dateCal.setTime(date);
				evento.setHoraComienzo(dateCal);
        
			} catch(ParseException e) {
				System.out.println("Exception:   "+e);
			}
        }
        
        if(request.queryParams("event-end-date") != null){
        	try {
	        	String dateString=(request.queryParams("event-end-date"));
	        	DateFormat formato= new SimpleDateFormat("MM/dd/yyyy");
				Date date= (Date) formato.parse(dateString);
				Calendar dateCal = Calendar.getInstance();
				dateCal.setTime(date);
				evento.setHoraTermino(dateCal);
        	} catch(ParseException e) {
        		System.out.println("Exception:   "+e);
			}
        }
        
        if(request.queryParams("estilo") != null){
        	
        	 String estiloRecibido= (request.queryParams("estilo"));
        	 switch(estiloRecibido) {
             case "ELEGANTE":
             	evento.setEstilo(Estilo.ELEGANTE);
             	break;
 	        case "ELEGANTSPORT":
 	        	evento.setEstilo(Estilo.ELEGANTSPORT);
 	        	break;
 	       case "DEPORTIVO":
 	    		evento.setEstilo(Estilo.DEPORTIVO);
 	    	break;
 			case "ENTRECASA":
 				evento.setEstilo(Estilo.ENTRECASA);
 				break;
 			case "NAVIDENIO":
 				evento.setEstilo(Estilo.NAVIDENIO);
 				break;
 			case "NORMAL":
 				evento.setEstilo(Estilo.NORMAL);
 				break;
 			case "PLAYERO":
 				evento.setEstilo(Estilo.PLAYERO);
 				break;
 		
        	 }
		
        }
    	
    }
    public Response guardarEvento(Request request, Response response) {
    	Evento evento= new Evento();
   
        RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
        
        RepositorioUbicacion repoUbicacion = FactoryRepositorioUbicacion.get();
        Ubicacion ubicacion= repoUbicacion.buscarTodos().get(0);
        evento.setUbicacion(ubicacion);
        asignarAtributosA(evento, request);   
			
		usuario.agregarEvento(evento);	
		repo.agregar(evento);
			
        response.redirect("/events");
		return response;
		
	 }


}
