package ar.utn.dds.controllers;
//import entities.Rol;

import ar.utn.dds.modelo.Atuendo;
import ar.utn.dds.modelo.Categoria;
import ar.utn.dds.modelo.Color;
import ar.utn.dds.modelo.Estilo;
import ar.utn.dds.modelo.Guardaropa;
import ar.utn.dds.modelo.Material;
import ar.utn.dds.modelo.Prenda;
import ar.utn.dds.modelo.Usuario;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.RepositorioOutfit;
import ar.utn.dds.repositories.RepositorioTipoPrenda;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioAtuendo;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioTipoPrenda;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;
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

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Atuendo> atuendos = this.repo.buscarTodos();
        parametros.put("atuendos", atuendos);
        return  new ModelAndView(parametros, "outfit.hbs");
    }

    public ModelAndView mostrarImagenDePrenda(Request request, Response response){
        int id = new Integer(request.params("id"));
        Atuendo atuendoBuscado = this.repo.buscar(id);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("imgPrendas", atuendoBuscado.getPrendas().get(0).getFotografo().imagenes().get(0));
        ModelAndView vista = new ModelAndView(parametros, "outfit.hbs");
        return vista;
    }

    public ModelAndView mostrarPrendas(Request request, Response response){
        int id = new Integer(request.params("id"));
        Atuendo atuendoBuscado = this.repo.buscar(id);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("prendas", atuendoBuscado.getPrendas());
        ModelAndView vista = new ModelAndView(parametros, "outfit.hbs");
        return vista;
    }


    public ModelAndView mostrar(Request request, Response response){
        int id = new Integer(request.params("id"));
        Atuendo atuendoBuscado = this.repo.buscar(id);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("atuendo", atuendoBuscado);
        ModelAndView vista = new ModelAndView(parametros, "outfit.hbs");
        return vista;
    }
    
    public ModelAndView mostrarOutfit(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
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
        	List<Atuendo> atuendosElegantes=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.ELEGANTE)))).collect(Collectors.toList());
        	List<Atuendo> atuendosElegantSport=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.ELEGANTSPORT)))).collect(Collectors.toList());
        	List<Atuendo> atuendosDeportivo=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.DEPORTIVO)))).collect(Collectors.toList());
        	List<Atuendo> atuendosEntreCasa=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.ENTRECASA)))).collect(Collectors.toList());
        	List<Atuendo> atuendosNavidenio=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.NAVIDENIO)))).collect(Collectors.toList());
        	List<Atuendo> atuendosNormal=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.NORMAL)))).collect(Collectors.toList());
        	List<Atuendo> atuendosPlayero=atuendos.stream().filter(atuendo -> atuendo.getPrendas().stream().anyMatch(prenda -> prenda.getEstilo().stream().anyMatch(estilo -> estilo.equals(Estilo.PLAYERO)))).collect(Collectors.toList());
        	
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
        return  new ModelAndView(parametros, "outfit2.hbs");
        
    }
    
    public ModelAndView crearOutfit(Request request, Response response) {
    	Map<String, Object> parametros = new HashMap<>();
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
        	List<Prenda> prendas=guardaropa.getPrendas().stream().filter(prenda->prenda.getDisponibilidad()).collect(Collectors.toList());List<Prenda> prendasTorso = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.TORSO.getCategoria())==0).collect(Collectors.toList());
	        List<Prenda> prendasParteInferior = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.PARTEINFERIOR.getCategoria())==0).collect(Collectors.toList());
	        List<Prenda> prendasCalzado  = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CALZADO.getCategoria())==0).collect(Collectors.toList());
	        List<Prenda> prendasAccesorio = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.ACCESORIOS.getCategoria())==0).collect(Collectors.toList());
	        List<Prenda> prendasManos = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.MANOS.getCategoria())==0).collect(Collectors.toList());
	        List<Prenda> prendasCabeza = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CABEZA.getCategoria())==0).collect(Collectors.toList());
	        List<Prenda> prendasCuello = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getTipo().compareTo(Categoria.CUELLO.getCategoria())==0).collect(Collectors.toList());
	       
        	//if()
            List<Color> colores= Arrays.asList(Color.values());
            List<Material> materiales= Arrays.asList(Material.values());
            List<Estilo> estilos= Arrays.asList(Estilo.values());
            parametros.put("login", LoginController.isUsuarioLogin(request));
            parametros.put("guardaropaId",new Long (request.params(":id")));
            //parametros.put("tipoPrendas", repoTipoPrenda.buscarTodos());
            //parametros.put("guardaropas", repoGuarda.buscarTodos());
            parametros.put("colores", colores);
            parametros.put("materiales", materiales);
            parametros.put("estilos", estilos);
        }
        return new ModelAndView(parametros, "addOut.hbs");
    }
    
    public ModelAndView guardarOutfit(Request request, Response response) {
    	Map<String, Object> parametros = new HashMap<>();
    	return new ModelAndView(parametros, "addOut.hbs"); // modificar esto
    }
}
