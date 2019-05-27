package ar.utn.dds.modelo;

public class Premium extends Membrecia{

    @Override
    public void cambiarAPremium(Usuario usuario) {
    }

    @Override
    public void cambiarAGratuito(Usuario usuario) {
        usuario.cambiarMembrecia(new Gratuito());
    }

    Premium(){}
}
