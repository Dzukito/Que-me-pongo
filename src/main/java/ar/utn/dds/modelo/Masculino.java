package ar.utn.dds.modelo;

import javax.persistence.*;

@Entity
@Table(name="Sexo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoSexo",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("2")
public class Masculino implements Sexo {
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
