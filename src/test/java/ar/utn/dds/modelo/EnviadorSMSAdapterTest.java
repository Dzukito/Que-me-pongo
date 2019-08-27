package ar.utn.dds.modelo;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnviadorSMSAdapterTest {

    @Test
    public void enviar() {
        EnviadorSMSAdapter enviador = new EnviadorSMSAdapter();
        enviador.enviar("+5491123427701","TPDisenio2019","Enviado desde java");
    }
}