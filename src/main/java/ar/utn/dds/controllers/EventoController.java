package ar.utn.dds.controllers;

import ar.utn.dds.repositories.RepositorioEvento;
import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.RepositorioOutfit;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioEvento;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class EventoController {
    private RepositorioGuardaropa repo;


    public EventoController(){
        this.repo = FactoryRepositorioGuardaropa.get();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
//lo cambio porque no se esta yendo a buscar el usuario a la base
//      Usuario usuario = LoginController.getUsuario(request);
        RepositorioUsuario repoUsuario=FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
        List<Evento> eventos = usuario.getEventos();
        parametros.put("eventos", eventos);
        parametros.put("login", LoginController.isUsuarioLogin(request));
        return  new ModelAndView(parametros, "eventos.hbs");
    }
    

    public ModelAndView mostrar(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        Long idEvento = new Long (request.params(":id"));
        
        RepositorioEvento repoEvento=FactoryRepositorioEvento.get();
        Evento evento = repoEvento.buscar(idEvento); 

        parametros.put("evento", evento);
        parametros.put("atuendos", evento.sugerencias());
        parametros.put("login", LoginController.isUsuarioLogin(request));
        return  new ModelAndView(parametros, "eventos.hbs");
    }
    

//	public Response crearEvento(Request request, Response response) throws ParseException {
//		Evento evento = new Evento();
//		Usuario usuario = LoginController.getUsuario(request);
//		if(request.queryParams("horaComienzo") != null) {
//            String fecha = request.queryParams("horaComienzo");
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm yyyy", Locale.ENGLISH);
//
////		    evento.setHoraComienzo(cal.setTime(sdf.parse(fecha)));
//        }
//        if(request.queryParams("horaFin") != null) {
//            String fecha = request.queryParams("horaFin");
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm yyyy", Locale.ENGLISH);
////          evento.setHoraTermino(cal.setTime(sdf.parse(fecha)));
//        }
//        if(request.queryParams("estilo") != null) {
//            String fecha = request.queryParams("horaFin");
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm yyyy", Locale.ENGLISH);
////          evento.setHoraTermino(cal.setTime(sdf.parse(fecha)));
//        }
//
////		guardaropa.setDescripcion(request.queryParams("descripcionGuardaropa"));
////		repo.agregar(guardaropa);
//
//        usuario.agregarEvento(evento);
//		response.redirect("/events");
//		return response;
//
//	 }


}
