package ar.utn.dds.persistencia;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.utn.dds.db.EntityManagerHelper;


import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

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
    public void persistirTipoDePrenda(){
        TipoPrenda tipo1 = new TipoPrenda();
        tipo1.setTipo(Categoria.TORSO.getCategoria());
        tipo1.setCategoria(Categoria.TORSO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo1);
        EntityManagerHelper.commit();
        
        TipoPrenda tipo2 = new TipoPrenda();
        tipo2.setTipo(Categoria.PARTEINFERIOR.getCategoria());
        tipo2.setCategoria(Categoria.PARTEINFERIOR);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo2);
        EntityManagerHelper.commit();
        
        TipoPrenda tipo3 = new TipoPrenda();
        tipo3.setTipo(Categoria.MANOS.getCategoria());
        tipo3.setCategoria(Categoria.MANOS);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo3);
        EntityManagerHelper.commit();
        
        TipoPrenda tipo4 = new TipoPrenda();
        tipo4.setTipo(Categoria.CUELLO.getCategoria());
        tipo4.setCategoria(Categoria.CUELLO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo4);
        EntityManagerHelper.commit();
        
        TipoPrenda tipo5 = new TipoPrenda();
        tipo5.setTipo(Categoria.CALZADO.getCategoria());
        tipo5.setCategoria(Categoria.CALZADO);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo5);
        EntityManagerHelper.commit();
        
        TipoPrenda tipo6 = new TipoPrenda();
        tipo6.setTipo(Categoria.CABEZA.getCategoria());
        tipo6.setCategoria(Categoria.CABEZA);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo6);
        EntityManagerHelper.commit();
        
        TipoPrenda tipo7 = new TipoPrenda();
        tipo7.setTipo(Categoria.ACCESORIOS.getCategoria());
        tipo7.setCategoria(Categoria.ACCESORIOS);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo7);
        EntityManagerHelper.commit();

    
    }
}