package ar.utn.dds.modelo;

import java.util.ArrayList;

public class Ermitanio implements Sensibilidad {
    @Override
    public void cambiarSensibilidad(Usuario usuario, Sensibilidad sensibilidad) {}
    @Override
    public void ajustarPreferencias(ArrayList<NivelDeCalor> nivelesDeCalor) {}
    @Override
    public NivelDeCalor ajustarNivelDeCalor(NivelDeCalor nivelDeCalor) { return  nivelDeCalor; }
    @Override
    public ArrayList<NivelDeCalor> ajustarNivelesDeCalor(ArrayList<NivelDeCalor> nivelesDeCalor) { return  nivelesDeCalor; }
}
