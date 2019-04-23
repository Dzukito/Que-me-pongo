package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class Guardaropa {
    private List<Prenda> prendas;

    public int cantidadDePrendas(){
       return this.prendas.size();
    }

    Guardaropa(ArrayList<Prenda> prendas){
        this.prendas = prendas;
    }
}
