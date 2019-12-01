package ar.utn.dds.modelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GuardaropaTest {
    Ubicacion buenosAires;
    Calendar fechaDeHoy, fechaDeHoyMas1Hora, fechaDeHoyMas2Hora, fechaDeHoyMenos1Hora, fechaDeHoyMenos2Hora,pasadomaniana,maniana,ayer,anteAyer;
    Evento irATrabajar, irAlGym, entreCasa, irALaFacu;
    HashSet<TipoPrenda> superponibleCalzon;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda pantalon, remera, zapatillas, accesorio, top, calzon,bufanda, zapatos;
    ArrayList<Color> blancoYNegro, azul, amarillo, rojoYVerde;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2;
    ArrayList<Prenda> prendas1, prendas2, prendas3;
    Guardaropa ropero1, ropero2, ropero3;
    ArrayList<NivelDeCalor> nivelDeCalor1, nivelDeCalor2;
    @Before
    public void setup() {
        nivelDeCalor1 = new ArrayList<NivelDeCalor>( Arrays.asList(
                new NivelDeCalor(Categoria.TORSO,1),
                new NivelDeCalor(Categoria.PARTEINFERIOR,2),
                new NivelDeCalor(Categoria.CALZADO,1)));

        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialRemera.add(Material.FRANELA);
        materialRemera.add(Material.ALGODON);
        materialPantalon = new HashSet<Material>();
        materialPantalon.add(Material.MALLA);
        materialPantalon.add(Material.JEAN);
        materialPantalon.add(Material.CUERO);
        materialCalzado = new HashSet<Material>();
        materialCalzado.add(Material.CUERO);
        materialCalzado.add(Material.PLASTICO);
        materialAccesorio = new HashSet<Material>();
        materialAccesorio.add(Material.PLASTICO);
        materialAccesorio.add(Material.ACEROINOXIDABLE);
        materialAccesorio.add(Material.CUERO);
        remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
   
        top = new TipoPrenda(Categoria.TORSO, "Top", materialRemera);
        zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        zapatos = new TipoPrenda(Categoria.CALZADO, "Zapatos", materialCalzado);
        accesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
        bufanda = new TipoPrenda(Categoria.ACCESORIOS, "Bufanda", materialAccesorio);
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
        remera1 = new Prenda(remera, "RemeraDePandas", Color.Blanco,Color.Negro , Material.LINO);
        remera2 = new Prenda(top, "Top", Color.Blanco,Color.Negro, Material.ALGODON);
        pantalon1 = new Prenda(pantalon, "Pantalon1", Color.Azul, Material.JEAN);
     
        zapatillas1 = new Prenda(zapatillas, "Zapatillas1",Color.Rojo,Color.Verde, Material.CUERO);
        zapatillas2 = new Prenda(zapatos, "Zapatos", Color.Blanco,Color.Negro, Material.CUERO);
        accesorio1 = new Prenda(accesorio, "Gorra", Color.Azul, Material.PLASTICO);
        accesorio2 = new Prenda(bufanda, "Bufanda", Color.Amarillo, Material.ACEROINOXIDABLE);
        prendas1 = new ArrayList<Prenda>();
        prendas1.add(remera1);
        prendas1.add(accesorio1);
        prendas1.add(zapatillas1);
        prendas1.add(pantalon1);
        prendas2 = new ArrayList<Prenda>();
        prendas2.add(remera2);
        prendas2.add(accesorio2);
        prendas2.add(zapatillas2);
        prendas2.add(pantalon2);
        prendas3 = new ArrayList<Prenda>();
        prendas3.add(zapatillas1);
        prendas3.add(pantalon1);
        prendas3.add(remera2);
        ropero1 = new Guardaropa(prendas1);
        ropero2 = new Guardaropa(prendas2);
        ropero3 = new Guardaropa(prendas3);
        buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
        pasadomaniana = Calendar.getInstance();
        maniana = Calendar.getInstance();
        ayer = Calendar.getInstance();
        anteAyer = Calendar.getInstance();
        pasadomaniana.add(Calendar.DAY_OF_YEAR,2);
        maniana.add(Calendar.DAY_OF_YEAR,1);
        anteAyer.add(Calendar.DAY_OF_YEAR,-2);
        ayer.add(Calendar.DAY_OF_YEAR,-1);
        fechaDeHoyMas1Hora =  Calendar.getInstance();
        fechaDeHoyMas2Hora = Calendar.getInstance();
        fechaDeHoyMenos1Hora =  Calendar.getInstance();
        fechaDeHoyMenos2Hora =  Calendar.getInstance();
        fechaDeHoyMas1Hora.add(Calendar.HOUR, 1);
        fechaDeHoyMas2Hora.add(Calendar.HOUR, 2);
        fechaDeHoyMenos1Hora.add(Calendar.HOUR, -1);
        fechaDeHoyMenos2Hora.add(Calendar.HOUR,-2);
        fechaDeHoy = Calendar.getInstance();
        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires,Estilo.DEPORTIVO);
        irATrabajar = new Evento(fechaDeHoyMenos1Hora,fechaDeHoyMas1Hora,buenosAires,Estilo.ELEGANTE);
        irALaFacu = new Evento(pasadomaniana,pasadomaniana,buenosAires,Estilo.ELEGANTSPORT);
        entreCasa = new Evento(fechaDeHoyMenos2Hora,fechaDeHoy,buenosAires,Estilo.NORMAL);
    }

    @Test
    public void agregarUsuario(){
        setup();
        Usuario usuarioNuevo = new Usuario("UsuarioNuevo",new ArrayList<Guardaropa>());
        ropero1.agregarUsuario(usuarioNuevo);
        Assert.assertEquals(1, ropero1.getUsuarios().size());
    }
    @Test
    public void quitarUsuario(){
        setup();
        Usuario usuarioNuevo = new Usuario("UsuarioNuevo",new ArrayList<Guardaropa>());
        ropero1.agregarUsuario(usuarioNuevo);
        ropero1.quitarUsuario(usuarioNuevo);
        Assert.assertEquals(0, ropero1.getUsuarios().size());
    }
    @Test
    public void prendasYaUsadasPorOtro(){

    }
}