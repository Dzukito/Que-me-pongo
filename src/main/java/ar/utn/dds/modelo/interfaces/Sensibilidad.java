package ar.utn.dds.modelo.interfaces;

import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.clima.NivelDeCalor;

import java.util.ArrayList;

public interface Sensibilidad {
    void cambiarSensibilidad(Usuario usuario, Sensibilidad sensibilidad);
    void ajustarPreferencias(ArrayList<NivelDeCalor> nivelesDeCalor);
    NivelDeCalor ajustarNivelDeCalor(NivelDeCalor nivelDeCalor);
    ArrayList<NivelDeCalor> ajustarNivelesDeCalor(ArrayList<NivelDeCalor> nivelesDeCalor);
}
