package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Guardaropa {
    private List<Prenda> prendas;

    public int cantidadDePrendas(){
       return this.prendas.size();
    }
    public List<Prenda> prendasPorCategoria(String categoria){
        return this.prendas.stream().filter(prenda -> prenda.categoria() == categoria).collect(Collectors.toList());
    }
    public  List<String> tiposCategorias(){
        return this.prendas.stream().map(prenda -> prenda.categoria()).distinct().collect(Collectors.toList());
    }
    public int cantidadCategorioas(){
        return this.tiposCategorias().size();
    }
    public ArrayList<ArrayList<Prenda>> listaDeListasPorCategorias(){
         ArrayList<ArrayList<Prenda>> retorno = new ArrayList<ArrayList<Prenda>>();
         for (int i = 0;i< this.cantidadCategorioas();i=i+1){
             retorno.add((ArrayList<Prenda>) this.prendasPorCategoria(this.tiposCategorias().get(i)));
         }
         return retorno;
    }
    public ArrayList<Atuendo> atuendosDisponibles() {
        ArrayList<ArrayList<Prenda>> combinaciones = new ArrayList<ArrayList<Prenda>>();
        combinaciones = listaDeListaDePrenda(this.listaDeListasPorCategorias().get(0));
        for (int i = 1; i<= this.cantidadCategorioas();i=i+1){
            this.agregarPrendaAAtuendo(combinaciones,this.listaDeListasPorCategorias().get(0));
        }
        ArrayList<Atuendo> atuendos = new ArrayList<Atuendo>();
        combinaciones.stream().forEach(prendas -> atuendos.add(this.listaDePrendasAAtuendo(prendas)));
        return atuendos;
    }
    public Atuendo listaDePrendasAAtuendo(ArrayList<Prenda> prendas){
        Map<String,Prenda> mapPrendas = new HashMap<String,Prenda>();
        prendas.stream().forEach(prenda -> mapPrendas.put(prenda.categoria(),prenda));
        return new Atuendo(mapPrendas);
    }
    public ArrayList<ArrayList<Prenda>> listaDeListaDePrenda(ArrayList<Prenda> prendas){
        ArrayList<ArrayList<Prenda>> retorno =  new ArrayList<ArrayList<Prenda>>();
        for (int i = 0; i<prendas.size(); i++){
            ArrayList<Prenda> listaDePrenda =  new ArrayList<Prenda>();
            listaDePrenda.add(prendas.get(i));
            retorno.add(listaDePrenda);
        }
        return retorno;
    }
    public ArrayList<ArrayList<Prenda>> agregarPrendaAAtuendo(ArrayList<ArrayList<Prenda>> prendas1, ArrayList<Prenda> prendas2) {
        ArrayList<ArrayList<Prenda>> combinaciones = new ArrayList<ArrayList<Prenda>>();
        for (int i = 0; i<prendas1.size(); i++){
            for (int j = 0; j<prendas1.size(); j++){
                ArrayList<Prenda> combinacion = new ArrayList<Prenda>();
                combinacion = (prendas1.get(i));
                combinacion.add(prendas2.get(j));
                combinaciones.add(combinacion);
            }
        }
        return combinaciones;
    }
    Guardaropa(ArrayList<Prenda> prendas){
        this.prendas = prendas;
    }
}

