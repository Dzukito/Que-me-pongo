package ar.utn.dds.modelo;
import javax.persistence.*;

@Entity
@Table(name="sexo")
public interface Sexo {
    boolean masculino(String sexo);
    boolean femenino(String sexo);
    boolean unisex(String sexo);
}
