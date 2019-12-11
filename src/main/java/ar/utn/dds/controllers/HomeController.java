package ar.utn.dds.controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

	public HomeController(){}

	public ModelAndView mostrar(Request request, Response response) {
		Map<String, Boolean> parametros = new HashMap<>();
		parametros.put("login", LoginController.isUsuarioLogin(request));
        return  new ModelAndView(parametros, "home.hbs");
    }

	
	
}
