package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.Inicializador;
import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.modelo.clases.Ubicacion;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.clima.MeteorologoWeatherAdapter;
import ar.utn.dds.modelo.interfaces.Meteorologo;
import ar.utn.dds.modelo.ropa.*;
import ar.utn.dds.modelo.tarea.EjecutorDeTarea;
import ar.utn.dds.modelo.tarea.TareaProgramada;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;
import java.util.concurrent.TimeUnit;
@DisplayName("Los test del command de ejecutor de tareas")
public class EjecutorDeTareasTest {
	Ubicacion buenosAires;
	Calendar fechaDeHoy, fechaDeHoyMas2Hora;
	Evento irATrabajar, irAlGym, irALaFacu;
	Usuario usuario1;
	HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio, materialParaguas, materialLentes;
	TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos, paraguas,lentes;
	Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2, accesorio3,accesorio4;
	ArrayList<Prenda> prendas1, prendas2, prendas3;
	Guardaropa ropero1, ropero2, ropero3;
	Meteorologo meteorologo;
	ArrayList<Usuario> usuarios;
	@Before
	public void setup(){
		//Meteorologos
		meteorologo = new MeteorologoWeatherAdapter();
		//Fechas
		fechaDeHoyMas2Hora = Calendar.getInstance();
		fechaDeHoyMas2Hora.add(Calendar.HOUR, 7);
		fechaDeHoy = Calendar.getInstance();
		fechaDeHoy.add(Calendar.HOUR, 2);
		//Ubicaciones
		buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
		//Eventos
		irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.DEPORTIVO);
		irALaFacu = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.ENTRECASA);
		irATrabajar = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.ELEGANTSPORT);
		//Materiales por prenda
		materialRemera = new HashSet<Material>(Arrays.asList(Material.LINO,Material.FRANELA,Material.ALGODON));
		materialPantalon = new HashSet<Material>(Arrays.asList(Material.LINO,Material.MALLA,Material.JEAN,Material.CUERO));
		materialCalzado = new HashSet<Material>(Arrays.asList(Material.LINO,Material.CUERO,Material.PLASTICO));
		materialAccesorio = new HashSet<Material>(Arrays.asList(Material.LINO,Material.PLASTICO,Material.ACEROINOXIDABLE,Material.CUERO));
		materialLentes = new HashSet<Material>(Arrays.asList(Material.LINO,Material.PLASTICO));
		materialParaguas = new HashSet<Material>(Arrays.asList(Material.LINO,Material.PLASTICO));
		//Tipos de prenda
		pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
		pantalonCorto = new TipoPrenda(Categoria.PARTEINFERIOR, "PantalonCorto", materialPantalon);
		remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
		top = new TipoPrenda(Categoria.TORSO, "Top", materialRemera);
		zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
		zapatos = new TipoPrenda(Categoria.CALZADO, "Zapatos", materialCalzado);
		accesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
		bufanda = new TipoPrenda(Categoria.ACCESORIOS, "Bufanda", materialAccesorio);
		paraguas = new TipoPrenda(Categoria.ACCESORIOS, "Paraguas", materialParaguas);
		lentes = new TipoPrenda(Categoria.ACCESORIOS, "Lentes", materialLentes);
		//Prendas
		remera1 = new Prenda(remera, "RemeraDePandas", Color.Blanco,Color.Negro, Material.LINO,Estilo.DEPORTIVO);
		remera2 = new Prenda(top, "Top", Color.Blanco,Color.Negro, Material.LINO, Estilo.DEPORTIVO);
		pantalon1 = new Prenda(pantalon, "Pantalon1", Color.Azul,  Material.LINO);
		pantalon2 = new Prenda(pantalonCorto, "PantalonCorto", Color.Amarillo, Material.LINO);
		zapatillas1 = new Prenda(zapatillas, "Zapatillas1", Color.Rojo,Color.Verde, Material.LINO,Estilo.DEPORTIVO);
		zapatillas2 = new Prenda(zapatos, "Zapatos", Color.Blanco,Color.Negro, Material.LINO);
		accesorio1 = new Prenda(accesorio, "Gorra", Color.Azul, Material.LINO);
		accesorio2 = new Prenda(bufanda, "Bufanda", Color.Amarillo, Material.LINO);
		accesorio3 = new Prenda(paraguas, "Paraguas", Color.Amarillo, Material.PLASTICO);
		accesorio4 = new Prenda(lentes, "Lentes", Color.Amarillo, Material.PLASTICO);
		//Lista de prendas
		prendas1 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1,accesorio3,accesorio4));
		prendas2 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1,remera2,accesorio2,zapatillas2,pantalon2,accesorio4));
		prendas3 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1,remera2,accesorio4));
		//Guardaropas
		ropero1 = new Guardaropa(prendas1);
		ropero2 = new Guardaropa(prendas2);
		ropero3 = new Guardaropa(prendas3);
		//Meteorologos
		meteorologo = new MeteorologoWeatherAdapter();
		//Usuarios
		usuario1 = new Usuario("Martin", new ArrayList<Guardaropa>(Arrays.asList(ropero1,ropero2,ropero3)));
		usuario1.agregarEvento(irAlGym);
		usuarios = new ArrayList<Usuario>(Arrays.asList(usuario1));
	}

	//El test funciona pero consume una api de acción bloqueante, que solo se puede correr 5 veces al día, por eso lo commento.

//	@Test
//	@DisplayName("Test para verificar que se generan las sugerencias después de un tiempo determinado de la creación de un evento")
//	public void test() throws InterruptedException {
//		meteorologo.getPronosticos(buenosAires);
//		TimerTask tarea= new TareaProgramada(usuarios);
//		EjecutorDeTarea ejecutorTarea= new EjecutorDeTarea(tarea, new Date(), 30, TimeUnit.SECONDS);
//		ejecutorTarea.start();
//		TimeUnit.SECONDS.sleep(120);
//		ejecutorTarea.stop();
//		Assert.assertTrue(irAlGym.tengoSugerencias());
//	}
}