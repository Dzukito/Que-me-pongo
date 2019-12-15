package ar.utn.dds.server;

import ar.utn.dds.controllers.*;
import ar.utn.dds.models.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import ar.utn.dds.repositories.DAO.DAOMySQL;
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
    	LoginController loginController = new LoginController();
    	HomeController homeController = new HomeController();
    	UsuarioController usuarioController = new UsuarioController();
        GuardaropaController guardaropaController = new GuardaropaController();
        PrendaController prendaController = new PrendaController();
        EventoController eventoController = new EventoController();
        OutfitController outfitController = new OutfitController();
        


        //Home
        Spark.get("/home", homeController::mostrar, Router.engine);
        //Guardaropas
        Spark.get("/guardaropas", guardaropaController::mostrarTodos, Router.engine);
        Spark.post("/guardaropas", guardaropaController::crearGuardaropa);
        Spark.get("/guardaropa/:id", guardaropaController::mostrarPrendas, Router.engine);
        //Eventos
        Spark.get("/events", eventoController::mostrarTodos, Router.engine);
        Spark.get("/events/:id", eventoController::mostrar, Router.engine);
//        Spark.get("/addEvent", eventoController::crearEvento);
//        Spark.post("/addEvent", eventoController::guardar);
        //Registro
        Spark.post("/usuario", usuarioController::guardar);
        Spark.get("/usuario", usuarioController::crear, Router.engine);
        //Login
        Spark.get("/login", loginController::crearLogin, Router.engine);
        Spark.post("/login", loginController::guardarLogin);
        //Prenda
        Spark.get("/addPrenda/:id", prendaController::crear, Router.engine);
        Spark.post("/addPrenda/:id", prendaController::guardar);
        
        //Atuendo
        Spark.get("/outfit/:id", outfitController::mostrarOutfitPorGuardaropa, Router.engine);
        Spark.post("/outfit/:idGuardaropa/:idAtuendo", outfitController::calificarOutfit);
        Spark.get("/addOutfit/:idGuardaropa/:nombreEstilo", outfitController::crearOutfitPorGuardaropa, Router.engine);
        Spark.post("/addOutfit/:idGuardaropa", outfitController::guardarOutfit);
        
        Spark.get("/sugerirOutfit/:idGuardaropa", outfitController::crearSugerenciaPorGuardaropa, Router.engine);
        Spark.post("/sugerirOutfit/:idGuardaropa", outfitController::sugerirOutfit);
        
//        Spark.post("/addPrenda", prendaController::guardar);
        
        //Imagen
        Spark.get("/Imagen", prendaController::crearImagen, Router.engine);
        Spark.post("/Imagen", prendaController::guardarFoto);


  //      Spark.delete("/usuario/:id", usuarioController::eliminar);

        
        Spark.before((req, res) -> {
            if (!LoginController.isUsuarioLogin(req) && !req.uri().equals("/home") && !req.uri().equals("/login")&& !req.uri().equals("/usuario")) {
            	res.redirect("/login");
            }
        });
        Spark.after((req, res) -> {
        	if(!req.uri().equals("/home") && !req.uri().equals("/login")) {
        		PerThreadEntityManagers.closeEntityManager();
        	}
        });
    }
}
