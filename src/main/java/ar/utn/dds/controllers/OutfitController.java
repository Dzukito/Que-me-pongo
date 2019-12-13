package ar.utn.dds.controllers;
//import entities.Rol;

import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.modelo.clases.CalificacionAtuendo;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.Color;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.clases.Fotografo;
import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.modelo.ropa.Material;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.ropa.TipoPrenda;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.repositories.RepositorioCalificacion;
import ar.utn.dds.repositories.RepositorioFotografo;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.repositories.RepositorioOutfit;
import ar.utn.dds.repositories.RepositorioPrenda;
import ar.utn.dds.repositories.RepositorioTipoPrenda;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioAtuendo;

import ar.utn.dds.repositories.factories.FactoryRepositorioCalificacion;
import ar.utn.dds.repositories.factories.FactoryRepositorioCalificacion;
import ar.utn.dds.repositories.factories.FactoryRepositorioFotografo;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioPrenda;
import ar.utn.dds.repositories.factories.FactoryRepositorioTipoPrenda;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;

import ar.utn.dds.spark.utils.AtuendoCalifVista;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

//import repositories.RepositorioRol;
//import repositories.factories.FactoryRepositorioRol;

public class OutfitController {
    private RepositorioOutfit repo;

    public OutfitController(){
        this.repo = FactoryRepositorioAtuendo.get();
    }

