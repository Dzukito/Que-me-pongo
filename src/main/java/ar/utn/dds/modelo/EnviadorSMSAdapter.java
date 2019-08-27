package ar.utn.dds.modelo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.ArrayList;
import java.util.List;

import static ar.utn.dds.Constantes.*;

public class EnviadorSMSAdapter extends Enviador{
    @Override
    public void enviar(String direccion, String asunto, String mensaje) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(twilioNumber),
                new PhoneNumber(direccion),
                asunto + " " + mensaje).create();

        System.out.println(message.getSid());
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
