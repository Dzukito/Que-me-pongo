package ar.utn.dds.modelo;

import org.junit.Assert;
import org.junit.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import java.util.*;


public class UsuarioTest {
    Usuario usuario1, usuario2, usuario3;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos;
    ArrayList<Color> blancoYNegro, azul, amarillo, rojoYVerde;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2;
    ArrayList<Prenda> prendas1, prendas2, prendas3;
    Guardaropa ropero1, ropero2, ropero3;

    @Before
    public void setup() {
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
          pantalonCorto = new TipoPrenda(Categoria.PARTEINFERIOR, "PantalonCorto", materialPantalon);
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
          pantalon2 = new Prenda(pantalonCorto, "PantalonCorto", Color.Amarillo, Material.JEAN);
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
        usuario1 = new Usuario("Martin", new ArrayList<Guardaropa>());
        usuario1.agregarRopero(ropero1);
        usuario2 = new Usuario("Gabriela", new ArrayList<Guardaropa>());
        usuario2.agregarRopero(ropero2);
        usuario3 = new Usuario("Gabriela", new ArrayList<Guardaropa>());
        usuario3.agregarRopero(ropero3);
    }
    @Test
    public void  compartirGuardaropa(){
        this.setup();
        usuario1.compartirGuardaropas(0,usuario2);
        Assert.assertTrue(usuario2.tengoGuardaropas(usuario1.getGuardaropa(0)));
    }

    @Test
    public void cantidadDePrendas(){
        this.setup();
        Assert.assertEquals(usuario1.cantidadPrendas(0),4);
    }
    @Test
    public void cantidadDePrendas5(){
        this.setup();
        Assert.assertEquals(usuario3.cantidadPrendas(0),5);
    }
    @Test
    public void cantidadDePrendas8(){
        this.setup();
        Assert.assertEquals(usuario2.cantidadPrendas(0),8);
    }
    @Test 
    public void cantidadDeCategorias(){
        this.setup();
        Assert.assertEquals(ropero1.cantidadCategorioas(ropero1.getPrendas()),4);
    }
    @Test
    public void pasarUsuarioDePremiumAGratuito(){
        this.setup();
    }
    @Test
    public void pasarUsuarioDeGratuitoAPremium(){
        this.setup();
    }
  /*  @Test
    public void atundosDisponiblesParaUnGuardarropa(){
        this.setup();
     
        Atuendo atuendo = usuario1.atuendosGuardaropa(ropero1).get(0);      
		Assert.assertEquals(remera1, atuendo.getPrenda(Categoria.TORSO));
		Assert.assertEquals(pantalon1, atuendo.getPrenda(Categoria.PARTEINFERIOR));
		Assert.assertEquals(zapatillas1, atuendo.getPrenda(Categoria.CALZADO));
        Assert.assertEquals(accesorio1, atuendo.getPrenda(Categoria.ACCESORIOS));
    }
*/
}