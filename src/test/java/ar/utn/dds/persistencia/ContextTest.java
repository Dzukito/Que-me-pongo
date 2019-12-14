package ar.utn.dds.persistencia;

import ar.utn.dds.modelo.clases.*;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.TipoPrenda;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.utn.dds.db.EntityManagerHelper;


import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}
	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}
	@Test
    public void persistirMembreciaTest(){
        Membrecia membreciaGratuito = new Gratuito();
        membreciaGratuito.setDescripcion("Gratuito");
        
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(membreciaGratuito);
        EntityManagerHelper.commit();
        
        Membrecia membreciaPremium = new Premium();
        membreciaPremium.setDescripcion("Premium");
        
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(membreciaPremium);
        EntityManagerHelper.commit();
    }
    @Test
    public void recuperandoMembrecia(){
        Gratuito gratuito = (Gratuito) EntityManagerHelper.createQuery("from Membrecia where descripcion = 'Gratuito'").getSingleResult();
        Assert.assertEquals("Gratuito", gratuito.getDescripcion());
        
        Premium premium = (Premium) EntityManagerHelper.createQuery("from Membrecia where descripcion = 'Premium'").getSingleResult();
        Assert.assertEquals("Premium", premium.getDescripcion());
    }
    @Test
    public void persistirUsuarios(){
        Usuario aroco = new Usuario();
            aroco.setNombre("Alejandro");
            aroco.setApellido("Roco");
            aroco.setMail("aroco@dds.com");
            aroco.setPassword("123456");
            aroco.setSexo("masculino");
            aroco.setTelefono(01123427701);
            aroco.setUserName("aroco");
            aroco.setMembrecia(new Gratuito());

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(aroco);
        EntityManagerHelper.commit();

        Usuario jazul = new Usuario();
            jazul.setNombre("Julieta");
            jazul.setApellido("Azul");
            jazul.setMail("jazul@dds.com");
            jazul.setPassword("123456");
            jazul.setSexo("femenino");
            jazul.setTelefono(01123427701);
            jazul.setUserName("jazul");
            jazul.setMembrecia(new Premium());

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(jazul);
        EntityManagerHelper.commit();
    }
    @Test
    public void recuperandoUsuario(){
        Usuario aroco = (Usuario) EntityManagerHelper.createQuery("from usuario where nombreUsuario = 'aroco'").getSingleResult();
        Assert.assertEquals("aroco", aroco.getUserName());

        Usuario jazul = (Usuario) EntityManagerHelper.createQuery("from usuario where nombreUsuario = 'jazul'").getSingleResult();
        Assert.assertEquals("jazul", jazul.getUserName());
    }
    @Test
    public void persistirGuardaropas(){
        Usuario aroco = (Usuario) EntityManagerHelper.createQuery("from usuario where nombreUsuario = 'aroco'").getSingleResult();
        Usuario jazul = (Usuario) EntityManagerHelper.createQuery("from usuario where nombreUsuario = 'jazul'").getSingleResult();
        Guardaropa roperoAroco = new Guardaropa();
            roperoAroco.setNombre("aroco");
            roperoAroco.setDescripcion("casoPruebaGratuito");
        aroco.agregarRopero(roperoAroco);
        Guardaropa roperoJazul = new Guardaropa();
            roperoJazul.setNombre("aroco");
            roperoJazul.setDescripcion("casoPruebaGratuito");
        jazul.agregarRopero(roperoJazul);
    }
    @Test
    public void recuperandoGuardaropas(){
        Guardaropa aroco = (Guardaropa) EntityManagerHelper.createQuery(
                "from guardaropa" +
                        " join map_usuario_guardaropa on map_usuario_guardaropa.id_guardaropa = guardaropa.id_guardaropa" +
                        " join usuario on usuario.id_usuario = map_usuario_guardaropa.id_usuario" +
                "where usuario.id_usuario = 1 and guardaropa.id_guardaropa = 1"
        ).getSingleResult();
        Assert.assertEquals("aroco", aroco.getNombre());

        Guardaropa jazul = (Guardaropa) EntityManagerHelper.createQuery(
                "from guardaropa" +
                        " join map_usuario_guardaropa on map_usuario_guardaropa.id_guardaropa = guardaropa.id_guardaropa" +
                        " join usuario on usuario.id_usuario = map_usuario_guardaropa.id_usuario" +
                        "where usuario.id_usuario = 1 and guardaropa.id_guardaropa = 1"
        ).getSingleResult();
        Assert.assertEquals("jazul", jazul.getNombre());
    }
    @Test
    public void persistirTipoDePrenda(){
        TipoPrenda tipo1 = new TipoPrenda();
        tipo1.setFotografo(new Fotografo());
        tipo1.setTipo("Remera cuello rendondo manga corta");
        tipo1.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo1);
        EntityManagerHelper.commit();

        TipoPrenda tipo2 = new TipoPrenda();
        tipo1.setFotografo(new Fotografo());
        tipo1.setTipo("Remera cuello rendondo manga larga");
        tipo1.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo2);
        EntityManagerHelper.commit();

        TipoPrenda tipo3 = new TipoPrenda();
        tipo1.setFotografo(new Fotografo());
        tipo1.setTipo("Remera escote V manga corta");
        tipo1.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo3);
        EntityManagerHelper.commit();

        TipoPrenda tipo4 = new TipoPrenda();
        tipo1.setFotografo(new Fotografo());
        tipo1.setTipo("Remera escote V manga larga");
        tipo1.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo4);
        EntityManagerHelper.commit();

        TipoPrenda tipo5 = new TipoPrenda();
        tipo1.setFotografo(new Fotografo());
        tipo1.setTipo("Sueter");
        tipo1.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo5);
        EntityManagerHelper.commit();

        TipoPrenda tipo6 = new TipoPrenda();
        tipo1.setFotografo(new Fotografo());
        tipo1.setTipo("Campera");
        tipo1.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo6);
        EntityManagerHelper.commit();

        TipoPrenda tipo7 = new TipoPrenda();
        tipo2.setFotografo(new Fotografo());
        tipo2.setTipo("Pantalon Largo");
        tipo2.setCategoria(Categoria.PARTEINFERIOR);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo7);
        EntityManagerHelper.commit();

        TipoPrenda tipo8 = new TipoPrenda();
        tipo2.setFotografo(new Fotografo());
        tipo2.setTipo("Pantalon Corto");
        tipo2.setCategoria(Categoria.PARTEINFERIOR);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo8);
        EntityManagerHelper.commit();

        TipoPrenda tipo9 = new TipoPrenda();
        tipo2.setFotografo(new Fotografo());
        tipo2.setTipo("Bermuda");
        tipo2.setCategoria(Categoria.PARTEINFERIOR);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo9);
        EntityManagerHelper.commit();

        TipoPrenda tipo10 = new TipoPrenda();
        tipo2.setFotografo(new Fotografo());
        tipo2.setTipo("Pollera");
        tipo2.setCategoria(Categoria.PARTEINFERIOR);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo10);
        EntityManagerHelper.commit();

        TipoPrenda tipo11 = new TipoPrenda();
        tipo2.setFotografo(new Fotografo());
        tipo2.setTipo("Calza");
        tipo2.setCategoria(Categoria.PARTEINFERIOR);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo11);
        EntityManagerHelper.commit();

        TipoPrenda tipo12 = new TipoPrenda();
        tipo2.setFotografo(new Fotografo());
        tipo2.setTipo("Buzo");
        tipo2.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo12);
        EntityManagerHelper.commit();

        TipoPrenda tipo13 = new TipoPrenda();
        tipo2.setFotografo(new Fotografo());
        tipo2.setTipo("Musculoza");
        tipo2.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo13);
        EntityManagerHelper.commit();

        TipoPrenda tipo14 = new TipoPrenda();
        tipo5.setFotografo(new Fotografo());
        tipo5.setTipo("Zapatillas");
        tipo5.setCategoria(Categoria.CALZADO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo14);
        EntityManagerHelper.commit();

        TipoPrenda tipo15 = new TipoPrenda();
        tipo5.setFotografo(new Fotografo());
        tipo5.setTipo("Zapatos");
        tipo5.setCategoria(Categoria.CALZADO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo15);
        EntityManagerHelper.commit();

        TipoPrenda tipo16 = new TipoPrenda();
        tipo5.setFotografo(new Fotografo());
        tipo5.setTipo("Sandalias");
        tipo5.setCategoria(Categoria.CALZADO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo16);
        EntityManagerHelper.commit();

        TipoPrenda tipo17 = new TipoPrenda();
        tipo3.setFotografo(new Fotografo());
        tipo3.setTipo(Categoria.MANOS.getCategoria());
        tipo3.setCategoria(Categoria.MANOS);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo3);
        EntityManagerHelper.commit();

        TipoPrenda tipo18 = new TipoPrenda();
        tipo4.setFotografo(new Fotografo());
        tipo4.setTipo("Bufanda");
        tipo4.setCategoria(Categoria.CUELLO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo4);
        EntityManagerHelper.commit();

        TipoPrenda tipo19 = new TipoPrenda();
        tipo6.setFotografo(new Fotografo());
        tipo6.setTipo("Gorro");
        tipo6.setCategoria(Categoria.CABEZA);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo6);
        EntityManagerHelper.commit();

        TipoPrenda tipo20 = new TipoPrenda();
        tipo7.setFotografo(new Fotografo());
        tipo7.setTipo(Categoria.ACCESORIOS.getCategoria());
        tipo7.setCategoria(Categoria.ACCESORIOS);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo7);
        EntityManagerHelper.commit();
    }
    @Test
    public void recuperandoTipoDePrenda(){
        TipoPrenda tipo1 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 1").getSingleResult();
        Assert.assertEquals("Remera cuello rendondo manga corta", tipo1.getTipo());

        TipoPrenda tipo2 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 2").getSingleResult();
        Assert.assertEquals("Remera cuello rendondo manga larga", tipo2.getTipo());

        TipoPrenda tipo3 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 3").getSingleResult();
        Assert.assertEquals("Remera escote V manga corta", tipo3.getTipo());

        TipoPrenda tipo4 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 4").getSingleResult();
        Assert.assertEquals("Remera escote V manga larga", tipo4.getTipo());

        TipoPrenda tipo5 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 5").getSingleResult();
        Assert.assertEquals("Sueter", tipo5.getTipo());

        TipoPrenda tipo6 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 6").getSingleResult();
        Assert.assertEquals("Campera", tipo6.getTipo());

        TipoPrenda tipo7 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 7").getSingleResult();
        Assert.assertEquals("Pantalon Largo", tipo7.getTipo());

        TipoPrenda tipo8 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 8").getSingleResult();
        Assert.assertEquals("Pantalon Corto", tipo8.getTipo());

        TipoPrenda tipo9 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 9").getSingleResult();
        Assert.assertEquals("Bermuda", tipo9.getTipo());
        TipoPrenda tipo10 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 10").getSingleResult();
        Assert.assertEquals("Pollera", tipo10.getTipo());
        TipoPrenda tipo11 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 11").getSingleResult();
        Assert.assertEquals("Calza", tipo11.getTipo());
        TipoPrenda tipo12 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 12").getSingleResult();
        Assert.assertEquals("Buzo", tipo12.getTipo());
        TipoPrenda tipo13 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 13").getSingleResult();
        Assert.assertEquals("Musculoza", tipo13.getTipo());
        TipoPrenda tipo14 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 14").getSingleResult();
        Assert.assertEquals("Zapatillas", tipo14.getTipo());
        TipoPrenda tipo15 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 15").getSingleResult();
        Assert.assertEquals("Zapatos", tipo15.getTipo());
        TipoPrenda tipo16 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 16").getSingleResult();
        Assert.assertEquals("Sandalias", tipo16.getTipo());
        TipoPrenda tipo17 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 17").getSingleResult();
        Assert.assertEquals("Manos", tipo17.getTipo());
        TipoPrenda tipo18 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 18").getSingleResult();
        Assert.assertEquals("Bufanda", tipo18.getTipo());
        TipoPrenda tipo19 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 19").getSingleResult();
        Assert.assertEquals("Gorro", tipo19.getTipo());
        TipoPrenda tipo20 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 20").getSingleResult();
        Assert.assertEquals(Categoria.ACCESORIOS.getCategoria(), tipo1.getTipo());
        }
    @Test
    public void persistirPrendasSuperponibles(){
        TipoPrenda tipo1 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 1").getSingleResult();
        TipoPrenda tipo2 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 2").getSingleResult();
        TipoPrenda tipo3 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 3").getSingleResult();
        TipoPrenda tipo4 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 4").getSingleResult();
        TipoPrenda tipo5 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 5").getSingleResult();
        TipoPrenda tipo6 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 6").getSingleResult();
        TipoPrenda tipo7 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 7").getSingleResult();
        TipoPrenda tipo8 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 8").getSingleResult();
        TipoPrenda tipo9 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 9").getSingleResult();
        TipoPrenda tipo10 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 10").getSingleResult();
        TipoPrenda tipo11 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 11").getSingleResult();
        TipoPrenda tipo12 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 12").getSingleResult();
        TipoPrenda tipo13 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 13").getSingleResult();
        TipoPrenda tipo14 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 14").getSingleResult();
        TipoPrenda tipo15 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 15").getSingleResult();
        TipoPrenda tipo16 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 16").getSingleResult();
        TipoPrenda tipo17 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 17").getSingleResult();
        TipoPrenda tipo18 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 18").getSingleResult();
        TipoPrenda tipo19 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 19").getSingleResult();
        TipoPrenda tipo20 = (TipoPrenda) EntityManagerHelper.createQuery("from tipoPrenda where id_tipoPrenda = 20").getSingleResult();
        tipo1.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12)));
        tipo2.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12)));
        tipo3.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12)));
        tipo4.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12)));
        tipo5.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo7,tipo6)));
        tipo6.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11)));
        tipo7.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6,tipo13)));
        tipo8.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        tipo9.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        tipo10.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        tipo11.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        tipo12.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo7,tipo8,tipo9,tipo10,tipo13)));
        tipo13.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo6,tipo8,tipo10,tipo11,tipo12)));
        tipo14.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12,tipo13,tipo14,tipo15,tipo16)));
        tipo15.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo7,tipo10)));
        tipo16.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo7,tipo8,tipo10)));
    }
    @Test
    public void recuperandoPrendasSuperpobibles(){}
    @Test
    public void persistirPrendas(){}
    @Test
    public void recuperandoPrendas(){}
    @Test
    public void persistirAtuendos(){}
    @Test
    public void recuperandoAtuendos(){}
    @Test
    public void persistirConjuntosPredefinidos(){}
    @Test
    public void recuperandoConjuntosPredefinidos(){}
    @Test
    public void persistirEventos(){}
    @Test
    public void recuperandoEventos(){}
}