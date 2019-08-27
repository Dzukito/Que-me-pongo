package ar.utn.dds.modelo;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnviadorEmailAdapterTest {

    @Test
    public void enviar() {
    }

    @Test
    public void testEnviar() {
        EnviadorEmailAdapter enviador = new EnviadorEmailAdapter();
        String destinatario =  "diazmartin_95@yahoo.com.ar"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde Java";
        String cuerpo = "Esta es una prueba de correo...";

        enviador.enviar(destinatario, asunto, cuerpo);
    }
}