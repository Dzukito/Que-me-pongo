package ar.utn.dds.modelo;

import javax.persistence.*;

@Entity
@Table(name="sexo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoSensibilidad",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("3")
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
