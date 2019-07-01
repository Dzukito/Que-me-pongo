package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.NivelDeCalorIncorrecto;

public class NivelDeCalor {
    private Categoria categoria;
    private int nivelDeCalor;

    public Categoria getCategoria() {
        return categoria;
    }

    public int getNivelDeCalor() {
        return nivelDeCalor;
    }
    NivelDeCalor(Categoria categoria, int nivelDeCalor1){
        this.categoria = categoria;
  /*      if((categoria.getNivelCalorMaximo() <=nivelDeCalor1) || categoria.getNivelCalorMinimo() >= nivelDeCalor1){
            throw new NivelDeCalorIncorrecto();
        }else{*/
            this.nivelDeCalor = nivelDeCalor1;
    //    }
    }
}
