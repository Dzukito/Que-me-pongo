package ar.utn.dds.modelo;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static ar.utn.dds.modelo.Material.*;
import static org.junit.Assert.*;

public class UsuarioTest {

    @Before
    public void init(){
        Set<Material> materialRemera = new TreeSet<>();
        materialRemera.add(LINO);
        materialRemera.add(ALGODON);
        materialRemera.add(FRANELA);
        Set<Material> materialCampera = new TreeSet<>();
        materialCampera.add(CUERO);
        materialCampera.add(GABARDINA);
        materialCampera.add(JEAN);
        Set<Material> pantalon = new TreeSet<>();
        TipoPrenda remera = new TipoPrenda("Remera", materialRemera);
    }
    @Test
    public void cantidadPrendas(){

    }

}