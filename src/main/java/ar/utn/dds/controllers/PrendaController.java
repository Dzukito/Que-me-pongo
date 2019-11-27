package ar.utn.dds.controllers;
import ar.utn.dds.modelo.*;
import ar.utn.dds.repositories.*;
import ar.utn.dds.repositories.factories.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrendaController {
    private RepositorioPrenda repo;
    public PrendaController(){
        this.repo = FactoryRepositorioPrenda.get();
    }

    
    public ModelAndView crear(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        RepositorioTipoPrenda repoTipoPrenda = FactoryRepositorioTipoPrenda.get();
        List<Color> colores= Arrays.asList(Color.values());
        parametros.put("tipoPrendas", repoTipoPrenda.buscarTodos());
        parametros.put("colores", colores);
        return new ModelAndView(parametros, "addPrenda.hbs");
    }

    private void asignarAtributosA(Prenda prenda, Request request){
    	
    	 if(request.queryParams("nivelCalor") != null){
    		 int nivel= new Integer(request.queryParams("nivelCalor"));
             prenda.setNivelDeCalor(nivel);
         }

    	if(request.queryParams("nombrePrenda") != null){
            prenda.setNombrePrenda(request.queryParams("nombrePrenda"));
        }
        
        if(request.queryParams("sexo") != null){
            prenda.setSexo(request.queryParams("sexo"));
        }
        
        if(request.queryParams("tipoPrenda") != null){
        	 RepositorioTipoPrenda repoTipoPrenda = FactoryRepositorioTipoPrenda.get();
        	 TipoPrenda tipoPrendarecibido = repoTipoPrenda.buscar(new Long(request.queryParams("tipoPrenda")));
        	 prenda.setTipoPrenda(tipoPrendarecibido);
        }
        
        if(request.queryParams("colorPrimario") != null){
            String colorRecibido= (request.queryParams("colorPrimario"));
            switch(colorRecibido) {
            case "Rojo":
            	prenda.setColor(Color.Rojo);
            	break;
            
	        case "Azul":
	        	prenda.setColor(Color.Azul);
	        	break;
	        
	       case "Amarillo":
	    	prenda.setColor(Color.Amarillo);
	    	break;
	    
			case "Verde":
				prenda.setColor(Color.Verde);
				break;
			
			case "Violeta":
				prenda.setColor(Color.Violeta);
				break;
			case "Naranja":
				prenda.setColor(Color.Naranja);
				break;
			case "Negro":
				prenda.setColor(Color.Negro);
				break;
			case "Blanco":
				prenda.setColor(Color.Blanco);
				break;
        }
       }
        
        if(request.queryParams("colorSecundario") != null){
        	String colorRecibido=(request.queryParams("colorPrimario")); //El color 0 en la lista es el primario, asi que si agrega otro sera secundario
            switch(colorRecibido) { //Perdon por la repeticion de codigo
            case "Rojo":
            	prenda.setColor(Color.Rojo);
            	break;
            
	        case "Azul":
	        	prenda.setColor(Color.Azul);
	        	break;
	        
	       case "Amarillo":
	    	prenda.setColor(Color.Amarillo);
	    	break;
	    
			case "Verde":
				prenda.setColor(Color.Verde);
				break;
			
			case "Violeta":
				prenda.setColor(Color.Violeta);
				break;
			case "Naranja":
				prenda.setColor(Color.Naranja);
				break;
			case "Negro":
				prenda.setColor(Color.Negro);
				break;
			case "Blanco":
				prenda.setColor(Color.Blanco);
				break;
        }
        }
    }

    public Response guardar(Request request, Response response){
    	//FALTA MANEJO DE SESIONES!!!
        Prenda prenda = new Prenda();
        asignarAtributosA(prenda, request);
        this.repo.agregar(prenda);
        response.redirect("/home");
        return response;
    }
    
}
