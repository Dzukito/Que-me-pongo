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

public class GuardaropaController {
    private RepositorioGuardaropa repo;

    public GuardaropaController(){
        this.repo = FactoryRepositorioGuardaropa.get();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
    	
        Map<String, Object> parametros = new HashMap<>();
        Usuario usuario = request.session().attribute("nombreDeUsuario");
        List<Guardaropa> guardaropas = usuario.getRoperos();
        parametros.put("guardaropas", guardaropas);
        return  new ModelAndView(parametros, "misGuardaropas.hbs");
    	
    }

}