    public ModelAndView mostrarOutfitPorGuardaropa(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Estilo> estilos= Arrays.asList(Estilo.values());
        RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
        String paramId = request.params(":id");
        Long idGuardaropa = new Long(paramId);
        Guardaropa guardaropa=usuario.getRoperos().stream().filter(guarda->guarda.getId_guardaropa()==idGuardaropa).collect(Collectors.toList()).get(0);
        List<Atuendo> atuendos;
        if(guardaropa==null) {
        	atuendos= new ArrayList<Atuendo>();
        	parametros.put("atuendos", atuendos);
        }else {
        	atuendos = guardaropa.getAtuendosMostrados();
        	List<AtuendoCalifVista> atuendosElegantes=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.ELEGANTE)))).map(a1->this.crearVista(a1, usuario)).collect(Collectors.toList());
        	List<AtuendoCalifVista> atuendosElegantSport=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.ELEGANTSPORT)))).map(a1->this.crearVista(a1, usuario)).collect(Collectors.toList());
        	List<AtuendoCalifVista> atuendosDeportivo=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.DEPORTIVO)))).map(a1->this.crearVista(a1, usuario)).collect(Collectors.toList());
        	List<AtuendoCalifVista> atuendosEntreCasa=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.ENTRECASA)))).map(a1->this.crearVista(a1, usuario)).collect(Collectors.toList());
        	List<AtuendoCalifVista> atuendosNavidenio=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.NAVIDENIO)))).map(a1->this.crearVista(a1, usuario)).collect(Collectors.toList());
        	List<AtuendoCalifVista> atuendosNormal=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.NORMAL)))).map(a1->this.crearVista(a1, usuario)).collect(Collectors.toList());
        	List<AtuendoCalifVista> atuendosPlayero=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.PLAYERO)))).map(a1->this.crearVista(a1, usuario)).collect(Collectors.toList());
        	
        	parametros.put("atuendosElegantes", atuendosElegantes);
        	parametros.put("atuendosElegantSport", atuendosElegantSport);
        	parametros.put("atuendosDeportivo", atuendosDeportivo);
        	parametros.put("atuendosEntreCasa", atuendosEntreCasa);
        	parametros.put("atuendosNavidenio", atuendosNavidenio);
        	parametros.put("atuendosNormal", atuendosNormal);
        	parametros.put("atuendosPlayero", atuendosPlayero);
        }
        
        parametros.put("login", LoginController.isUsuarioLogin(request));
        parametros.put("guardaropaId", idGuardaropa);
        parametros.put("estilos", estilos);
        return  new ModelAndView(parametros, "outfit2.hbs");
        
    }
    
    public AtuendoCalifVista crearVista(Atuendo atuendo,Usuario usuario) {
    	AtuendoCalifVista atuendoVista=new AtuendoCalifVista(atuendo,usuario);
    	return atuendoVista;
    }
    public ModelAndView crearOutfitPorGuardaropa(Request request, Response response) {
    	Map<String, Object> parametros = new HashMap<>();
    	RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
       
        String paramEstilo =request.params(":nombreEstilo");
        Long idGuardaropa = new Long(request.params(":idGuardaropa"));
        Guardaropa guardaropa=usuario.getRoperos().stream().filter(guarda->guarda.getId_guardaropa()==idGuardaropa).collect(Collectors.toList()).get(0);
        List<Atuendo> atuendos;
        if(guardaropa==null) {
        	atuendos= new ArrayList<Atuendo>();
        	parametros.put("atuendos", atuendos);
        }else {
        	List<Prenda> prendas=guardaropa.getPrendas().stream().filter(prenda->prenda.getDisponibilidad()).collect(Collectors.toList());
        	List<Prenda> prendasTorso = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.TORSO.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
	        List<Prenda> prendasParteInferior = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.PARTEINFERIOR.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
	        List<Prenda> prendasCalzado  = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CALZADO.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
	        List<Prenda> prendasAccesorio = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.ACCESORIOS.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
	        List<Prenda> prendasManos = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.MANOS.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
	        List<Prenda> prendasCabeza = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CABEZA.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
	        List<Prenda> prendasCuello = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CUELLO.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
	       
     
            parametros.put("login", LoginController.isUsuarioLogin(request));
            parametros.put("guardaropaId",idGuardaropa);
            parametros.put("estilo",paramEstilo);
            parametros.put("prendasTorso", prendasTorso);
	        parametros.put("prendasParteInferior", prendasParteInferior);
	        parametros.put("prendasCalzado", prendasCalzado);
	        parametros.put("prendasAccesorio", prendasAccesorio);
	        parametros.put("prendasManos", prendasManos);
	        parametros.put("prendasCabeza", prendasCabeza);
	        parametros.put("prendasCuello", prendasCuello);
        }
        return new ModelAndView(parametros, "addOutfit.hbs");
    }
    

    private void asignarAtributosA(Atuendo atuendo, Request request){
    	
    	if(request.params(":idGuardaropa") != null){
         	 RepositorioGuardaropa repoGuardaropa = FactoryRepositorioGuardaropa.get();
         	 Guardaropa guardaropa = repoGuardaropa.buscar(new Long (request.params(":idGuardaropa")));
         	 guardaropa.agregarAtuendo(atuendo);	 
         }
    	
    	if(request.queryParams("torso") != null){
    		RepositorioPrenda repoPrenda = FactoryRepositorioPrenda.get();
        	Prenda prenda= repoPrenda.buscar(new Long (request.queryParams("torso")));
        	atuendo.agregarPrenda(prenda);

        }
      
    	if(request.queryParams("inferior") != null){
    		RepositorioPrenda repoPrenda = FactoryRepositorioPrenda.get();
        	Prenda prenda= repoPrenda.buscar(new Long (request.queryParams("inferior")));
        	atuendo.agregarPrenda(prenda);
            
        }
    	
    	if(request.queryParams("calzado") != null){
    		RepositorioPrenda repoPrenda = FactoryRepositorioPrenda.get();
        	Prenda prenda= repoPrenda.buscar(new Long (request.queryParams("calzado")));
        	atuendo.agregarPrenda(prenda);
            
        }
    	
    	if(request.queryParams("accesorio") != null){
    		RepositorioPrenda repoPrenda = FactoryRepositorioPrenda.get();
        	Prenda prenda= repoPrenda.buscar(new Long (request.queryParams("accesorio")));
        	atuendo.agregarPrenda(prenda);
            
        }
    	
    	if(request.queryParams("manos") != null){
    		RepositorioPrenda repoPrenda = FactoryRepositorioPrenda.get();
        	Prenda prenda= repoPrenda.buscar(new Long (request.queryParams("manos")));
        	atuendo.agregarPrenda(prenda);
            
        }
    	
    	if(request.queryParams("cabeza") != null){
    		RepositorioPrenda repoPrenda = FactoryRepositorioPrenda.get();
        	Prenda prenda= repoPrenda.buscar(new Long (request.queryParams("cabeza")));
        	atuendo.agregarPrenda(prenda);
            
        }
    	
    	if(request.queryParams("cuello") != null){
    		RepositorioPrenda repoPrenda = FactoryRepositorioPrenda.get();
        	Prenda prenda= repoPrenda.buscar(new Long (request.queryParams("cuello")));
        	atuendo.agregarPrenda(prenda);
            
        }
    	 
  }
    
    public Response guardarOutfit(Request request, Response response) {
    	Map<String, Object> parametros = new HashMap<>();
    	
    	
    	Atuendo atuendo = new Atuendo();
        asignarAtributosA(atuendo, request);
        this.repo.agregar(atuendo);

        response.redirect("/outfit/"+request.params(":idGuardaropa"));
        //response.redirect("/guardaropa/request.params(":id")");
        return response;
    }
    
    public Response calificarOutfit(Request request, Response response) {
    	RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
       
        Long idGuardaropa =new Long(request.params(":idGuardaropa"));
        Long idAtuendo = new Long(request.params(":idAtuendo"));
        
        int numCalificacion=0;
        if(request.queryParams("estrellas-"+idAtuendo)!=null) {
        	numCalificacion= new Integer(request.queryParams("estrellas-"+idAtuendo));
        }
        Guardaropa guardaropa=usuario.getRoperos().stream().filter(guarda->guarda.getId_guardaropa()==idGuardaropa).collect(Collectors.toList()).get(0);
        Atuendo atuendo=repo.buscar(idAtuendo); 
       
        if(guardaropa.getAtuendosMostrados().contains(atuendo)) {
        	if(numCalificacion >0){
        		CalificacionAtuendo calif= new CalificacionAtuendo();
        		calif.setUsuario(usuario);
        		calif.setAtuendo(atuendo);
        		calif.setCalificacion(numCalificacion);
        		
        		RepositorioCalificacion repoCalificacion = FactoryRepositorioCalificacion.get();
        		List<CalificacionAtuendo> allCalificaciones=repoCalificacion.buscarTodos();
        		Optional<CalificacionAtuendo> unaCalificacion=allCalificaciones.stream().filter(c1->c1.getAtuendo()==atuendo && c1.getUsuario()==usuario).findFirst();
        		if(unaCalificacion.isPresent()) {
        			
        			unaCalificacion.get().setCalificacion(numCalificacion);
        			repoCalificacion.modificar(unaCalificacion.get());
        		}else {
        		atuendo.setterCalificacion(calif);
        		repoCalificacion.agregar(calif);
        		}
            }
        }



        response.redirect("/outfit/"+idGuardaropa);
        //response.redirect("/guardaropa/request.params(":id")");
        return response;
    }
    
}
