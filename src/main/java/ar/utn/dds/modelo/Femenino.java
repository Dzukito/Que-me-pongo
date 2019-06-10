package ar.utn.dds.modelo;

public class Femenino implements Sexo{
    @Override
    public boolean masculino() {
        return false;
    }

    @Override
    public boolean femenino() {
        return true;
    }
}
