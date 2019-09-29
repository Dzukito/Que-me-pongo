package ar.utn.dds.modelo;

import java.util.ArrayList;

public interface Sensibilidad {
    void cambiarSensibilidad(Usuario usuario, Sensibilidad sensibilidad);
    void ajustarPreferencias(ArrayList<NivelDeCalor> nivelesDeCalor);
    NivelDeCalor ajustarNivelDeCalor(NivelDeCalor nivelDeCalor);
    ArrayList<NivelDeCalor> ajustarNivelesDeCalor(ArrayList<NivelDeCalor> nivelesDeCalor);
}
