package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.NivelDeCalorIncorrecto;

import java.util.Objects;

public class NivelDeCalor {
    private Categoria categoria;
    private int nivelDeCalor;



    public Categoria getCategoria() {
        return categoria;
    }
    public int getNivelDeCalor() {
        return nivelDeCalor;
    }

    public NivelDeCalor puntoMedioDeNivelDeCalor(){
        return new NivelDeCalor(this.categoria, this.categoria.getPuntoMedio());
    }
    public NivelDeCalor reducirNivelDeCalor(){
        if(categoria.getNivelCalorMinimo()<=this.nivelDeCalor){
            return new NivelDeCalor(this.categoria, this.nivelDeCalor--);
        }else{
            return this;
        }
    }
    public NivelDeCalor aumentarNivelDeCalor(){
        if(categoria.getNivelCalorMinimo()>=this.nivelDeCalor){
            return new NivelDeCalor(this.categoria, this.nivelDeCalor++);
        }else{
            return this;
        }
    }
    @Override
    public int hashCode(){
        return Objects.hash(categoria, nivelDeCalor);
    }

    NivelDeCalor(Categoria categoria){
        this.categoria = categoria;
        this.nivelDeCalor = (this.categoria.getNivelCalorMaximo()/2)-(this.categoria.getNivelCalorMaximo()%2) ;
    }
    NivelDeCalor(Categoria categoria, int nivelDeCalor1){
        this.categoria = categoria;
        if((categoria.getNivelCalorMaximo() <=nivelDeCalor1) || categoria.getNivelCalorMinimo() >= nivelDeCalor1){
            throw new NivelDeCalorIncorrecto();
        }else{
            this.nivelDeCalor = nivelDeCalor1;
        }
    }
}