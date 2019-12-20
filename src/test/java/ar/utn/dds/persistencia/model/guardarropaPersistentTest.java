package ar.utn.dds.persistencia.model;

import ar.utn.dds.db.EntityManagerHelper;
import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.modelo.clases.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.Assert.assertNotNull;

public class guardarropaPersistentTest  extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }
    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {});
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
}
