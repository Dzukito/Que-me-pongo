package ar.utn.dds.controllers;
//import entities.Rol;

import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.modelo.clases.Ubicacion;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.clima.MeteorologoAccuWeatherAdapter;
import ar.utn.dds.modelo.clima.Pronostico;
import ar.utn.dds.modelo.clima.TipoClima;
import ar.utn.dds.modelo.interfaces.Meteorologo;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.modelo.clases.CalificacionAtuendo;
import ar.utn.dds.modelo.clases.ConjuntosPredefinidos;
import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.Color;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.clases.Fotografo;
import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.modelo.ropa.Material;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.ropa.TipoPrenda;
import ar.utn.dds.modelo.sensibilidades.Ermitanio;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.repositories.RepositorioCalificacion;
import ar.utn.dds.repositories.RepositorioEvento;
import ar.utn.dds.repositories.RepositorioFotografo;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.repositories.RepositorioOutfit;
import ar.utn.dds.repositories.RepositorioPrenda;
import ar.utn.dds.repositories.RepositorioTipoPrenda;
import ar.utn.dds.repositories.RepositorioUbicacion;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.factories.FactoryRepositorioAtuendo;

import ar.utn.dds.repositories.factories.FactoryRepositorioCalificacion;
import ar.utn.dds.repositories.factories.FactoryRepositorioEvento;
import ar.utn.dds.repositories.factories.FactoryRepositorioCalificacion;
import ar.utn.dds.repositories.factories.FactoryRepositorioFotografo;
import ar.utn.dds.repositories.factories.FactoryRepositorioGuardaropa;
import ar.utn.dds.repositories.factories.FactoryRepositorioPrenda;
import ar.utn.dds.repositories.factories.FactoryRepositorioTipoPrenda;
import ar.utn.dds.repositories.factories.FactoryRepositorioUbicacion;
import ar.utn.dds.repositories.factories.FactoryRepositorioUsuario;

