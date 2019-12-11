package ar.utn.dds.controllers;

import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
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
        Usuario usuario = LoginController.getUsuario(request);
        List<Evento> eventos = usuario.getEventos();
        parametros.put("eventos", eventos);
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
