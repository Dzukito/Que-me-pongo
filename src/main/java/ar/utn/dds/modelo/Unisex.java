package ar.utn.dds.modelo;

public class Unisex implements Sexo {

    @Override
    public boolean masculino() {
        return true;
    }

    @Override
    public boolean femenino() {
        return true;
    }
}
