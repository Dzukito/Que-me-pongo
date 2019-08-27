package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class EnviadorWhatsappAdapter extends Enviador {
    @Override
    public void enviar(String direccion, String asunto, String mensaje) {

    }

    @Override
    public void enviar(String direccion, String asunto, String mensaje, ArrayList<String> image) {

    }

    @Override
    public void enviarSugerencia(String direccion, String asunto, String mensaje, ArrayList<String> image) {

    }

    @Override
    public void enviarAlertaMeteorologica(String direccion, String asunto, String mensaje, List<List<String>> image) {

    }
}
