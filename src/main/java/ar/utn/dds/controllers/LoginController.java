package ar.utn.dds.controllers;

import java.util.HashMap;
import java.util.List;
import ar.utn.dds.modelo.Usuario;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	
	private RepositorioUsuario repo;

    public LoginController(){
        this.repo = FactoryRepositorioUsuario.get();
    }
	
    
    public ModelAndView crearLogin(Request request, Response response){ 
    	return  new ModelAndView(new HashMap<>(), "login.hbs");
    }
    
    public Response guardarLogin(Request request, Response response){
    	List<Usuario> usuarios = this.repo.buscarTodos();
    	
    	boolean usuarioOk=false;
    	for(int i = 0;i< usuarios.size() || !usuarioOk;i=i+1) {
    		
        	if((usuarios.get(i).getUserName().compareTo(request.queryParams("nombreDeUsuario")))==0 &&
        		(usuarios.get(i).getPassword().compareTo(request.queryParams("password")))==0) {
//falta manejar errores        		
        		usuarioOk=true;
        		request.session(true);
        		request.session().attribute("nombreDeUsuario",usuarios.get(i));
        		
        	} 
        	
        }
    	       
    	response.redirect("/home");
        return response;
    }

}
