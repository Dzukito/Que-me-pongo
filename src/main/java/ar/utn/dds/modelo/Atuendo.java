package ar.utn.dds.modelo;


import ar.utn.dds.excepciones.EsaPrendaYaLaTengo;
import ar.utn.dds.excepciones.noPuedeSuperponerse;

import java.util.*;
import java.util.stream.Collectors;

public class Atuendo {
    private ArrayList<Prenda> prendas;
    private int usabilidad;//Cantidad de veces que fue usado
    private ArrayList<CalificacionAtuendo> calificaciones;


    //Metodos-no-se-usan----------------------------------
    public List<String> TiposDePrendas() {
        return prendas.stream().map(prenda -> prenda.getTipoDePrenda().categoria()).collect(Collectors.toList());
    }
    public int nivelDeCalorTotal(){
        return prendas.size();
    }
    public boolean tieneEstilo(){
        return this.prendas.stream().map(prenda -> prenda.getEstilo()).distinct().collect(Collectors.toList()).size() == 1;
    }
    public void noPuedoAgregarPrenda(Prenda prenda){ //En lugar de un boolean que tire una nueva excepcion
        if(this.prendas.stream().anyMatch(prenda1 -> !prenda1.esSuperponible(prenda))) {throw new noPuedeSuperponerse();}
    }
    public List<String> NombresPrendas() {
        return prendas.stream().map(prenda -> prenda.getNombrePrenda()).collect(Collectors.toList());
    }

    //Metodos-privados------------------------------------
    private void esaPrendaYaLaTengo(Prenda nuevaPrenda){ //Ya no evalua la categoria, sino el tipo, porque puedo tener una remera y un buso-ambos son de la misma categoria
        if (this.prendas.stream().anyMatch(prenda -> prenda.getTipoDePrenda().tipo() == nuevaPrenda.getTipoDePrenda().tipo() )) {
            throw new EsaPrendaYaLaTengo();
        }
    }
    private boolean tengoTusPrendas(Atuendo atuendo){
        return this.prendas.stream().allMatch(prenda -> atuendo.tengoPrenda(prenda));
    }
    public int nivelDeCalor(Categoria categoria){
        return this.prendas.stream().filter(prenda -> prenda.getCategoria() == categoria.getCategoria()).collect(Collectors.toList()).size();
    }
    public boolean compatibleConTiempo(Pronostico pronostico,Usuario usuario) {
    	return !prendas.stream().anyMatch(prenda->pronostico.prendasNegadas().contains(prenda.tipo())) &&
    			prendas.stream().anyMatch(prenda->pronostico.prendasSatisfacen().contains(prenda.tipo()));
    }
    //Estilos
    public boolean tieneEstiloEnParticular(Estilo estilo){
        return this.prendas.stream().anyMatch(prenda -> prenda.tieneEstilo(estilo));
    }
    public void cambiarPrenda(Prenda prendaNueva){
        this.prendas = (ArrayList<Prenda>) this.prendas.stream().filter(prenda -> prenda.getCategoria() != prendaNueva.getCategoria()).collect(Collectors.toList());
        this.prendas.add(prendaNueva);
    }
    public boolean somosIguales(Atuendo atuendo){
       return (this.tengoTusPrendas(atuendo) && atuendo.tengoTusPrendas(this));
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
    public void agregarUsabilidad() {
        this.usabilidad = this.usabilidad +1;
    }


    //Getters-y-Setters----------------------------------------------
    public ArrayList<Prenda> getPrendas(){
        return this.prendas;
    }
    public ArrayList<TipoPrenda> getTiposDePrenda(){
        return (ArrayList<TipoPrenda>) this.getPrendas().stream().map(prenda -> prenda.getTipoDePrenda()).collect(Collectors.toList());
    }
    public Prenda getPrenda(String categoria) {
        return this.prendas.stream().filter(prenda -> prenda.getCategoria() == categoria).collect(Collectors.toList()).get(0);
    }
    public List<List<String>> getImagenes() { //lista con las listas de imagenes de c/prenda
        return prendas.stream().map(prenda -> prenda.getFotografo().imagenes()).collect(Collectors.toList());
    }
    public void setterCalificacion(CalificacionAtuendo calif) {
        this.calificaciones.add(calif);
    }
    //Constructores--------------------------------------------------
    Atuendo(){
        this.prendas = new ArrayList<Prenda>();
        this.usabilidad = 0;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
    }
    Atuendo(ArrayList<Prenda> prendas){
        this.prendas = prendas;
        this.usabilidad = 0;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
    }
    Atuendo(ArrayList<Prenda> prendas, int usabilidad){
        this.prendas = prendas;
        this.usabilidad = usabilidad;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
    }
    Atuendo(ArrayList<Prenda> prendas, int usabilidad, CalificacionAtuendo calificacion){
        this.prendas = prendas;
        this.usabilidad = usabilidad;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
        this.calificaciones.add(calificacion);
    }
}