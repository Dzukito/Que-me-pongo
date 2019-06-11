package ar.utn.dds.modelo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PronosticoTest {
    Pronostico pronostico1, pronostico2, pronostico3, pronostico4;
    ArrayList<TipoClima> lluvioso,ventoso,nublado,lluviosoYVentoso;
    @Before
    public void setup(){
        lluvioso = new ArrayList<TipoClima>();
        lluvioso.add(TipoClima.LLUVIOSO);
        ventoso = new ArrayList<TipoClima>();
        ventoso.add(TipoClima.VENTOSO);
        nublado = new ArrayList<TipoClima>();
        nublado.add(TipoClima.NUBLADO);
        lluviosoYVentoso = new ArrayList<TipoClima>();
        lluviosoYVentoso.add(TipoClima.LLUVIOSO);
        lluviosoYVentoso.add(TipoClima.VENTOSO);
        pronostico1 = new Pronostico((float)1.1,lluvioso,(float)1.1);
        pronostico2 = new Pronostico((float)1.1,ventoso,(float)1.1);
        pronostico3 = new Pronostico((float)1.1,nublado,(float)1.1);
        pronostico4 = new Pronostico((float)1.1,lluviosoYVentoso,(float)1.1);
    }
    @Test
    public void prendasNegadas() {
        assertTrue(pronostico2.prendasNegadas().contains("Paraguas"));
        assertTrue(pronostico2.prendasNegadas().contains("Gorro"));
        assertTrue(pronostico2.prendasNegadas().contains("Pollera"));
    }
    @Test
    public void prendasSatisfacen() {
        assertTrue(pronostico4.prendasSatisfacen().contains("Rompevientos"));
        assertTrue(pronostico4.prendasSatisfacen().contains("Piloto"));
        assertFalse(pronostico4.prendasSatisfacen().contains("Paraguas"));
    }
}