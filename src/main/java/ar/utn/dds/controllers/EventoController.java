package ar.utn.dds.controllers;
import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.clases.Ubicacion;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.repositories.RepositorioEvento;
import ar.utn.dds.repositories.RepositorioUbicacion;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioEvento;
import ar.utn.dds.repositories.factories.FactoryRepositorioUbicacion;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
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


    public EventoController(){
        this.repo = FactoryRepositorioEvento.get();
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
//        Evento evento = this.repo.buscar(new Integer(request.params("id")));
//        RepositorioEvento repoEvento = FactoryRepositorioEvento.get();
//        Map<String, Object> parametros = new HashMap<>();
//        parametros.put("evento", evento);
        
//        return new ModelAndView(parametros, "mostrarEvento.hbs");
		Map<String, Object> parametros = new HashMap<>();
        Long idEvento = new Long (request.params(":id"));
        Evento evento = repo.buscar(idEvento); 
//        List<AtuendoCalifVista> atuendosElegantes = evento.agregarSugerencias();
        RepositorioUsuario repoUsuario=FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));

        if(usuario.getEventos().contains(evento)) {
        	parametros.put("evento", evento);
        	parametros.put("login", LoginController.isUsuarioLogin(request));
        	parametros.put("atuendo", evento.getAtuendo());
        	parametros.put("atuendos", evento.sugerencias());
        }

        parametros.put("login", LoginController.isUsuarioLogin(request));
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
