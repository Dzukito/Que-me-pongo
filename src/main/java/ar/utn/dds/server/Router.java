package ar.utn.dds.server;

import ar.utn.dds.controllers.GuardaropaController;
import ar.utn.dds.controllers.UsuarioController;
import ar.utn.dds.controllers.HomeController;
import ar.utn.dds.models.GuardaropaModel;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import ar.utn.dds.DAO.DAOMySQL;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import ar.utn.dds.spark.utils.BooleanHelper;
import ar.utn.dds.spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
    	HomeController homeController = new HomeController();
    	UsuarioController usuarioController = new UsuarioController();
        GuardaropaController guardaropaController = new GuardaropaController();
        
        
        Spark.get("/home", homeController::mostrar, Router.engine);
        
        Spark.get("/guardaropas", guardaropaController::mostrarTodos, Router.engine);

        Spark.get("/usuario", usuarioController::crear, Router.engine);
        
        Spark.post("/usuario", usuarioController::guardar);

//        Spark.post("/usuario/:id", usuarioController::modificar);

  //      Spark.delete("/usuario/:id", usuarioController::eliminar);

        //Spark.after((req, res) -> {
        //    PerThreadEntityManagers.closeEntityManager();
        //});
    }
}
