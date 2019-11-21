package ar.utn.dds.controllers;
//import entities.Rol;

import ar.utn.dds.modelo.Atuendo;
import ar.utn.dds.modelo.Guardaropa;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.RepositorioOutfit;
import ar.utn.dds.repositories.factories.FactoryRepositorioAtuendo;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import repositories.RepositorioRol;
//import repositories.factories.FactoryRepositorioRol;

public class OutfitController {
    private RepositorioOutfit repo;

    public OutfitController(){
        this.repo = FactoryRepositorioAtuendo.get();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Atuendo> atuendos = this.repo.buscarTodos();
        parametros.put("atuendos", atuendos);
        return  new ModelAndView(parametros, "outfit.hbs");
    }

    public ModelAndView mostrarImagenDePrenda(Request request, Response response){
        int id = new Integer(request.params("id"));
        Atuendo atuendoBuscado = this.repo.buscar(id);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("imgPrendas", atuendoBuscado.getPrendas().get(0).getFotografo().imagenes().get(0));
        ModelAndView vista = new ModelAndView(parametros, "outfit.hbs");
        return vista;
    }

    public ModelAndView mostrarPrendas(Request request, Response response){
        int id = new Integer(request.params("id"));
        Atuendo atuendoBuscado = this.repo.buscar(id);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("prendas", atuendoBuscado.getPrendas());
        ModelAndView vista = new ModelAndView(parametros, "outfit.hbs");
        return vista;
    }


    public ModelAndView mostrar(Request request, Response response){
        int id = new Integer(request.params("id"));
        Atuendo atuendoBuscado = this.repo.buscar(id);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("atuendo", atuendoBuscado);
        ModelAndView vista = new ModelAndView(parametros, "outfit.hbs");
        return vista;
    }
}
