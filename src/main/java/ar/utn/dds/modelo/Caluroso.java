package ar.utn.dds.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
@Entity
@Table(name="sensibilidad")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoSensibilidad",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("2")
public class Caluroso implements Sensibilidad{
    @Override
    public void cambiarSensibilidad(Usuario usuario, Sensibilidad sensibilidad) {

    }

    @Override
    public void ajustarPreferencias(ArrayList<NivelDeCalor> nivelesDeCalor) {

    }

    @Override
    public NivelDeCalor ajustarNivelDeCalor(NivelDeCalor nivelDeCalor) {
        return nivelDeCalor.reducirNivelDeCalor();
    }

    @Override
    public ArrayList<NivelDeCalor> ajustarNivelesDeCalor(ArrayList<NivelDeCalor> nivelesDeCalor) {
        return (ArrayList<NivelDeCalor>) nivelesDeCalor.stream().map(nivelDeCalor -> nivelDeCalor.reducirNivelDeCalor()).collect(Collectors.toList());
    }
}
