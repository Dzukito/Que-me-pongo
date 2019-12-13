package ar.utn.dds.controllers;
import ar.utn.dds.modelo.clases.Fotografo;
import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.ropa.*;
import ar.utn.dds.repositories.*;
import ar.utn.dds.repositories.factories.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

public class PrendaController {
    private RepositorioPrenda repoPrenda;
    public PrendaController(){
        this.repoPrenda = FactoryRepositorioPrenda.get();
    }

    public ModelAndView crear(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        RepositorioTipoPrenda repoTipoPrenda = FactoryRepositorioTipoPrenda.get();
        RepositorioGuardaropa repoGuarda = FactoryRepositorioGuardaropa.get();
        List<Color> colores= Arrays.asList(Color.values());
        List<Material> materiales= Arrays.asList(Material.values());
        List<Estilo> estilos= Arrays.asList(Estilo.values());
        parametros.put("login", LoginController.isUsuarioLogin(request));
        parametros.put("guardaropaId",new Long (request.params(":id")));
        parametros.put("tipoPrendas", repoTipoPrenda.buscarTodos());
        parametros.put("guardaropas", repoGuarda.buscarTodos());
        parametros.put("colores", colores);
        parametros.put("materiales", materiales);
        parametros.put("estilos", estilos);
        return new ModelAndView(parametros, "addPrenda.hbs");
    }
    

    
    private void asignarAtributosA(Prenda prenda, Request request){
/*    	
    	if(request.queryParams("guardaropas") != null){
          	 RepositorioGuardaropa repoGuardaropa = FactoryRepositorioGuardaropa.get();
          	 Guardaropa guarda = repoGuardaropa.buscar(new Long(request.queryParams("guardaropas")));
          	 guarda.agregarPrenda(prenda);
         
          	 
          }
*/   
    	if(request.params(":id") != null){
         	 RepositorioGuardaropa repoGuardaropa = FactoryRepositorioGuardaropa.get();
         	 Guardaropa guarda = repoGuardaropa.buscar(new Long (request.params(":id")));
         	 guarda.agregarPrenda(prenda);	 
         }
      
    	if(request.queryParams("tipoPrenda") != null){
       	 RepositorioTipoPrenda repoTipoPrenda = FactoryRepositorioTipoPrenda.get();
       	 TipoPrenda tipoPrendarecibido = repoTipoPrenda.buscar(new Long(request.queryParams("tipoPrenda")));
       	 prenda.setTipoPrenda(tipoPrendarecibido);
       }
    	
    	 if(request.queryParams("nivelCalor") != null){ //REVISAR
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
        if(request.queryParams("material") != null){
            String materialRecibido= (request.queryParams("material")); //HAY QUE FILTRAR!!!
            switch(materialRecibido) {
            case "PLASTICO":
            	prenda.setMaterial(Material.PLASTICO);
            	break;
	        case "CUERO":
	        	prenda.setMaterial(Material.CUERO);
	        	break;
	       case "JEAN":
	    		prenda.setMaterial(Material.JEAN);
	    	break;
			case "ALGODON":
				prenda.setMaterial(Material.ALGODON);
				break;
			case "MALLA":
				prenda.setMaterial(Material.MALLA);
				break;
			case "LINO":
				prenda.setMaterial(Material.LINO);
				break;
			case "FRANELA":
				prenda.setMaterial(Material.FRANELA);
				break;
		
        }
           
      }
        if(request.queryParams("estilo") != null){
            String estiloRecibido= (request.queryParams("estilo"));
            switch(estiloRecibido) {
            case "ELEGANTE":
            	prenda.setEstilo(Estilo.ELEGANTE);
            	break;
	        case "ELEGANTSPORT":
	        	prenda.setEstilo(Estilo.ELEGANTSPORT);
	        	break;
	       case "DEPORTIVO":
	    		prenda.setEstilo(Estilo.DEPORTIVO);
	    	break;
			case "ENTRECASA":
				prenda.setEstilo(Estilo.ENTRECASA);
				break;
			case "NAVIDENIO":
				prenda.setEstilo(Estilo.NAVIDENIO);
				break;
			case "NORMAL":
				prenda.setEstilo(Estilo.NORMAL);
				break;
			case "PLAYERO":
				prenda.setEstilo(Estilo.PLAYERO);
				break;
		
        }
           
      }
         
        if(request.queryParams("colorPrimario") != null){
            String colorRecibido= (request.queryParams("colorPrimario"));
            switch(colorRecibido) {
            case "Rojo":
            	prenda.setColorPrimario(Color.Rojo);
            	break;
            	        case "Azul":
	        	prenda.setColorPrimario(Color.Azul);
	        	break;
	        
	       case "Amarillo":
	    	prenda.setColorPrimario(Color.Amarillo);
	    	break;
	    
			case "Verde":
				prenda.setColorPrimario(Color.Verde);
				break;
			
			case "Violeta":
				prenda.setColorPrimario(Color.Violeta);
				break;
			case "Naranja":
				prenda.setColorPrimario(Color.Naranja);
				break;
			case "Negro":
				prenda.setColorPrimario(Color.Negro);
				break;
			case "Blanco":
				prenda.setColorPrimario(Color.Blanco);
				break;
        }
       }
        
        if(request.queryParams("colorSecundario") != null){
        	String colorRecibido=(request.queryParams("colorSecundario")); //El color 0 en la lista es el primario, asi que si agrega otro sera secundario
            switch(colorRecibido) { //Perdon por la repeticion de codigo
            case "Rojo":
            	prenda.setColorSecundario(Color.Rojo);
            	break;
	        case "Azul":
	        	prenda.setColorSecundario(Color.Azul);
	        	break;
	        
	       case "Amarillo":
	    	prenda.setColorSecundario(Color.Amarillo);
	    	break;
	    
			case "Verde":
				prenda.setColorSecundario(Color.Verde);
				break;
			
			case "Violeta":
				prenda.setColorSecundario(Color.Violeta);
				break;
			case "Naranja":
				prenda.setColorSecundario(Color.Naranja);
				break;
			case "Negro":
				prenda.setColorSecundario(Color.Negro);
				break;
			case "Blanco":
				prenda.setColorSecundario(Color.Blanco);
				break;
        }
        }
    }

    Prenda aux;
    public Response guardar(Request request, Response response){
    	//FALTA MANEJO DE SESIONES!!!
        Prenda prenda = new Prenda();
        asignarAtributosA(prenda, request);
        this.repoPrenda.agregar(prenda);
        aux=prenda;

        if(request.queryParams("file") != null){
        	RepositorioFotografo repoFotografo = FactoryRepositorioFotografo.get();
       	 	Fotografo fotografo = repoFotografo.buscar(prenda.getFotografo().getId_fotografo());
        	fotografo.cargarUnicaImagen(request.queryParams("file"));
        	repoFotografo.agregar(fotografo);
 //       	prenda.setFotografo(fotografo);
        }

        response.redirect("/Imagen");
        //response.redirect("/guardaropa/request.params(":id")");
        return response;
    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------------
    
    public Response guardarFoto(Request request, Response response){
    	//FALTA MANEJO DE SESIONES!!!
    	
    	Prenda prenda = repoPrenda.buscar(aux.getId_prenda());
    	String path=null;
        try {
			path=guardarImagen(request,prenda);
			prenda.getFotografo().cargarUnicaImagen(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       this.repoPrenda.modificar(prenda);

        response.redirect("/home");
        //response.redirect("/guardaropa/request.params(":id")");
        return response;
    }
    
    public ModelAndView crearImagen(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
        Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
        return new ModelAndView(parametros, "imagenPrenda.hbs");
    }
//-----------------------------------------------------
    
    public String guardarImagen(Request req,Prenda prenda) throws IOException, ServletException {
    	File uploadDir = new File("src\\main\\resources\\public\\img");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist

            Path tempFile = Files.createTempFile(uploadDir.toPath(),"","" );
            Path targetDir = Paths.get("src\\main\\resources\\public\\img"); 
            
            
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            
            Path target = targetDir.resolve("testWOWO.jpg");
            
            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
                
                
            }
           	
            logInfo(req, tempFile);
            System.out.println("TEMPFILE:"+tempFile.getFileName().toString());
            return "testWOWO.jpg";

    			}

		    // methods used for logging
		    private  void logInfo(Request req, Path tempFile) throws IOException, ServletException {
		        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("uploaded_file")) + "' saved as '" + tempFile.toAbsolutePath().toString() + "'");
		    }
		
		    private  String getFileName(Part part) {
		        for (String cd : part.getHeader("content-disposition").split(";")) {
		            if (cd.trim().startsWith("filename")) {
		                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
		            }
		        }
		        return null;

    }   
    
}
