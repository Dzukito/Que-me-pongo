package ar.utn.dds.controllers;

import ar.utn.dds.modelo.Categoria;
import ar.utn.dds.modelo.Color;
import ar.utn.dds.modelo.Estilo;
import ar.utn.dds.modelo.Guardaropa;
import ar.utn.dds.modelo.Material;
import ar.utn.dds.modelo.Prenda;
import ar.utn.dds.modelo.Usuario;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.RepositorioTipoPrenda;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioTipoPrenda;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class GuardaropaController {
    private RepositorioGuardaropa repo;
    

    public GuardaropaController(){
        this.repo = FactoryRepositorioGuardaropa.get();
        
    }
    
    public ModelAndView mostrarTodos(Request request, Response response) {
    	
        Map<String, Object> parametros = new HashMap<>();
 //       Usuario usuario1 = LoginController.getUsuario(request);
        RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
       
        List<Guardaropa> guardaropas = usuario.getRoperos();
        parametros.put("guardaropas", guardaropas);
        parametros.put("login", LoginController.isUsuarioLogin(request));
        
        return  new ModelAndView(parametros, "misGuardaropas.hbs");
        
    }
    
	public Response crearGuardaropa(Request request, Response response) {
	    	
		Guardaropa guardaropa = new Guardaropa();
//		Usuario usuario = request.session().attribute("nombreDeUsuario");
        
//		Usuario usuario1 = LoginController.getUsuario(request);
        RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
        
		if(request.queryParams("nombreGuardaropa") != null){
			guardaropa.setNombre(request.queryParams("nombreGuardaropa"));
			guardaropa.setDescripcion(request.queryParams("descripcionGuardaropa"));
			guardaropa.agregarUsuario(usuario);
			usuario.agregarRopero(guardaropa);
			
//			usuario.modificar(usuario);
			repo.agregar(guardaropa);
			
        }
	
		List<Guardaropa> guarda=repo.buscarTodos();
		
        PerThreadEntityManagers.closeEntityManager();
        System.out.println("************** cierro  *************");
		
        
        response.redirect("/guardaropas");
		return response;
		
	 }

	 public ModelAndView mostrarPrendas(Request request, Response response) {	    	
	        Map<String, Object> parametros = new HashMap<>();
	        List<Estilo> estilos= Arrays.asList(Estilo.values());
	        Guardaropa guardaropa = this.repo.buscar(new Long (request.params(":id"))); 
//	        Usuario usuari1 = LoginController.getUsuario(request);     
	        RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
	        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
	        if (guardaropa!=null && !guardaropa.esDeIdUsuario(usuario)) {  	
	        	guardaropa=null;
	        }
/*	        List<Guardaropa> guardaropas = usuario.getRoperos();
	        for(int i=0; i<guardaropas.size();i++) {
	        	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	        	if(guardaropas.get(i).getId_guardaropa()==(new Long (request.params(":id")))) {
	        		guardaropa=guardaropas.get(i);
	        	}
	        }
*/
	        if(guardaropa ==null){

	        	parametros.put("guardaropa", guardaropa);
	        	parametros.put("login", LoginController.isUsuarioLogin(request));
	        	
		    }
	        
	        else { 	
	        	System.out.println("///////////////////////----------------------------------/////////////////////////");
	        	
	        	
		        List<Prenda> prendas = guardaropa.getPrendas();
       			
		        Map<String, Object> fotoPrendas  = new HashMap<>();
	        
		        for(int i =0; i<prendas.size();i++) {
		        	if(prendas.get(i).getFotografo().getImagenes().stream().findFirst().isPresent()) {
		        		fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),prendas.get(i).getFotografo().getImagenes().stream().findFirst().get());
		        	}
		        	else {
		       
		        		if(prendas.get(i).getCategoria().equalsIgnoreCase(Categoria.TORSO.getCategoria())) {
		        			fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),"remeraIcono.png");	
		        		}
		        		
		        		if(prendas.get(i).getCategoria().equalsIgnoreCase(Categoria.PARTEINFERIOR.getCategoria())) {
		        			fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),"pantalonIcono.png");	
		        		}
		        		
		        		if(prendas.get(i).getCategoria().equalsIgnoreCase(Categoria.CALZADO.getCategoria())) {
		        			fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),"zapatillaIcono.png");	
		        		}
		        		
		        		if(prendas.get(i).getCategoria().equalsIgnoreCase(Categoria.ACCESORIOS.getCategoria())) {
		        			fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),"relojIcono.png");	
		        		}
		        		
		        		if(prendas.get(i).getCategoria().equalsIgnoreCase(Categoria.CABEZA.getCategoria())) {
		        			fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),"gorroIcono.png");	
		        		}
		        		
		        		if(prendas.get(i).getCategoria().equalsIgnoreCase(Categoria.CUELLO.getCategoria())) {
		        			fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),"bufandaIcono.png");	
		        		}
		        		
		        		if(prendas.get(i).getCategoria().equalsIgnoreCase(Categoria.MANOS.getCategoria())) {
		        			fotoPrendas.put(String.valueOf(prendas.get(i).getId_prenda()),"guantesIcono.png");	
		        		}
		        	}
		        }
		        
		        
		        List<Prenda> prendasTorso = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.TORSO)).collect(Collectors.toList());
		        List<Prenda> prendasParteInferior = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.PARTEINFERIOR)).collect(Collectors.toList());
		        List<Prenda> prendasCalzado = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.CALZADO)).collect(Collectors.toList());
		        List<Prenda> prendasAccesorio = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.ACCESORIOS)).collect(Collectors.toList());
		        List<Prenda> prendasManos = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.MANOS)).collect(Collectors.toList());
		        List<Prenda> prendasCabeza = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.CABEZA)).collect(Collectors.toList());
		        List<Prenda> prendasCuello = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.CUELLO)).collect(Collectors.toList());
		        

		        parametros.put("login", LoginController.isUsuarioLogin(request));
		        parametros.put("guardaropaId", request.params(":id"));
		        parametros.put("guardaropa", guardaropa.getNombre());
		        parametros.put("guardaropaDescripcion", guardaropa.getDescripcion());
		        parametros.put("usuarios", guardaropa.getUsuarios());
		        parametros.put("prendasTorso", prendasTorso);
		        parametros.put("fotoPrendas", fotoPrendas);
		        parametros.put("prendasParteInferior", prendasParteInferior);
		        parametros.put("prendasCalzado", prendasCalzado);
		        parametros.put("prendasAccesorio", prendasAccesorio);
		        parametros.put("prendasManos", prendasManos);
		        parametros.put("prendasCabeza", prendasCabeza);
		        parametros.put("prendasCuello", prendasCuello);
		        parametros.put("estilos", estilos);
		        
	        } 
	        
	        return  new ModelAndView(parametros, "miGuardaropa.hbs");
	 
	 }

}
