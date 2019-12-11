package ar.utn.dds.modelo.enviadores;

import org.junit.Test;

public class EnviadorSMSAdapterTest {

    @Test
    public void enviar() {
        EnviadorSMSAdapter enviador = new EnviadorSMSAdapter();
        enviador.enviar("+5491123427701","TPDisenio2019","Enviado desde java");
    }
}