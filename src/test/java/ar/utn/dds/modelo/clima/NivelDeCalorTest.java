package ar.utn.dds.modelo.clima;

import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.clima.NivelDeCalor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("Test de la clase pronostico")
public class NivelDeCalorTest {
    NivelDeCalor nivelDeCalor = new NivelDeCalor(Categoria.TORSO,3);

    @Test
    @DisplayName("Verifica la reducción del nivel de calor")
    public void reducirNivelDeCalor() {
        System.out.println();
    }
    @Test
    @DisplayName("Verifica el aumento del nivel de calor")
    public void aumentarNivelDeCalor() {
    }
    @Test
    @DisplayName("Verifica la excepcion que tiene que salir cuando un nivel de calor supera los valores soportados por una categoria")
    public void fueraDeRangoDeLaCategoria() {
    }
}
