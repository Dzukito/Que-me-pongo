package ar.utn.dds.persistencia.model;

import ar.utn.dds.db.EntityManagerHelper;
import ar.utn.dds.modelo.ropa.TipoPrenda;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class superponiblesPersistentTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }
    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {});
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
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo1);
        EntityManagerHelper.commit();
        tipo2.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo2);
        EntityManagerHelper.commit();
        tipo3.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo3);
        EntityManagerHelper.commit();
        tipo4.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo4);
        EntityManagerHelper.commit();
        tipo5.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo7,tipo6)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo5);
        EntityManagerHelper.commit();
        tipo6.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo6);
        EntityManagerHelper.commit();
        tipo7.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6,tipo13)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo7);
        EntityManagerHelper.commit();
        tipo8.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo8);
        EntityManagerHelper.commit();
        tipo9.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo9);
        EntityManagerHelper.commit();
        tipo10.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo10);
        EntityManagerHelper.commit();
        tipo11.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo6,tipo13)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo11);
        EntityManagerHelper.commit();
        tipo12.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo7,tipo8,tipo9,tipo10,tipo13)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo12);
        EntityManagerHelper.commit();
        tipo13.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo6,tipo8,tipo10,tipo11,tipo12)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo13);
        EntityManagerHelper.commit();
        tipo14.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6,tipo7,tipo8,tipo9,tipo10,tipo11,tipo12,tipo13,tipo14,tipo15,tipo16)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo14);
        EntityManagerHelper.commit();
        tipo15.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo7,tipo10)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo15);
        EntityManagerHelper.commit();
        tipo16.setSuperponibles((Set<TipoPrenda>)new ArrayList<TipoPrenda>(Arrays.asList(tipo7,tipo8,tipo10)));
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipo16);
        EntityManagerHelper.commit();
    }
    @Test
    public void recuperandoPrendasSuperpobibles(){}


}
