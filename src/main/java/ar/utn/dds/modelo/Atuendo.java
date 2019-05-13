package ar.utn.dds.modelo;


import ar.utn.dds.excepciones.EsaPrendaYaLaTengo;

import java.util.*;
import java.util.stream.Collectors;

public class Atuendo {
    private ArrayList<Prenda> prendas;
    Atuendo(){}
    Atuendo(ArrayList<Prenda> prendas){
       this.prendas = prendas;
    }

    public void cambiarPrenda(Prenda prendaNueva){
        this.prendas = (ArrayList<Prenda>) this.prendas.stream().filter(prenda -> prenda.categoria() != prendaNueva.categoria()).collect(Collectors.toList());
        this.prendas.add(prendaNueva);
    }

    public boolean somosIguales(Atuendo atuendo){
       return (this.tengoTusPrendas(atuendo) && atuendo.tengoTusPrendas(this));
    }

    private boolean tengoTusPrendas(Atuendo atuendo){
        return this.prendas.stream().allMatch(prenda -> atuendo.tengoPrenda(prenda));
    }

    public boolean tengoPrenda(Prenda prenda){
        return this.prendas.stream().anyMatch(prenda1 -> prenda1.hashCode() == prenda.hashCode());
    }

    public void agregarPrenda(Prenda prenda){
        try {
            this.esaPrendaYaLaTengo(prenda);
            this.prendas.add(prenda);
        }catch (EsaPrendaYaLaTengo e){
            this.cambiarPrenda(prenda);
        }
    }

    public Prenda getPrenda(String categoria) {
    	return this.prendas.stream().filter(prenda -> prenda.categoria() == categoria).collect(Collectors.toList()).get(0);
    }

    private void esaPrendaYaLaTengo(Prenda nuevaPrenda){
        if (this.prendas.stream().anyMatch(prenda -> prenda.categoria() == nuevaPrenda.categoria())) {
            throw new EsaPrendaYaLaTengo();
        }
    }

}
