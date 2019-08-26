package ar.utn.dds.modelo;


import ar.utn.dds.excepciones.EsaPrendaYaLaTengo;
import ar.utn.dds.excepciones.noPuedeSuperponerse;

import java.util.*;
import java.util.stream.Collectors;

public class Atuendo {
    private ArrayList<Prenda> prendas;
    private int usabilidad;//Cantidad de veces que fue usado
    private CalificacionAtuendo calificacion;
    
    
    
    public boolean cumpleSensacionTermica(Pronostico pronostico, Usuario usuario) {
    	return !(this.cumploParaCalurosos(pronostico, usuario) || this.aptoParaFriolentos(pronostico, usuario)) ||
    			(usuario.getSensacionTermica()==5); //si el usuario no tiene preferencias es buena sugerencia y ya
    }
    public boolean cumploParaCalurosos(Pronostico pronostico, Usuario usuario) {
    	//en obras 
    	return true;
    	
    }
    public boolean aptoParaFriolentos(Pronostico pronostico, Usuario usuario) {
    	//en obras
    	return true;
    }
    /*
     * cumpleSensacionTermica: evalua si el atuendo se adapta a las subjetividad del usuario
     *
     */


    public int nivelDeCalor(Categoria categoria){
        return this.prendas.stream().filter(prenda -> prenda.categoria() == categoria.getCategoria()).collect(Collectors.toList()).size();
    }
    public int nivelDeCalorTotal(){
         return prendas.size();
    }
    public boolean compatibleConTiempo(Pronostico pronostico) {
    	return !prendas.stream().anyMatch(prenda->pronostico.prendasNegadas().contains(prenda.tipo())) &&
    			prendas.stream().anyMatch(prenda->pronostico.prendasSatisfacen().contains(prenda.tipo())) &&
    		    (pronostico.haceFrio() && this.nivelDeCalorTotal()>14  && this.nivelDeCalorTotal()<30  //MAX y MIN
    		    		|| (!pronostico.haceFrio() && this.nivelDeCalorTotal()<=pronostico.getTemperatura())); 
    }
    
    public boolean tieneEstilo(){
        return this.prendas.stream().map(prenda -> prenda.getEstilo()).distinct().collect(Collectors.toList()).size() == 1;
    }
    public boolean tieneEstiloEnParticular(Estilo estilo){
        return this.prendas.stream().anyMatch(prenda -> prenda.getEstilo()== estilo);
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
          //  this.noPuedoAgregarPrenda(prenda);
            this.prendas.add(prenda);
            
        }catch (EsaPrendaYaLaTengo e){
            this.cambiarPrenda(prenda);
        }
       /* }catch (noPuedeSuperponerse e){
            this.cambiarPrenda(prenda);
        }*/
    }
    public Prenda getPrenda(String categoria) {
    	return this.prendas.stream().filter(prenda -> prenda.categoria() == categoria).collect(Collectors.toList()).get(0);
    }
    private void esaPrendaYaLaTengo(Prenda nuevaPrenda){ //Ya no evalua la categoria, sino el tipo, porque puedo tener una remera y un buso-ambos son de la misma categoria
        if (this.prendas.stream().anyMatch(prenda -> prenda.tipoDePrenda().tipo() == nuevaPrenda.tipoDePrenda().tipo() )) { 
            throw new EsaPrendaYaLaTengo();
        }
    }
    public void noPuedoAgregarPrenda(Prenda prenda){ //En lugar de un boolean que tire una nueva excepcion
        if(this.prendas.stream().anyMatch(prenda1 -> !prenda1.esSuperponible(prenda))) {throw new noPuedeSuperponerse();}
    }
    public ArrayList<Prenda> todasLasPrendas(){
        return this.prendas;
    }
    public void sumarUsabilidad() {
        this.usabilidad = this.usabilidad +1;
    }

    public List<String> NombresPrendas() {
        return prendas.stream().map(prenda -> prenda.getNombrePrenda()).collect(Collectors.toList());
    }
    public List<String> TiposDePrendas() {
        return prendas.stream().map(prenda -> prenda.tipoDePrenda().categoria()).collect(Collectors.toList());
    }
    public List<List<String>> Imagenes() { //lista con las listas de imagenes de c/prenda
        return prendas.stream().map(prenda -> prenda.imagenes()).collect(Collectors.toList());
    }
    public void agregarCalificacion(CalificacionAtuendo calif) { 
    	this.calificacion= calif;
    }
}
