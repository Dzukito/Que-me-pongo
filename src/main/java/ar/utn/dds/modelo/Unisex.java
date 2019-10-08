package ar.utn.dds.modelo;

import javax.persistence.*;

@Entity
@Table(name="Sexo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoSexo",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
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
