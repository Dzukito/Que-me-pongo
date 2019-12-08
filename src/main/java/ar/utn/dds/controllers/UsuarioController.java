package ar.utn.dds.controllers;
import ar.utn.dds.modelo.Membrecia;
import ar.utn.dds.modelo.Usuario;
import ar.utn.dds.repositories.RepositorioMembrecia;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioMembrecia;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
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
        response.redirect("/login");
        return response;
    }
    
}
