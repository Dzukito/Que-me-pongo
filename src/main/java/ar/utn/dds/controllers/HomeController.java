package ar.utn.dds.controllers;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

	public HomeController(){}
	public ModelAndView mostrar(Request request, Response response) {
        return  new ModelAndView(new HashMap<>(), "home.hbs");
    }

	
}
