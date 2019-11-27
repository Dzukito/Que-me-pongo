package ar.utn.dds.controllers;

import ar.utn.dds.modelo.Guardaropa;
import ar.utn.dds.modelo.Usuario;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class GuardaropaController {
    private RepositorioGuardaropa repo;

    public GuardaropaController(){
        this.repo = FactoryRepositorioGuardaropa.get();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
    	
        Map<String, Object> parametros = new HashMap<>();
        Usuario usuario = LoginController.getUsuario(request);
        List<Guardaropa> guardaropas = usuario.getRoperos();
        parametros.put("guardaropas", guardaropas);
        parametros.put("login", LoginController.isUsuarioLogin(request));
        return  new ModelAndView(parametros, "misGuardaropas.hbs");
    	
    }
    
	public Response crearGuardaropa(Request request, Response response) {
	    	
		Guardaropa guardaropa = new Guardaropa();
//		Usuario usuario = request.session().attribute("nombreDeUsuario");
		Usuario usuario = LoginController.getUsuario(request);
		if(request.queryParams("nombreGuardaropa") != null){
			guardaropa.setNombre(request.queryParams("nombreGuardaropa"));
			guardaropa.agregarUsuario(usuario);
			usuario.agregarRopero(guardaropa);
           
			this.repo.agregar(guardaropa);
        }
		
		response.redirect("/guardaropas");
		return response;
	    
	 }

}
