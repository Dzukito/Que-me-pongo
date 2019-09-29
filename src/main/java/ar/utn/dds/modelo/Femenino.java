package ar.utn.dds.modelo;

public class Femenino implements Sexo{
    @Override
    public boolean masculino(String sexo) {
        return sexo == "Masculino";
    }
    @Override
    public boolean femenino(String sexo) {
        return sexo == "Femenino";
    }
    @Override
    public boolean unisex(String sexo){ return false;}
}
