package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Guardaropa {
    private List<Prenda> prendas;




    private ArrayList<String> mostrarPrendasCombinacion(ArrayList<Prenda> prendas){
        return (ArrayList<String>)prendas.stream().map(prenda -> prenda.tipo()).collect(Collectors.toList());
    }

    public int cantidadDePrendas(){
       return this.prendas.size();
    }
    public List<Prenda> prendasPorCategoria(String categoria, List<Prenda> prendas){
        return prendas.stream().filter(prenda -> prenda.categoria().equals(categoria)).collect(Collectors.toList());
    }
    public  List<String> tiposCategorias(List<Prenda> prendas){
        return prendas.stream().map(prenda -> prenda.categoria()).distinct().collect(Collectors.toList());
    }
    public int cantidadCategorioas(List<Prenda> prendas){
        return this.tiposCategorias(prendas).size();
    }
    private ArrayList<ArrayList<Prenda>> listaDeListasPorCategorias(List<Prenda> prendas){
         ArrayList<ArrayList<Prenda>> retorno = new ArrayList<ArrayList<Prenda>>();
         for (int i = 0;i< this.cantidadCategorioas(prendas);i=i+1){
             retorno.add((ArrayList<Prenda>) this.prendasPorCategoria(this.tiposCategorias(prendas).get(i),prendas));
         }
         return retorno;
    }
    private ArrayList<Atuendo> generarAtuendos(List<Prenda> prendas) {
        ArrayList<ArrayList<Prenda>> combinaciones;
        combinaciones = listaDeListaDePrenda(this.listaDeListasPorCategorias(prendas).get(0));
        System.out.println(combinaciones.stream().map(combinacion -> this.mostrarPrendasCombinacion(combinacion)).collect(Collectors.toList()));
        for (int i = 1; i<= this.cantidadCategorioas(prendas)-1;i=i+1){
            combinaciones = combinatoriaPrendaCombinacion(combinaciones,this.listaDeListasPorCategorias(prendas).get(i));
            System.out.println(combinaciones.stream().map(combinacion -> this.mostrarPrendasCombinacion(combinacion)).collect(Collectors.toList()));
        }
        ArrayList<Atuendo> atuendos = new ArrayList<Atuendo>();
        combinaciones.forEach(prendas1 -> atuendos.add(this.listaDePrendasAAtuendo(prendas1)));
        return atuendos;
    }
    private Atuendo listaDePrendasAAtuendo(ArrayList<Prenda> prendas){
        Map<String,Prenda> mapPrendas = new HashMap<String,Prenda>();
        prendas.forEach(prenda -> mapPrendas.put(prenda.categoria(),prenda));
        return new Atuendo(mapPrendas);
    }
    private ArrayList<ArrayList<Prenda>> listaDeListaDePrenda(ArrayList<Prenda> prendas){
        ArrayList<ArrayList<Prenda>> retorno =  new ArrayList<ArrayList<Prenda>>();
        for (int i = 0; i<prendas.size(); i++){
            ArrayList<Prenda> listaDePrenda =  new ArrayList<Prenda>();
            listaDePrenda.add(prendas.get(i));
            retorno.add(listaDePrenda);
        }
        return retorno;
    }
    private ArrayList<ArrayList<Prenda>> combinatoriaPrendaCombinacion(ArrayList<ArrayList<Prenda>> combinaciones, ArrayList<Prenda> prendas) {
        ArrayList<ArrayList<Prenda>> combinaciones2 = new ArrayList<ArrayList<Prenda>>();
        for (int i = 0; i<combinaciones.size(); i++){
            for (int j = 0; j<prendas.size(); j++){
                ArrayList<Prenda> combinacion = combinaciones.get(i);
                combinacion.add(prendas.get(j));
                combinaciones2.add(combinacion);
            }
        }
        System.out.println(combinaciones2.size());
        return combinaciones2;
    }
    Guardaropa(ArrayList<Prenda> prendas){
        this.prendas = prendas;
    }
    public int cantidadAtuendosDisponibles(){
        return this.atuendosDisponibles().size();
    }
    public void agregarPrenda(Prenda prenda) {
        this.prendas.add(prenda);
    }
    public void quitarPrenda(Prenda prenda){
        this.prendas = this.prendas.stream().filter(prenda1 -> !(prenda1.equals(prenda))).collect(Collectors.toList());
    }
    public ArrayList<Atuendo> atuendosDisponibles() {
        return this.generarAtuendos(this.prendas);
    }
}