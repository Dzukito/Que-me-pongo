package ar.utn.dds.modelo.clima;

import ar.utn.dds.modelo.clima.Pronostico;
import ar.utn.dds.modelo.clima.TipoClima;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
@DisplayName("Test de la clase pronostico")
public class PronosticoTest {
    Pronostico pronostico1, pronostico2, pronostico3, pronostico4, pronostico5,muyFrio,muyCaluroso,frio,caluroso,templado;
    ArrayList<TipoClima> lluvioso,ventoso,nublado,lluviosoYVentoso;
    @Before
    public void setup(){
        lluvioso = new ArrayList<TipoClima>(Arrays.asList(TipoClima.LLUVIOSO));
        ventoso = new ArrayList<TipoClima>(Arrays.asList(TipoClima.VENTOSO));
        nublado = new ArrayList<TipoClima>(Arrays.asList(TipoClima.NUBLADO));
        lluviosoYVentoso = new ArrayList<TipoClima>(Arrays.asList(TipoClima.LLUVIOSO,TipoClima.VENTOSO));
        muyFrio = new Pronostico((float)4);
        frio = new Pronostico((float)10);
        templado = new Pronostico((float)20);
        caluroso = new Pronostico((float)30);
        muyCaluroso = new Pronostico((float)40);
        pronostico5 = new Pronostico((float)1.1,lluvioso,(float)1.1);
        pronostico1 = new Pronostico((float)1.1,lluvioso,(float)1.1);
        pronostico2 = new Pronostico((float)1.1,ventoso,(float)1.1);
        pronostico3 = new Pronostico((float)1.1,nublado,(float)1.1);
        pronostico4 = new Pronostico((float)1.1,lluviosoYVentoso,(float)1.1);
    }
    @Test
    @DisplayName("Verifica las prendas que no pueden estar para un determinado tipo de clima")
    public void prendasNegadas() {
        assertTrue(pronostico2.prendasNegadas().contains("Paraguas"));
        assertTrue(pronostico2.prendasNegadas().contains("Gorro"));
        assertTrue(pronostico2.prendasNegadas().contains("Pollera"));
    }
    @Test
    @DisplayName("Verifica las prendas que tienen que estar para un determinado tipo de clima")
    public void prendasSatisfacen() {
        assertTrue(pronostico4.prendasSatisfacen().contains("Rompevientos"));
        assertTrue(pronostico4.prendasSatisfacen().contains("Piloto"));
        assertFalse(pronostico4.prendasSatisfacen().contains("Paraguas"));
    }
    @Test
    @DisplayName("Verifica que dos climas ")
    public void climasSimilares(){
        assertTrue(pronostico1.somosSimilares(pronostico1));
    }
    @Test
    @DisplayName("Verifica la lista de niveles de calor cuando es un clima de muy bajas temperaturas")
    public void nivelesDeCalorMuyFrio(){
        Assert.assertEquals(muyFrio.nivelesDeCalorRequeridos().get(0).getClass(),NivelDeCalor.class);
    }
    @Test
    @DisplayName("Verifica la lista de niveles de calor cuando es un clima de bajas temperaturas")
    public void nivelesDeCalorFrio(){
        Assert.assertEquals(frio.nivelesDeCalorRequeridos().get(0).getClass(),NivelDeCalor.class);
    }
    @Test
    @DisplayName("Verifica la lista de niveles de calor cuando es un clima de temperaturas normales ")
    public void nivelesDeCalorTemplado(){
        Assert.assertEquals(templado.nivelesDeCalorRequeridos().get(0).getClass(),NivelDeCalor.class);
    }
    @Test
    @DisplayName("Verifica la lista de niveles de calor cuando es un clima de altas temperaturas")
    public void nivelesDeCalorCaluroso(){
        Assert.assertEquals(caluroso.nivelesDeCalorRequeridos().get(0).getClass(),NivelDeCalor.class);
    }
    @Test
    @DisplayName("Verifica la lista de niveles de calor cuando es un clima de muy altas temperaturas")
    public void nivelesDeCalorMuyCaluroso(){
       Assert.assertEquals(muyCaluroso.nivelesDeCalorRequeridos().get(0).getClass(),NivelDeCalor.class);
    }
}