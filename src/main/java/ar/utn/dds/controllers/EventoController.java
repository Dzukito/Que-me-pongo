package ar.utn.dds.controllers;
import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.clases.Membrecia;
import ar.utn.dds.modelo.clases.Ubicacion;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.repositories.RepositorioEvento;
import ar.utn.dds.repositories.RepositorioOutfit;
import ar.utn.dds.repositories.RepositorioUbicacion;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioEvento;
import ar.utn.dds.repositories.factories.FactoryRepositorioUbicacion;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioAtuendo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventoController {
    private RepositorioEvento repo;

    public EventoController(){
        this.repo = FactoryRepositorioEvento.get();
    }

    public ModelAndView crear(Request request, Response response){
    	
        Map<String, Object> parametros = new HashMap<>();
        RepositorioEvento repoEvento = FactoryRepositorioEvento.get();
        List<Estilo> estilos= Arrays.asList(Estilo.values());
        RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
        parametros.put("estilos", estilos);
        
        //FALTA AGARRAR LA SESION
        
        return new ModelAndView(parametros, "registro.hbs");
    }

    private void asignarAtributosA(Evento evento, Request request){

        /*if(request.queryParams("nombre") != null){
            usuario.setNombre(request.queryParams("nombre"));
            //PERSISTIR COLUMNA
        }*/

        if(request.queryParams("horaComienzo") != null){
        	
        }

    	if(request.queryParams("horaTermino") != null){
   
        }
        
        if(request.queryParams("ubicacion") != null){
        	 RepositorioUbicacion repoUbicacion = FactoryRepositorioUbicacion.get();
        	 Ubicacion ubicacion = repoUbicacion.buscar(new Long(request.queryParams("ubicacion")));
            evento.setUbicacion(ubicacion);
            
        }
        if(request.queryParams("atuendo") != null){
       	 RepositorioOutfit repoAtuendo = FactoryRepositorioAtuendo.get();
       	 Atuendo atuendo = repoAtuendo.buscar(new Long(request.queryParams("atuendo")));
         evento.setAtuendo(atuendo);
           
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

    public Response guardar(Request request, Response response){
        Evento evento = new Evento();
        asignarAtributosA(evento, request);
        this.repo.agregar(evento);
        response.redirect("/home");
        return response;
    }
    
}
