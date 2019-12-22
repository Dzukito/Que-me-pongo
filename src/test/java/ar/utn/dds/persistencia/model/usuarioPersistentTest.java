package ar.utn.dds.persistencia.model;

import ar.utn.dds.db.EntityManagerHelper;
import ar.utn.dds.modelo.clases.Gratuito;
import ar.utn.dds.modelo.clases.Premium;
import ar.utn.dds.modelo.clases.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.Assert.assertNotNull;

public class usuarioPersistentTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }
    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {});
    }
    @Test
    public void persistirUsuarios(){
        Usuario aroco = new Usuario();
        Gratuito gratuito = (Gratuito) EntityManagerHelper.createQuery("from Membrecia where descripcion = 'Gratuito'").getSingleResult();
        aroco.setNombre("Alejandro");
        aroco.setApellido("Roco");
        aroco.setMail("aroco@dds.com");
        aroco.setPassword("123456");
        aroco.setSexo("mascuLINO");
        aroco.setTelefono(01123427701);
        aroco.setUserName("aroco");
        aroco.setMembrecia(gratuito);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(aroco);
        EntityManagerHelper.commit();

        Usuario jazul = new Usuario();
        Premium premium = (Premium) EntityManagerHelper.createQuery("from Membrecia where descripcion = 'Premium'").getSingleResult();
        jazul.setNombre("Julieta");
        jazul.setApellido("Azul");
        jazul.setMail("jazul@dds.com");
        jazul.setPassword("123456");
        jazul.setSexo("femenino");
        jazul.setTelefono(01123427701);
        jazul.setUserName("jazul");
        jazul.setMembrecia(premium);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(jazul);
        EntityManagerHelper.commit();
    }

    @Test
    public void recuperandoUsuario(){
//        Usuario aroco = (Usuario) entityManager().createQuery("SELECT * from usuario where id_usuario = :id").
//                setParameter("id", 1).
//                getSingleResult();
//        Assert.assertEquals("aroco", aroco.getUserName());

        Usuario jazul = (Usuario) EntityManagerHelper.createQuery("from usuario where id_usuario = '2'").getSingleResult();
        Assert.assertEquals("jazul", jazul.getUserName());
    }
}
