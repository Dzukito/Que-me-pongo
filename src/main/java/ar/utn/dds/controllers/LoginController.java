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
	
	static private RepositorioUsuario repo = FactoryRepositorioUsuario.get();
//	public static boolean usuarioLogin=false;

    public LoginController(){
        //this.repo = FactoryRepositorioUsuario.get();
    }
	
    
    public ModelAndView crearLogin(Request request, Response response){ 
//    	setUsuarioLogin(false);
    	request.session().removeAttribute("nombreDeUsuario"); 
    	return  new ModelAndView(new HashMap<>(), "login.hbs");
    }
    
    public Response guardarLogin(Request request, Response response){
    	List<Usuario> usuarios = repo.buscarTodos();
    	
    	for(int i = 0;i< usuarios.size() && !isUsuarioLogin(request);i=i+1) {
    		
        	if((usuarios.get(i).getUserName().compareTo(request.queryParams("nombreDeUsuario")))==0 &&
        		(usuarios.get(i).getPassword().compareTo(request.queryParams("password")))==0) {
        		
        		
        		request.session(true);
        		request.session().attribute("nombreDeUsuario",usuarios.get(i));
        		
        	} 
        	
        }
    	if  (isUsuarioLogin(request)) {   
    		response.redirect("/home");
    	}else {
    		response.redirect("/login");	
    	}
        return response;
    }


	public static boolean isUsuarioLogin(Request request) {
		return request.session().attribute("nombreDeUsuario") != null;
	}

	public static Usuario getUsuario(Request request) {
		return repo.buscar(((Usuario)request.session().attribute("nombreDeUsuario")).getId_usuario());
		
	}
/*
	public static void setUsuarioLogin(boolean usuarioLogin) {
		LoginController.usuarioLogin = usuarioLogin;
	}
*/
}
