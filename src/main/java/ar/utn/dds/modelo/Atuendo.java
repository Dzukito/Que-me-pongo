package ar.utn.dds.modelo;


import ar.utn.dds.excepciones.EsaPrendaYaLaTengo;

import java.util.*;
import java.util.stream.Collectors;

public class Atuendo {
    private ArrayList<Prenda> prendas;
    private int usabilidad;//Cantidad de veces que fue usado
    private CalificacionAtuendo calificacion;


    public int nivelDeCalor(Categoria categoria){
        return this.prendas.stream().filter(prenda -> prenda.categoria() == categoria.getCategoria()).collect(Collectors.toList()).size();
    }
    public boolean tieneEstilo(){
        return this.prendas.stream().map(prenda -> prenda.getEstilo()).distinct().collect(Collectors.toList()).size() == 1;
    }
    Atuendo(){
        this.prendas = new ArrayList<Prenda>();
        this.usabilidad = 0;
        this.calificacion = new CalificacionAtuendo();
    }
    Atuendo(ArrayList<Prenda> prendas){
       this.prendas = prendas;
       this.usabilidad = 0;
       this.calificacion = new CalificacionAtuendo();
    }
    Atuendo(ArrayList<Prenda> prendas, int usabilidad){
        this.prendas = prendas;
        this.usabilidad = usabilidad;
        this.calificacion = new CalificacionAtuendo();
    }
    Atuendo(ArrayList<Prenda> prendas, int usabilidad, CalificacionAtuendo calificacion){
        this.prendas = prendas;
        this.usabilidad = usabilidad;
        this.calificacion = calificacion;
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
        return this.prendas.stream().anyMatch(prenda1 -> prenda1.somosIguales(prenda));
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
    public ArrayList<Prenda> todasLasPrendas(){
        return this.prendas;
    }
    public void sumarUsabilidad() {
        this.usabilidad = this.usabilidad +1;
    }
}
