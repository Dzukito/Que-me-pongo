package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guardaropa implements AceptarSuegerenciaObservador{
    private List<Prenda> prendas;
    private List<Atuendo> atuendosMostrados;

    Guardaropa(ArrayList<Prenda> prendas){
        this.prendas = prendas;
        this.atuendosMostrados = new ArrayList<Atuendo>();
    }
    public Atuendo sugerirAtuendo(){
        Atuendo atuendo;
        if(atuendosMostrados.size()!= this.cantidadDeAtuendosPosibles())
        {
            atuendo = this.generarAtuendo();
            if(this.estaEnLaLista(atuendo)){
                this.sugerirAtuendo();
            }else{
                this.atuendosMostrados.add(atuendo);
                return atuendo;
            }
        }
        return this.atuendosMostrados.get((int) (Math.random() * this.atuendosMostrados.size()));
    }
    public boolean estaEnLaLista(Atuendo atuendo){
        return  this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo1.somosIguales(atuendo));
    }
    public Atuendo generarAtuendo(){
        Atuendo atuendo = new Atuendo();
        this.prendasPorCategoria().forEach(categoria -> atuendo.agregarPrenda(categoria.get((int) (Math.random() * categoria.size()))));
        return atuendo;
    }
    private ArrayList<ArrayList<Prenda>> prendasPorCategoria(){
        ArrayList<ArrayList<Prenda>> retorno = new ArrayList<ArrayList<Prenda>>();
        for (int i = 0;i< this.cantidadCategorioas(prendas);i=i+1){
            retorno.add((ArrayList<Prenda>) this.prendasPorCategoria(this.tiposCategorias(prendas).get(i),prendas));
        }
        return retorno;
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
    public ArrayList<Atuendo> atuendosGenerados(){
        return (ArrayList<Atuendo>) this.atuendosMostrados;
    }
    public int cantidadAtuendosGenerados() {
        return this.atuendosMostrados.size();
    }
    public void agregarPrenda(Prenda prenda) {
        this.prendas.add(prenda);
    }
    public int cantidadDeAtuendosPosibles(){
        int j =1;
        for(int i = 0; i<this.prendasPorCategoria().size();i=i+1 ){
            j=this.prendasPorCategoria().get(i).size()*j;
        }
        return j;
    }
    public List<Prenda> getPrendas() {
        return this.prendas;
    }
    public int cantidadDePrendasEnCategoria(String categoria) {
        return this.prendasPorCategoria(categoria, this.prendas).size();
    }
    public void reservarPrendas(Atuendo atuendo){
        this.prendas.stream()
                .filter(prenda -> atuendo.tengoPrenda(prenda))
                .forEach(prenda -> prenda.bloquearse());
    }
    public void desbloquearPrendas(Atuendo atuendo){
        this.prendas.stream()
                .filter(prenda -> atuendo.tengoPrenda(prenda))
                .forEach(prenda -> prenda.desbloquearse());
    }
    public boolean puedoCrear(Atuendo atuendo){
        return atuendo.todasLasPrendas().stream().allMatch(prenda -> this.prendas.stream().anyMatch(prenda1 -> prenda.somosIguales(prenda1)));
    }
    public Atuendo sugerirAtuendo(Pronostico pronostico, Evento evento, Usuario usuario) {
        Atuendo atuendo = new Atuendo();
        return atuendo;
    }
    public boolean yaMostreAtuendo(Atuendo atuendo){
        return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo.somosIguales(atuendo1));
    }
    @Override
    public void updateAceptarSugerencia(Atuendo atuendo) {
        if(this.puedoCrear(atuendo)){
            if(this.yaMostreAtuendo(atuendo)){
                this.atuendosMostrados.stream().filter(atuendo1 -> this.yaMostreAtuendo(atuendo)).collect(Collectors.toList()).get(0).sumarUsabilidad();
            }else{
                this.atuendosMostrados.add(atuendo);
                this.atuendosMostrados.stream().filter(atuendo1 -> this.yaMostreAtuendo(atuendo)).collect(Collectors.toList()).get(0).sumarUsabilidad();
            }
        }
    }
}