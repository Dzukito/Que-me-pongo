package ar.utn.dds.controllers;

import ar.utn.dds.modelo.*;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    
//	public Response crearEvento(Request request, Response response) {
//
//		Evento evento = new Evento();
//		Usuario usuario = LoginController.getUsuario(request);
//		if(request.queryParams("nombreGuardaropa") != null){
//			guardaropa.setNombre(request.queryParams("nombreGuardaropa"));
//			guardaropa.setDescripcion(request.queryParams("descripcionGuardaropa"));
//			guardaropa.agregarUsuario(usuario);
//			usuario.agregarRopero(guardaropa);
//
//			repo.agregar(guardaropa);
//        }
//
//		response.redirect("/guardaropas");
//		return response;
//
//	 }

//	 public ModelAndView mostrarPrendas(Request request, Response response) {
//	        Map<String, Object> parametros = new HashMap<>();
//	        Guardaropa guardaropa = this.repo.buscar(new Long (request.params(":id")));
//	        if(guardaropa ==null){
//	        	parametros.put("guardaropa", guardaropa);
//	        }else {
//		        List<Prenda> prendas = guardaropa.getPrendas();
////		        List<Prenda> prendasTorso= prendas.stream().filter(prenda -> prenda.getCategoria().compareTo(Categoria.TORSO.getCategoria())>=0).collect(Collectors.toList());
////		        List<Prenda> prendasParteInferior= prendas.stream().filter(prenda -> prenda.getCategoria().compareTo(Categoria.PARTEINFERIOR.getCategoria())>=0).collect(Collectors.toList());
//		        List<Prenda> prendasTorso = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.TORSO.getCategoria())==0).collect(Collectors.toList());
//		        List<Prenda> prendasParteInferior = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.PARTEINFERIOR.getCategoria())==0).collect(Collectors.toList());
//		        List<Prenda> prendasCalzado = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CALZADO.getCategoria())==0).collect(Collectors.toList());
//		        List<Prenda> prendasAccesorio = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.ACCESORIOS.getCategoria())==0).collect(Collectors.toList());
//		        List<Prenda> prendasManos = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.MANOS.getCategoria())==0).collect(Collectors.toList());
//		        List<Prenda> prendasCabeza = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CABEZA.getCategoria())==0).collect(Collectors.toList());
//		        List<Prenda> prendasCuello = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CUELLO.getCategoria())==0).collect(Collectors.toList());
//		        parametros.put("login", LoginController.isUsuarioLogin(request));
//		        parametros.put("guardaropa", guardaropa.getNombre());
//		        parametros.put("guardaropaDescripcion", guardaropa.getDescripcion());
//		        parametros.put("usuarios", guardaropa.getUsuarios());
//		        parametros.put("prendasTorso", prendasTorso);
//		        parametros.put("prendasParteInferior", prendasParteInferior);
//		        parametros.put("prendasCalzado", prendasCalzado);
//		        parametros.put("prendasAccesorio", prendasAccesorio);
//		        parametros.put("prendasManos", prendasManos);
//		        parametros.put("prendasCabeza", prendasCabeza);
//		        parametros.put("prendasCuello", prendasCuello);
//	        }
//
//	        return  new ModelAndView(parametros, "miGuardaropa.hbs");
//
//	    }

}
