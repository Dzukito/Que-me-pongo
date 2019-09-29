package ar.utn.dds.modelo;

import java.util.*;
import java.util.stream.Collectors;

public class Guardaropa implements AceptarSuegerenciaObservador, RechazarSugerenciaObservador {
    private List<Prenda> prendas;
    private List<Atuendo> atuendosMostrados;
    private List<Usuario> usuarios;
    private ArrayList<ConjuntosPredefinidos> conjuntosPredefinidos;



    //Metodos-que-hay-que-borrar----------------------------------
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
    }//borrar y hacer mockitos con sus Test
    public Atuendo generarAtuendo() {
        Atuendo atuendo = new Atuendo();
        this.prendasPorCategoria().forEach(categoria -> atuendo.agregarPrenda(categoria.get((int) (Math.random() * categoria.size()))));
        return atuendo;
    }//borrar y hacer mockitos con sus Test
    public int cantidadDeAtuendosPosibles(){
        int j =1;
        for(int i = 0; i<this.prendasPorCategoria().size();i=i+1 ){
            j=this.prendasPorCategoria().get(i).size()*j;
        }
        return j;
    }
    //Metodos-que-no-se-usan--------------------------------------
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
    //Metodos-privados--------------------------------------------
    private ArrayList<ArrayList<Prenda>> prendasPorCategoria(){
        ArrayList<ArrayList<Prenda>> retorno = new ArrayList<ArrayList<Prenda>>();
        for (int i = 0;i< this.cantidadCategorioas(prendas);i=i+1){
            retorno.add((ArrayList<Prenda>) this.prendasPorCategoria(this.tiposCategorias(prendas).get(i),prendas));
        }
        return retorno;
    }
    //Metodos-publicos--------------------------------------------
    //SugerenciaDeAtuendo-----------------------------------------
    public Atuendo sugerirAtuendo(Pronostico pronostico, Evento evento, Usuario usuario) {
        Atuendo atuendo = this.sugerirAtuendo();
        ArrayList<NivelDeCalor> nivelesDeCalor = pronostico.nivelesDeCalorRequeridos();
        usuario.getSensibilidad().ajustarNivelesDeCalor(nivelesDeCalor);

        if(atuendo.tieneEstiloEnParticular(evento.getEstilo()) && atuendo.compatibleConTiempo(pronostico,usuario)) return atuendo;
        else return sugerirAtuendo(pronostico, evento, usuario);
    }
    public boolean estaEnLaLista(Atuendo atuendo) {
        return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo1.somosIguales(atuendo));
    }
    public List<Prenda> prendasPorCategoria(String categoria, List<Prenda> prendas){
        return prendas.stream().filter(prenda -> prenda.getCategoria().equals(categoria)).collect(Collectors.toList());
    }
    public List<String> tiposCategorias(List<Prenda> prendas){
        return prendas.stream().map(prenda -> prenda.getCategoria()).distinct().collect(Collectors.toList());
    }
    public int cantidadCategorioas(List<Prenda> prendas){
        return this.tiposCategorias(prendas).size();
    }
    //------------------------------------------------------------
    public ArrayList<Atuendo> atuendosGenerados(){
        return (ArrayList<Atuendo>) this.atuendosMostrados;
    }
    public int cantidadAtuendosGenerados() {
        return this.atuendosMostrados.size();
    }
    public int cantidadDePrendasEnCategoria(String categoria) {
        return this.prendasPorCategoria(categoria, this.prendas).size();
    }
    public void bloquearPrenda(int i) {  //Bloquea una prenda
    	this.prendas.get(i).bloquearse();
    }
    public int cantidadDePrendas(){
        return this.prendas.size();
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
        return atuendo.getPrendas().stream().allMatch(prenda -> this.prendas.stream().anyMatch(prenda1 -> prenda.somosIguales(prenda1)));
    }
    public boolean atuendoMostrado(Atuendo atuendo){
        return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo.somosIguales(atuendo1));
    }



    public void agregarPrenda(Prenda prenda) {
        this.prendas.add(prenda);
    }
    public void agregarPrendas(ArrayList<Prenda> prendas){
        prendas.forEach(prenda -> this.agregarPrenda(prenda));
    }
    public void agregarUsuario(Usuario usuario1) {
        this.usuarios.add(usuario1);
    }
    public void quitarUsuario(Usuario usuario1) {
        this.usuarios = this.usuarios.stream().filter(usuario -> usuario != usuario1).collect(Collectors.toList());
    }

    //Getters-y-Setters------------------------------------------------
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public List<Prenda> getPrendas() {
        return this.prendas;
    }
    //Constructores--------------------------------------------
    Guardaropa(ArrayList<Prenda> prendas) {
            this.prendas = prendas;
            this.atuendosMostrados = new ArrayList<Atuendo>();
            this.usuarios = new ArrayList<Usuario>();
        }
    Guardaropa(){
        this.prendas = new ArrayList<Prenda>();
        this.atuendosMostrados = new ArrayList<Atuendo>();
        }
    //observers-methods----------------------------------------
    @Override
    public void updateAceptarSugerencia(Atuendo atuendo) {
        if(this.puedoCrear(atuendo)){
            if(this.atuendoMostrado(atuendo)){
                this.atuendosMostrados.stream().filter(atuendo1 -> this.atuendoMostrado(atuendo)).collect(Collectors.toList()).get(0).agregarUsabilidad();
            }else{
                this.atuendosMostrados.add(atuendo);
                this.atuendosMostrados.stream().filter(atuendo1 -> this.atuendoMostrado(atuendo)).collect(Collectors.toList()).get(0).agregarUsabilidad();
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
