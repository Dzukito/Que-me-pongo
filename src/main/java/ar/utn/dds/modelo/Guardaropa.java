package ar.utn.dds.modelo;

import java.util.*;
import java.util.stream.Collectors;

import ar.utn.dds.Constantes;

public class Guardaropa implements AceptarSuegerenciaObservador, RechazarSugerenciaObservador {

    private List<Prenda> prendas;
    private List<Atuendo> atuendosMostrados;
    private List<Usuario> usuarios;


    Guardaropa(ArrayList<Prenda> prendas) {
        this.prendas = prendas;
        this.atuendosMostrados = new ArrayList<Atuendo>();
        this.usuarios = new ArrayList<Usuario>();
    }

    public void agregarUsuario(Usuario usuario1) {
        this.usuarios.add(usuario1);
    }

    public void quitarUsuario(Usuario usuario1) {
        this.usuarios = this.usuarios.stream().filter(usuario -> usuario != usuario1).collect(Collectors.toList());
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Atuendo sugerirAtuendo() {
        Atuendo atuendo;
        if (atuendosMostrados.size() != this.cantidadDeAtuendosPosibles()) {
            atuendo = this.generarAtuendo();
            if (this.estaEnLaLista(atuendo)) {
                this.sugerirAtuendo();
            } else {
                this.atuendosMostrados.add(atuendo);
                return atuendo;
            }
        }
        return this.atuendosMostrados.get((int) (Math.random() * this.atuendosMostrados.size()));
    }
    public Atuendo sugerirAtuendo(Pronostico pronostico, Evento evento, Usuario usuario) {
        Atuendo atuendo = this.sugerirAtuendo();

        if(atuendo.tieneEstiloEnParticular(evento.estilo()) && atuendo.compatibleConTiempo(pronostico,usuario))
        {
            return atuendo;
        }
        else return sugerirAtuendo(pronostico, evento, usuario);

    }
    public boolean estaEnLaLista(Atuendo atuendo) {
        return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo1.somosIguales(atuendo));
    }

    public Atuendo generarAtuendo() {
        Atuendo atuendo = new Atuendo();
        this.prendasPorCategoria().forEach(categoria -> atuendo.agregarPrenda(categoria.get((int) (Math.random() * categoria.size()))));
        return atuendo;
    }

    public Atuendo generarAtuendo(ArrayList<NivelDeCalor> nivelesDeCalor, Evento evento){
        Atuendo atuendo = new Atuendo();
            nivelesDeCalor.forEach(nivelDeCalor ->
                   this.agregarPrendasAAtuendo(
                           this.generaraSeccionDeAtuendo(nivelDeCalor.getCategoria(),nivelDeCalor.getNivelDeCalor(),evento)
                            ,atuendo));
        return atuendo;
    }
    // Todo esto va a desaparecer con el Decorator, por motivos de tiempo y parciales a la vista lo hacemos de esta forma
    private Atuendo agregarPrendasAAtuendo(List<Prenda> prendas, Atuendo atuendo){
        prendas.forEach(prenda -> atuendo.agregarPrenda(prenda));
        return atuendo;
    }
    private List<Prenda> generaraSeccionDeAtuendo(Categoria categoria, int nivelDeCalor, Evento evento) {
        ArrayList<Prenda> prendas = new ArrayList<Prenda>();
        for (int i = 0; i < nivelDeCalor; i++) {
            prendas.add(prendaMasSuperponible(prendasMasIdeales(evento, prendasSuperponibles(prendas, this.prendasPorCategoria(categoria.getCategoria(),this.prendas)))));
        }
        return prendas;
    }
    private Prenda prendaMasSuperponible(List<Prenda> prendasAFiltrar){
        int maxValue = prendasAFiltrar.stream().map(prenda -> prenda.cantidadSuperponibles()).max(Comparator.naturalOrder()).get();
        return prendasAFiltrar.stream().filter(prenda -> prenda.cantidadSuperponibles() == maxValue).collect(Collectors.toList()).get(0);
    }
    private List<Prenda> prendasMasIdeales(Evento evento, List<Prenda> prendasAFiltrar){
        return prendasAFiltrar.stream()
                .filter(prenda -> prenda.getEstilo() == evento.estilo())
                .collect(Collectors.toList());
    }
    private List<Prenda> prendasSuperponibles(ArrayList<Prenda> prendas, List<Prenda> prendasAFiltrar){
              return prendasAFiltrar.stream().filter(prenda -> prendas.stream().allMatch(prenda1 -> prenda1.esSuperponible(prenda))).collect(Collectors.toList());
    }
    // hasta acÃ¡ hay que sacar todo

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
    public List<String> tiposCategorias(List<Prenda> prendas){
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
    public void bloquearPrenda(int i) {  //Bloquea una prenda
    	this.prendas.get(i).bloquearse();
    }
    public void bloquearExcedente(int excedente) { //Bloquea un determinado excedente de prendas
    	if(this.cantidadDePrendas()>excedente) { 
    		for(int j= this.cantidadDePrendas()-1; j>=excedente; j--) {
    			this.bloquearPrenda(j);}
    	}
    }
    public void desbloquearTodo() { //Desbloquea todas las prendas que tiene dentro
    	this.prendas.forEach(prenda->prenda.desbloquearse());
    	}
    public boolean puedoCrear(Atuendo atuendo){
        return atuendo.todasLasPrendas().stream().allMatch(prenda -> this.prendas.stream().anyMatch(prenda1 -> prenda.somosIguales(prenda1)));
    }
    /* Nueva Sugerencia de atuendos*/
    

    public boolean yaMostreAtuendo(Atuendo atuendo){
        return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo.somosIguales(atuendo1));
    }


    public void agregarPrendas(ArrayList<Prenda> prendas){
        prendas.forEach(prenda -> this.agregarPrenda(prenda));
    }
    Guardaropa(){
        this.prendas = new ArrayList<Prenda>();
        this.atuendosMostrados = new ArrayList<Atuendo>();
        }

        //---- observers methods
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
    @Override
    public void downdateAceptarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) {

    }
    @Override
    public void updateRechazarSugerencia(Atuendo atuendo) {

    }
    @Override
    public void downdateRechazarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) {

    }
}
