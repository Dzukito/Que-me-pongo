package ar.utn.dds.modelo;

import ar.utn.dds.Constantes;

public class Premium extends Membrecia{

    @Override
    public void cambiarAPremium(Usuario usuario) {
    }

    @Override
    public void cambiarAGratuito(Usuario usuario) {
        usuario.cambiarMembrecia(new Gratuito());
        usuario.misRoperos().forEach(ropero->ropero.bloquearExcedente(Constantes.maximoPrendasGuardaropa)); 
        }

    Premium(){}
}
