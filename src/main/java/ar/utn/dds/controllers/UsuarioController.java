package ar.utn.dds.controllers;
import ar.utn.dds.modelo.Guardaropa;
import ar.utn.dds.modelo.Membrecia;
import ar.utn.dds.modelo.Pronostico;
import ar.utn.dds.modelo.Usuario;
import ar.utn.dds.repositories.RepositorioMembrecia;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioMembrecia;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {
    private RepositorioUsuario repo;

    public UsuarioController(){
        this.repo = FactoryRepositorioUsuario.get();
    }

    public ModelAndView crear(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        RepositorioMembrecia repoMembrecia = FactoryRepositorioMembrecia.get();
        parametros.put("membrecias", repoMembrecia.buscarTodos());
        return new ModelAndView(parametros, "registro.hbs");
    }
/*    
    public ModelAndView mostrar(Request request, Response response){
        Usuario usuario = this.repo.buscar(new Integer(request.params("id")));
        RepositorioMembrecia repoRol = FactoryRepositorioMembrecia.get();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", usuario);
        parametros.put("roles", repoRol.buscarTodos());
        return new ModelAndView(parametros, "usuario.hbs");
    }

    public Response modificar(Request request, Response response){
        Usuario usuario = this.repo.buscar(new Integer(request.params("id")));
        asignarAtributosA(usuario, request);
        this.repo.modificar(usuario);
        response.redirect("/usuarios");
        return response;
    }
*/
    private void asignarAtributosA(Usuario usuario, Request request){

    	if(request.queryParams("nombreDeUsuario") != null){
            usuario.setUserName(request.queryParams("nombreDeUsuario"));
        }
        
        if(request.queryParams("password") != null){
            String password = new String(request.queryParams("password"));
            usuario.setPassword(password);
        }
        
        if(request.queryParams("sexo") != null){
            usuario.setSexo(request.queryParams("sexo"));
        }
        
        if(request.queryParams("membrecia") != null){
        	 RepositorioMembrecia repoMembrecia = FactoryRepositorioMembrecia.get();
        	 Membrecia unaMembrecia = repoMembrecia.buscar(new Long(request.queryParams("membrecia")));
            usuario.setMembrecia(unaMembrecia);
        }
        
        if(request.queryParams("email") != null){
            usuario.setMail(request.queryParams("email"));
        }
        
        if(request.queryParams("telefono") != null){
            int telefono = new Integer(request.queryParams("telefono"));
            usuario.setTelefono(telefono);
        }
    }

    public Response guardar(Request request, Response response){
        Usuario usuario = new Usuario();
        asignarAtributosA(usuario, request);
        this.repo.agregar(usuario);
        response.redirect("/home");//TODO cambiar por login
        return response;
    }
    
    
    public ModelAndView crearLogin(Request request, Response response){ 
    	return  new ModelAndView(new HashMap<>(), "login.hbs");
    }
    
    public Response guardarLogin(Request request, Response response){
    	Map<String, Object> parametros = new HashMap<>();
    	List<Usuario> usuarios = this.repo.buscarTodos();
    	System.out.println("test 0");
    	System.out.println(usuarios.size());
    	boolean usuarioOk=false;
    	for(int i = 0;i< usuarios.size() || !usuarioOk;i=i+1) {
    		System.out.println("test 0.1");
    		System.out.println(i);
        	if((usuarios.get(i).getUserName().compareTo(request.queryParams("nombreDeUsuario")))==0) {
        		System.out.println("entro");
        		usuarioOk=true;
        		request.session(true);
        		request.session().attribute("nombreDeUsuario",usuarios.get(i));
        		
        	} 
        	System.out.println("test 1");
        }
    	       
    	response.redirect("/home");//TODO cambiar por login
        return response;
    }
/*
    public Response eliminar(Request request, Response response){
        Usuario usuario = this.repo.buscar(new Integer(request.params("id")));
        this.repo.eliminar(usuario);
        return response;
    }
*/
}
