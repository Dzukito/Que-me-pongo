package ar.utn.dds.modelo;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name="sensibilidad")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoSensibilidad",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("4")
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
