package ar.utn.dds.modelo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class creacionDeTablasTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}
	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}

}
