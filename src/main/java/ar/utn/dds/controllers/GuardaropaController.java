package ar.utn.dds.controllers;
//import entities.Rol;
import ar.utn.dds.modelo.Guardaropa;
import ar.utn.dds.repositories.RepositorioGuardaropa;
//import repositories.RepositorioRol;
//import repositories.factories.FactoryRepositorioRol;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
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
        List<Guardaropa> guardaropas = this.repo.buscarTodos();
        parametros.put("guardaropas", guardaropas);
        return  new ModelAndView(parametros, "misGuardaropas.hbs");
    }

}
