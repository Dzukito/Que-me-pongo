package ar.utn.dds.modelo;

public class Unisex implements Sexo {
    @Override
    public boolean masculino(String sexo) {
        return true;
    }
    @Override
    public boolean femenino(String sexo) {
        return true;
    }
    @Override
    public boolean unisex(String sexo){ return true; }
    Unisex() {}
}
