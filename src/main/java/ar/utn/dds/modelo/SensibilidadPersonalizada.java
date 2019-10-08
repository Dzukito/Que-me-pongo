package ar.utn.dds.modelo;

import javafx.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
@Entity
@Table(name="sensibilidad")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoSensibilidad",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
public class SensibilidadPersonalizada implements Sensibilidad {
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sensibilidadPersonalizada",
            joinColumns = {@JoinColumn(name = "id_nivelDeCalor", referencedColumnName = "id_nivelDeCalor")},
            inverseJoinColumns = {@JoinColumn(name = "id_sensibilidad", referencedColumnName = "id_sensibilidad")})
    private ArrayList<Pair<NivelDeCalor,Sensibilidad>> sensibilidades;


    @Override
    public void cambiarSensibilidad(Usuario usuario, Sensibilidad sensibilidad) {}
    @Override
    public void ajustarPreferencias(ArrayList<NivelDeCalor> nivelesDeCalor) {}
    @Override
    public NivelDeCalor ajustarNivelDeCalor(NivelDeCalor nivelDeCalor) { return this.sensibilidades.stream().filter(categoriaSensibilidadPair -> categoriaSensibilidadPair.getKey().getCategoria() == nivelDeCalor.getCategoria()).collect(Collectors.toList()).get(0).getValue().ajustarNivelDeCalor(nivelDeCalor); }
    @Override
    public ArrayList<NivelDeCalor> ajustarNivelesDeCalor(ArrayList<NivelDeCalor> nivelesDeCalor) { return (ArrayList<NivelDeCalor>) nivelesDeCalor.stream().map(nivelDeCalor -> this.ajustarNivelDeCalor(nivelDeCalor)).collect(Collectors.toList()); }
    public void cambiarMiSensibilidad(Categoria categoria, Sensibilidad sensibilidad){ ArrayList<Pair<NivelDeCalor,Sensibilidad>> nivelDeCalor = (ArrayList<Pair<NivelDeCalor, Sensibilidad>>) this.sensibilidades.stream().filter(nivelDeCalorSensibilidadPair -> nivelDeCalorSensibilidadPair.getKey().getCategoria() == categoria).collect(Collectors.toList()); }

}
