package ar.utn.dds.persistencia.model;

import ar.utn.dds.db.EntityManagerHelper;
import ar.utn.dds.modelo.clases.Gratuito;
import ar.utn.dds.modelo.clases.Membrecia;
import ar.utn.dds.modelo.clases.Premium;
import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.Assert.assertNotNull;

public class membreciaPersistentTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

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
}
