package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.clima.Pronostico;
import ar.utn.dds.modelo.interfaces.Meteorologo;
import ar.utn.dds.modelo.ropa.*;
import ar.utn.dds.modelo.clima.NivelDeCalor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;
@DisplayName("Test de la clase Guardaropa")
public class GuardaropaTest {
	Ubicacion buenosAires;
    Calendar fechaDeHoy, fechaDeHoyMas1Hora, fechaDeHoyMas2Hora, fechaDeHoyMenos1Hora, fechaDeHoyMenos2Hora,pasadomaniana,maniana,ayer,dosDiasAntes;
    Evento irATrabajar, irAlGym, entreCasa, irALaFacu;
    HashSet<TipoPrenda> superponibleCalzon;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda tipoPantalon, tipoRemera, tipoZapatillas, tipoAccesorio, tipoTop, tipoCuello, tipoZapatos, tipoPantalonCorto, tipoManos, tipoCabeza;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2;
    ArrayList<Prenda> prendas1, prendas2, prendas3, prendas4;
    Guardaropa ropero1, ropero2, ropero3;
    ArrayList<NivelDeCalor> nivelDeCalor1, nivelDeCalor2;
    Pronostico templado;
    Usuario usuario1;
    HashSet<TipoPrenda> superponiblesTodos;
    @Before
    public void setup() {
        //Material tipoPrenda
    	materialRemera = new HashSet<Material>(Arrays.asList(Material.LINO,Material.FRANELA,Material.ALGODON));
        materialPantalon = new HashSet<Material>(Arrays.asList(Material.MALLA,Material.JEAN,Material.CUERO));
        materialCalzado = new HashSet<Material>(Arrays.asList(Material.CUERO,Material.PLASTICO));
        materialAccesorio = new HashSet<Material>(Arrays.asList(Material.PLASTICO,Material.ACEROINOXIDABLE,Material.CUERO));
        //Tipo de prenda
        tipoRemera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        tipoPantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
        tipoPantalonCorto = new TipoPrenda(Categoria.PARTEINFERIOR, "PantalonCorto", materialPantalon);
        tipoTop = new TipoPrenda(Categoria.TORSO, "Top", materialRemera);
        tipoZapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        tipoZapatos = new TipoPrenda(Categoria.CALZADO, "Zapatos", materialCalzado);
        tipoAccesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
        tipoCuello = new TipoPrenda(Categoria.CUELLO, "Bufanda", materialAccesorio);
        tipoManos = new TipoPrenda(Categoria.MANOS, "Guantes", materialAccesorio);
        tipoCabeza = new TipoPrenda(Categoria.CABEZA, "Guantes", materialAccesorio);

        superponiblesTodos = new HashSet<TipoPrenda>();
        superponiblesTodos.add(tipoRemera);
        superponiblesTodos.add(tipoPantalon);
        superponiblesTodos.add(tipoPantalonCorto);
        superponiblesTodos.add(tipoTop);
        superponiblesTodos.add(tipoZapatillas);
        superponiblesTodos.add(tipoZapatos);
        superponiblesTodos.add(tipoAccesorio);
        superponiblesTodos.add(tipoCuello);
        superponiblesTodos.add(tipoManos);
        superponiblesTodos.add(tipoCabeza);
        tipoRemera.setSuperponibles(superponiblesTodos);
        tipoPantalon.setSuperponibles(superponiblesTodos);
        tipoPantalonCorto.setSuperponibles(superponiblesTodos);
        tipoTop.setSuperponibles(superponiblesTodos);
        tipoZapatillas.setSuperponibles(superponiblesTodos);
        tipoZapatos.setSuperponibles(superponiblesTodos);
        tipoAccesorio.setSuperponibles(superponiblesTodos);
        tipoCuello.setSuperponibles(superponiblesTodos);
        tipoManos.setSuperponibles(superponiblesTodos);
        tipoCabeza.setSuperponibles(superponiblesTodos);


        //Prendas
        remera1 = new Prenda(tipoRemera, "RemeraDePandas", Color.Blanco,Color.Negro , Material.LINO);
        remera1.setEstilo(Estilo.ENTRECASA);
        remera2 = new Prenda(tipoTop, "Top", Color.Blanco,Color.Negro, Material.ALGODON);
        remera2.setEstilo(Estilo.ENTRECASA);
        pantalon1 = new Prenda(tipoPantalon, "Pantalon1", Color.Azul, Material.JEAN);
        pantalon1.setEstilo(Estilo.ENTRECASA);
        pantalon2 = new Prenda(tipoPantalonCorto, "PantalonCorto", Color.Amarillo, Material.JEAN);
        pantalon2.setEstilo(Estilo.ENTRECASA);
        zapatillas1 = new Prenda(tipoZapatillas, "Zapatillas1",Color.Rojo,Color.Verde, Material.CUERO);
        zapatillas1.setEstilo(Estilo.ENTRECASA);
        zapatillas2 = new Prenda(tipoZapatos, "Zapatos", Color.Blanco,Color.Negro, Material.CUERO);
        zapatillas2.setEstilo(Estilo.ENTRECASA);
        accesorio1 = new Prenda(tipoAccesorio, "Gorra", Color.Azul, Material.PLASTICO);
        accesorio1.setEstilo(Estilo.ENTRECASA);
        accesorio2 = new Prenda(tipoCuello, "Bufanda", Color.Amarillo, Material.ACEROINOXIDABLE);
        accesorio2.setEstilo(Estilo.ENTRECASA);

        //Lista de prendas
        prendas1 = new ArrayList<Prenda>(Arrays.asList(remera1, accesorio1, zapatillas1, pantalon1));
        prendas2 = new ArrayList<Prenda>(Arrays.asList(remera2, accesorio2, zapatillas2, pantalon2));
        prendas3 = new ArrayList<Prenda>(Arrays.asList(zapatillas1,pantalon1,remera2));
        prendas4 = new ArrayList<Prenda>(Arrays.asList(remera2, accesorio2, zapatillas2, pantalon2, remera1, accesorio1, zapatillas1, pantalon1));
        //Guardaropas
        ropero1 = new Guardaropa(prendas1);
        ropero2 = new Guardaropa(prendas2);
        ropero3 = new Guardaropa(prendas3);

        //Fecha hora
        fechaDeHoyMas1Hora =  Calendar.getInstance();
        fechaDeHoyMas2Hora = Calendar.getInstance();
        fechaDeHoyMenos1Hora =  Calendar.getInstance();
        fechaDeHoyMenos2Hora =  Calendar.getInstance();
        fechaDeHoyMas1Hora.add(Calendar.HOUR, 1);
        fechaDeHoyMas2Hora.add(Calendar.HOUR, 2);
        fechaDeHoyMenos1Hora.add(Calendar.HOUR, -1);
        fechaDeHoyMenos2Hora.add(Calendar.HOUR,-2);
        fechaDeHoy = Calendar.getInstance();
        //Ubicación
        buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
        //Evento
        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.DEPORTIVO);
        irATrabajar = new Evento(fechaDeHoyMenos1Hora,fechaDeHoyMas1Hora,buenosAires,Estilo.ELEGANTE);
        irALaFacu = new Evento(pasadomaniana,pasadomaniana,buenosAires,Estilo.ELEGANTSPORT);
        entreCasa = new Evento(fechaDeHoyMenos2Hora,fechaDeHoy,buenosAires,Estilo.NORMAL);
        usuario1 = new Usuario("Martin", new ArrayList<Guardaropa>());
    }
    @Test
    @DisplayName("Verifica que se agregue un usuario al guardaropas")
    public void agregarUsuario(){
        setup();
        Usuario usuarioNuevo = new Usuario("juan",new ArrayList<Guardaropa>());
        ropero1.agregarUsuario(usuarioNuevo);
        Assert.assertEquals(1, ropero1.getUsuarios().size());
    }
    @Test
    @DisplayName("Verifica que se quite un especifico usuario al guardaropas")
    public void quitarUsuario(){
        setup();
        Usuario usuarioNuevo = new Usuario("UsuarioNuevo",new ArrayList<Guardaropa>());
        ropero1.agregarUsuario(usuarioNuevo);
        ropero1.quitarUsuario(usuarioNuevo);
        Assert.assertEquals(0, ropero1.getUsuarios().size());
    }
    @Test
    @DisplayName("Verifica que se genere un atuendo que satisfaga aleatorio")
    public void generarAtuendoSinEvento(){
        Atuendo atuendoSugerido = ropero2.sugerirAtuendoSinEvento();
        atuendoSugerido.getPrendas().forEach(prenda -> System.out.println(prenda.getCategoria()));
        Assert.assertEquals(Atuendo.class,atuendoSugerido.getClass());
    }
    @Test
    @DisplayName("Verifica que se genere un atuendo que satisfaga las necesidades del pronostico, del evento y las sensibilidades del usuario")
    public void sugerirAtuendoPorPronosticoEventoYUsuario(){}
    @Test
    @DisplayName("Verifica que se genere un conjunto predefinido con las prendas minimas de torso, calzado y parate Inferior")
    public void conjuntoPredefinidoMinimo(){}
    @Test
    @DisplayName("Verifica que se genere un conjunto predefinido aleatorio")
    public void conjuntoPredefinidoPosibles(){}
    @Test
    @DisplayName("Verifica que se genere un atuendo que satisfaga las necesidades del pronostico, del evento y las sensibilidades del usuario")
    public void conjuntoPredefinidoTipico(){}
    @Test
    @DisplayName("Verifica que se genere un atuendo que satisfaga las necesidades del pronostico, del evento y las sensibilidades del usuario")
    public void llenadoConjuntoPredefinido(){}
    @Test
    @DisplayName("Verifica que se genere un atuendo que satisfaga las necesidades del pronostico, del evento y las sensibilidades del usuario")
    public void atuendoCoincide(){}
    @Test
    @DisplayName("Verifica que se genere un atuendo que satisfaga las necesidades del pronostico, del evento y las sensibilidades del usuario")
    public void generacionDeUnAtuendoConsiderandoUnConjuntoPredefinidoSinUtilizarlo(){}

}