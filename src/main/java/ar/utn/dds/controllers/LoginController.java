package ar.utn.dds.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	
	static private RepositorioUsuario repo= FactoryRepositorioUsuario.get();
	public static boolean usuarioLogin=false;

    public LoginController(){
//        this.repo = FactoryRepositorioUsuario.get();
    }
	
    
    public ModelAndView crearLogin(Request request, Response response){ 
    	Map<String, Object> parametros = new HashMap<>();
    	request.session(false);
    	request.session().removeAttribute("nombreDeUsuario"); 
    	parametros.put("intentoLogin", usuarioLogin);
    	usuarioLogin=false;
    	return  new ModelAndView(parametros, "login.hbs");
    }
    
    public Response guardarLogin(Request request, Response response){
    	List<Usuario> usuarios = repo.buscarTodos();
    	usuarioLogin=false;
    	request.session(false);
    	request.session().removeAttribute("nombreDeUsuario"); 
    	for(int i = 0;i< usuarios.size() && !isUsuarioLogin(request);i=i+1) {
    		
        	if((usuarios.get(i).getUserName().compareTo(request.queryParams("nombreDeUsuario")))==0 &&
        		(usuarios.get(i).getPassword().compareTo(request.queryParams("password")))==0) {
        		
        		request.session(true);
        		request.session().attribute("nombreDeUsuario",usuarios.get(i).getId_usuario());
        		
        	} 
        	
        }
    	if  (isUsuarioLogin(request)) {   
    		response.redirect("/home");
    	}else {
    		usuarioLogin=true;
    		response.redirect("/login");	
    	}
    	PerThreadEntityManagers.closeEntityManager();
        System.out.println("************** cierro  *************");
        return response;
    }


	public static boolean isUsuarioLogin(Request request) {
		return request.session().attribute("nombreDeUsuario") != null;
	}

	public static Usuario getUsuario(Request request) {
		return repo.buscar(request.session().attribute("nombreDeUsuario"));
		
	}
/*
	public static void setUsuarioLogin(boolean usuarioLogin) {
		LoginController.usuarioLogin = usuarioLogin;
	}
*/
}
