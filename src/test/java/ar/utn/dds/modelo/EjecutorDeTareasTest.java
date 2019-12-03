package ar.utn.dds.modelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class EjecutorDeTareasTest {
	 Ubicacion buenosAires;
	    Calendar fechaDeHoy, fechaDeHoyMas2Hora;
	    Evento irATrabajar, irAlGym, irALaFacu;
	    Usuario usuario1;
	    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio, materialParaguas, materialLentes;
	    TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos, paraguas,lentes;
	    ArrayList<Color> blancoYNegro, azul, amarillo, rojoYVerde;
	    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2, accesorio3,accesorio4;
	    ArrayList<Prenda> prendas1, prendas2, prendas3;
	    Guardaropa ropero1, ropero2, ropero3;
	    Meteorologo meteorologo;
	    ArrayList<Usuario> usuarios;
	    @Before
	    public void setup(){
	        buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
	        fechaDeHoyMas2Hora = Calendar.getInstance();
	        fechaDeHoyMas2Hora.add(Calendar.HOUR, 7);
	        fechaDeHoy = Calendar.getInstance();
	        fechaDeHoy.add(Calendar.HOUR, 2);
	        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires,Estilo.DEPORTIVO);
	        materialRemera = new HashSet<Material>();
	        materialRemera.add(Material.LINO);
	        materialPantalon = new HashSet<Material>();
	        materialPantalon.add(Material.LINO);
	        materialCalzado = new HashSet<Material>();
	        materialCalzado.add(Material.LINO);
	        materialAccesorio = new HashSet<Material>();
	        materialAccesorio.add(Material.LINO);
	        materialParaguas = new HashSet<Material>();
	        materialParaguas.add(Material.PLASTICO);
	        materialLentes = new HashSet<Material>();
	        materialLentes.add(Material.PLASTICO);
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
	        blancoYNegro = new ArrayList<Color>();
	        blancoYNegro.add(Color.Blanco);
	        blancoYNegro.add(Color.Negro);
	        azul = new ArrayList<Color>();
	        azul.add(Color.Azul);
	        amarillo = new ArrayList<Color>();
	        amarillo.add(Color.Amarillo);
	        rojoYVerde = new ArrayList<Color>();
	        rojoYVerde.add(Color.Rojo);
	        rojoYVerde.add(Color.Verde);
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
	        prendas1 = new ArrayList<Prenda>();
	        prendas1.add(remera1);
	        prendas1.add(accesorio1);
	        prendas1.add(zapatillas1);
	        prendas1.add(pantalon1);
	        prendas1.add(accesorio3);
	        prendas1.add(accesorio4);
	        prendas2 = new ArrayList<Prenda>();
	        prendas2.add(remera1);
	        prendas2.add(accesorio1);
	        prendas2.add(zapatillas1);
	        prendas2.add(pantalon1);
	        prendas2.add(remera2);
	        prendas2.add(accesorio2);
	        prendas2.add(zapatillas2);
	        prendas2.add(pantalon2);
	        prendas2.add(accesorio4);
	        prendas3 = new ArrayList<Prenda>();
	        prendas3.add(remera1);
	        prendas3.add(accesorio1);
	        prendas3.add(zapatillas1);
	        prendas3.add(pantalon1);
	        prendas3.add(remera2);
	        prendas3.add(accesorio4);
	        ropero1 = new Guardaropa();
	        ropero1.agregarPrendas(prendas1);
	        ropero2 = new Guardaropa();
	        ropero2.agregarPrendas(prendas2);
	        ropero3 = new Guardaropa();
	        ropero3.agregarPrendas(prendas3);
	        meteorologo = new MeteorologoWeatherAdapter();
	        usuario1 = new Usuario("Martin", new ArrayList<Guardaropa>());
	        usuario1.agregarRopero(ropero1);
	        usuario1.agregarRopero(ropero2);
	        usuario1.agregarRopero(ropero3);
	        usuario1.agregarEvento(irAlGym);
	        usuarios = new ArrayList<Usuario>();
	        usuarios.add(usuario1);
	      }
	    
	    @Test
	    public void test() throws InterruptedException {
	    	
	    	meteorologo.getPronosticos(buenosAires);  
	    	TimerTask tarea= new TareaProgramada(usuarios);
	    	EjecutorDeTarea ejecutorTarea= new EjecutorDeTarea(tarea, new Date(), 30, TimeUnit.SECONDS);
	    	ejecutorTarea.start();
	    	TimeUnit.SECONDS.sleep(120);
	    	ejecutorTarea.stop();
	    	Assert.assertTrue(irAlGym.tengoSugerencias());
	    }
}