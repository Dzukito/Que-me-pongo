package ar.utn.dds.modelo.sensibilidades;

import ar.utn.dds.modelo.clima.NivelDeCalor;
import ar.utn.dds.modelo.interfaces.Sensibilidad;
import ar.utn.dds.modelo.clases.Usuario;

import java.util.ArrayList;
import java.util.stream.Collectors;
//@Entity
//@Table(name="sensibilidad")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipoSensibilidad",discriminatorType = DiscriminatorType.INTEGER)
//@DiscriminatorValue("3")
public class Friolento implements Sensibilidad {
    @Override
    public void cambiarSensibilidad(Usuario usuario, Sensibilidad sensibilidad) { }
    @Override
    public void ajustarPreferencias(ArrayList<NivelDeCalor> nivelesDeCalor) { }
    @Override
    public NivelDeCalor ajustarNivelDeCalor(NivelDeCalor nivelDeCalor) { return nivelDeCalor.aumentarNivelDeCalor(); }
    @Override
    public ArrayList<NivelDeCalor> ajustarNivelesDeCalor(ArrayList<NivelDeCalor> nivelesDeCalor) {
        return (ArrayList<NivelDeCalor>) nivelesDeCalor.stream().map(nivelDeCalor -> nivelDeCalor.aumentarNivelDeCalor()).collect(Collectors.toList());
    }
}
