package ar.utn.dds.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ar.utn.dds.controllers.LoginController;
import ar.utn.dds.modelo.Estilo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

	public HomeController(){}

	public ModelAndView mostrar(Request request, Response response) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("login", LoginController.isUsuarioLogin(request));
		
        return  new ModelAndView(parametros, "home.hbs");
    }

	
	
}
