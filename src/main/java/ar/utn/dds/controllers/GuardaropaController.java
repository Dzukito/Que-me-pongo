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
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioTipoPrenda;
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
        Usuario usuario = LoginController.getUsuario(request);
        List<Guardaropa> guardaropas = usuario.getRoperos();
        parametros.put("guardaropas", guardaropas);
        parametros.put("login", LoginController.isUsuarioLogin(request));
        return  new ModelAndView(parametros, "misGuardaropas.hbs");
    	
    }
    
	public Response crearGuardaropa(Request request, Response response) {
	    	
		Guardaropa guardaropa = new Guardaropa();
//		Usuario usuario = request.session().attribute("nombreDeUsuario");
		Usuario usuario = LoginController.getUsuario(request);
		if(request.queryParams("nombreGuardaropa") != null){
			guardaropa.setNombre(request.queryParams("nombreGuardaropa"));
			guardaropa.setDescripcion(request.queryParams("descripcionGuardaropa"));
			guardaropa.agregarUsuario(usuario);
			usuario.agregarRopero(guardaropa);
           
			repo.agregar(guardaropa);
        }
		
		response.redirect("/guardaropas");
		return response;
	    
	 }

	 public ModelAndView mostrarPrendas(Request request, Response response) {	    	
	        Map<String, Object> parametros = new HashMap<>();
	        Guardaropa guardaropa = this.repo.buscar(new Long (request.params(":id")));
	        if(guardaropa ==null){
	        	parametros.put("guardaropa", guardaropa);
	        }else {
	        	
	        	System.out.println("///////////////////////----------------------------------/////////////////////////");
	        	
	        	
		        List<Prenda> prendas = guardaropa.getPrendas();
//		        List<Prenda> prendasTorso= prendas.stream().filter(prenda -> prenda.getCategoria().compareTo(Categoria.TORSO.getCategoria())>=0).collect(Collectors.toList());
//		        List<Prenda> prendasParteInferior= prendas.stream().filter(prenda -> prenda.getCategoria().compareTo(Categoria.PARTEINFERIOR.getCategoria())>=0).collect(Collectors.toList());
		        List<Prenda> prendasTorso = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.TORSO)).collect(Collectors.toList());
		        List<Prenda> prendasParteInferior = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.PARTEINFERIOR)).collect(Collectors.toList());
		        List<Prenda> prendasCalzado = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.CALZADO)).collect(Collectors.toList());
		        List<Prenda> prendasAccesorio = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.ACCESORIOS)).collect(Collectors.toList());
		        List<Prenda> prendasManos = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.MANOS)).collect(Collectors.toList());
		        List<Prenda> prendasCabeza = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.CABEZA)).collect(Collectors.toList());
		        List<Prenda> prendasCuello = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().equals(Categoria.CUELLO)).collect(Collectors.toList());
		        parametros.put("login", LoginController.isUsuarioLogin(request));
		        parametros.put("guardaropa", guardaropa.getNombre());
		        parametros.put("guardaropaDescripcion", guardaropa.getDescripcion());
		        parametros.put("usuarios", guardaropa.getUsuarios());
		        parametros.put("prendasTorso", prendasTorso);
		        parametros.put("prendasParteInferior", prendasParteInferior);
		        parametros.put("prendasCalzado", prendasCalzado);
		        parametros.put("prendasAccesorio", prendasAccesorio);
		        parametros.put("prendasManos", prendasManos);
		        parametros.put("prendasCabeza", prendasCabeza);
		        parametros.put("prendasCuello", prendasCuello);
		     
		        
	        }    
	        
	        return  new ModelAndView(parametros, "miGuardaropa.hbs");
	    	
	    }

}