import ar.utn.dds.spark.utils.AtuendoCalifVista;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
			List<Prenda> prendasTorso = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().toString().compareTo(Categoria.TORSO.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
			List<Prenda> prendasParteInferior = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().toString().compareTo(Categoria.PARTEINFERIOR.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
			List<Prenda> prendasCalzado  = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().toString().compareTo(Categoria.CALZADO.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
			List<Prenda> prendasAccesorio = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().toString().compareTo(Categoria.ACCESORIOS.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
			List<Prenda> prendasManos = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().toString().compareTo(Categoria.MANOS.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
			List<Prenda> prendasCabeza = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().toString().compareTo(Categoria.CABEZA.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());
			List<Prenda> prendasCuello = prendas.stream().filter(prenda->prenda.getTipoDePrenda().getCategoria().toString().compareTo(Categoria.CUELLO.getCategoria())==0 && prenda.getEstilos().get(0).toString().compareTo(paramEstilo)==0).collect(Collectors.toList());


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
//-----------------------------------------SUGERENCIAS--------------------------------------------------------------------------||
	public Response sugerirOutfit(Request request, Response response) {
		Map<String, Object> parametros = new HashMap<>();
		/*Algunas cosas a usar:*/
		Atuendo atuendoSugerido=null;
		Meteorologo meteorologo = new MeteorologoAccuWeatherAdapter();
		Ubicacion buenosAires= buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
		Pronostico pronostico;
		Estilo estilo=null;
		/*                   */
		/*1) Seteo el Estilo que quiere mi usuario para la sugerencia*/
		if(request.queryParams("estilo") != null){
			String estiloRecibido= (request.queryParams("estilo"));
			switch(estiloRecibido) {
				case "ELEGANTE":
					estilo=(Estilo.ELEGANTE);
					break;
				case "ELEGANTSPORT":
					estilo=(Estilo.ELEGANTSPORT);
					break;
				case "DEPORTIVO":
					estilo=(Estilo.DEPORTIVO);
					break;
				case "ENTRECASA":
					estilo=(Estilo.ENTRECASA);
					break;
				case "NAVIDENIO":
					estilo=(Estilo.NAVIDENIO);
					break;
				case "NORMAL":
					estilo=(Estilo.NORMAL);
					break;
				case "PLAYERO":
					estilo=(Estilo.PLAYERO);
					break;}
		}
		/*2)Agarro el guardaropas que va a sugerir*/
		if(request.params(":idGuardaropa") != null){ //seteo el guardaropas
			RepositorioGuardaropa repoGuardaropa = FactoryRepositorioGuardaropa.get();
			Guardaropa guardaropa = repoGuardaropa.buscar(new Long (request.params(":idGuardaropa")));
			/*3) Agarro mi usuario*/
			RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
			Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));


			/*4)Consulto si va a ser una sugerencia RANDOM o por evento*/
			if(request.queryParams("evento")  != null && new Long(request.queryParams("evento")) ==-999) { //sugerime para ahora, no para un evento (-999 seria una id inexistente)
				meteorologo.getPronosticos(buenosAires);
//
				Calendar fecha = Calendar.getInstance();
				fecha.add(Calendar.HOUR, 3);
//            	fecha.setTime(fecha1);
				ArrayList<TipoClima>  nublado = new ArrayList<TipoClima>(Arrays.asList(TipoClima.NUBLADO));
				//pronostico = meteorologo.getPronosticoTiempoYUbicacion(fecha, buenosAires); //seteo el pronostico
				pronostico= new Pronostico((float)23,nublado,(float)1.1);
				/*5)Sugerir*/
				atuendoSugerido=guardaropa.sugerirAtuendo(pronostico, estilo, usuario);

			}
			if(request.queryParams("evento")  != null && new Long(request.queryParams("evento")) !=-999) { //sugerime para un evento

				RepositorioEvento repoEvento= FactoryRepositorioEvento.get();
				Evento evento = repoEvento.buscar(new Long (request.params("evento")));
				Calendar fecha = Calendar.getInstance();
				fecha.add(Calendar.HOUR, 3);
//             	fecha.setTime(fecha1);
				ArrayList<TipoClima>  nublado = new ArrayList<TipoClima>(Arrays.asList(TipoClima.NUBLADO));
				//pronostico = meteorologo.getPronosticoTiempoYUbicacion(fecha, buenosAires); //seteo el pronostico
				pronostico= new Pronostico((float)23,nublado,(float)1.1);


				/*5)Sugerir*/
				atuendoSugerido=guardaropa.sugerirAtuendo(pronostico, evento, usuario);

			}
			if(atuendoSugerido!=null) { //Encontro algo que sugerir
				this.repo.agregar(atuendoSugerido);
				guardaropa.agregarAtuendo(atuendoSugerido);
				repoGuardaropa.modificar(guardaropa);}
			else
				System.out.println("ATENCION: NO HAY SUGERENCIAS"); //Puede que no tenga las prendas suficientes que satisfagan el conjunto, o no cumplan el estilo dichas prendas. Hay varios motivos para llegar aca...
		}

		response.redirect("/outfit/"+request.params(":idGuardaropa"));
		//response.redirect("/guardaropa/request.params(":id")");
		return response;
	}
	public ModelAndView crearSugerenciaPorGuardaropa(Request request, Response response) {
		Map<String, Object> parametros = new HashMap<>();
		RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
		Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
		RepositorioEvento repoEvento= FactoryRepositorioEvento.get();


		Long idGuardaropa = new Long(request.params(":idGuardaropa"));
		List<Estilo> estilos= Arrays.asList(Estilo.values());
		List<Evento> eventos =repoEvento.buscarTodos().stream().filter(evento->usuario.getEventos().contains(evento)).collect(Collectors.toList());


		parametros.put("login", LoginController.isUsuarioLogin(request));
		parametros.put("guardaropaId",idGuardaropa);
		parametros.put("estilos",estilos);
		parametros.put("eventos", eventos);

		return new ModelAndView(parametros, "sugerirOutfit.hbs");
	}
	//-----------------------------------------SUGERENCIA-POR-GUARDAROPA------------------------------------------------------------||
	public ModelAndView sugerirOutfitPorGuardaropa(Request request, Response response) {
		Map<String, Object> parametros = new HashMap<>();

		Atuendo atuendoSugerido;
		Meteorologo meteorologo = new MeteorologoAccuWeatherAdapter();

		RepositorioUbicacion repoUbicacion=FactoryRepositorioUbicacion.get();
		Ubicacion buenosAires=repoUbicacion.buscarTodos().get(0);
		Pronostico pronostico;
		Estilo estilo=null;

		String estiloRecibido= request.params(":nombreEstilo");
		meteorologo.getPronosticos(buenosAires);
		List<Atuendo> atuendos = new ArrayList<Atuendo>();
		/*1) Seteo el Estilo que quiere mi usuario para la sugerencia*/
		if(request.params(":nombreEstilo") != null){ 

			switch(estiloRecibido) {
				case "ELEGANTE":
					estilo=(Estilo.ELEGANTE);
					break;
				case "ELEGANTSPORT":
					estilo=(Estilo.ELEGANTSPORT);
					break;
				case "DEPORTIVO":
					estilo=(Estilo.DEPORTIVO);
					break;
				case "ENTRECASA":
					estilo=(Estilo.ENTRECASA);
					break;
				case "NAVIDENIO":
					estilo=(Estilo.NAVIDENIO);
					break;
				case "NORMAL":
					estilo=(Estilo.NORMAL);
					break;
				case "PLAYERO":
					estilo=(Estilo.PLAYERO);
					break;}
		}

		/*2)Verifico guardaropa para ese usuario*/

		if(request.params(":idGuardaropa") != null){

			RepositorioUsuario repoUsuario = FactoryRepositorioUsuario.get();
			Usuario usuario = repoUsuario.buscar(request.session().attribute("nombreDeUsuario"));
			usuario.setSensibilidad(new Ermitanio());

			Long idGuardaropa = new Long(request.params(":idGuardaropa"));
			List<Guardaropa> guardaropas=usuario.getRoperos().stream().filter(guarda->guarda.getId_guardaropa()==idGuardaropa).collect(Collectors.toList());
			if(guardaropas.size()>0) {
				Guardaropa guardaropa=guardaropas.get(0);
				Calendar fecha = Calendar.getInstance();
				fecha.add(Calendar.HOUR, 9);
				pronostico = meteorologo.getPronosticoTiempoYUbicacion(fecha, buenosAires); //seteo el pronostico
			
				atuendoSugerido=guardaropa.sugerirAtuendoPorGuardaropa(pronostico, estilo);

				if(atuendoSugerido!=null) {
				atuendos.add(atuendoSugerido);
				}
			}

		}
		parametros.put("login", LoginController.isUsuarioLogin(request));
		parametros.put("guardaropaId",request.params(":idGuardaropa"));
		parametros.put("estilo",request.params(":nombreEstilo"));
		parametros.put("atuendos", atuendos);
		return new ModelAndView(parametros, "mostrarSugerencia.hbs");

	}
	public Response guardarOutfitPorGuardaropa(Request request, Response response) {

		Atuendo atuendo = new Atuendo();
		Prenda prenda;
		RepositorioPrenda repoPrenda=FactoryRepositorioPrenda.get();
		Set<String> claves=request.queryParams();
		for (String clave : claves) {
			prenda = repoPrenda.buscar(new Long(request.queryParams(clave)));
			atuendo.agregarPrenda(prenda);
		}
		if(request.params(":idGuardaropa") != null){
			RepositorioGuardaropa repoGuardaropa = FactoryRepositorioGuardaropa.get();
			Guardaropa guardaropa = repoGuardaropa.buscar(new Long (request.params(":idGuardaropa")));
			guardaropa.agregarAtuendo(atuendo);
		}

		this.repo.agregar(atuendo);


		response.redirect("/outfit/"+request.params(":idGuardaropa"));
		//response.redirect("/guardaropa/request.params(":id")");
		return response;
	}
}
