package ar.utn.dds.modelo;

import java.util.*;
import javax.persistence.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Entity
@Table(name="guardaropa")
public class Guardaropa implements AceptarSuegerenciaObservador, RechazarSugerenciaObservador {
    
	@Id
	private long id_guardaropa;
	
	private List<Prenda> prendas;
    private List<Atuendo> atuendosMostrados;
    private List<Usuario> usuarios;
    private ArrayList<ConjuntosPredefinidos> conjuntosPredefinidos;



    //Metodos-que-hay-que-borrar----------------------------------
    //Metodos-que-no-se-usan--------------------------------------
    public boolean estaEnLaLista(Atuendo atuendo) {
        return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo1.somosIguales(atuendo));
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
    //Metodos-privados--------------------------------------------
    private ArrayList<Atuendo> atuendosUtilez(ArrayList<Atuendo> atuendos, Usuario usuario, Evento evento, Pronostico pronostico, ArrayList<NivelDeCalor> nivelesDeCalor){
        return (ArrayList<Atuendo>) atuendos.stream().filter(atuendo ->
                atuendo.tieneEstiloEnParticular(evento.getEstilo())
                        && atuendo.compatibleConTiempo(pronostico,usuario)
                        && atuendo.satisfaceNivelesDeCalor(nivelesDeCalor))
                .collect(Collectors.toList());
    }
    //Metodos-publicos--------------------------------------------
        //SugerenciaDeAtuendo-----------------------------------------
    public Atuendo sugerirAtuendo(Pronostico pronostico, Evento evento, Usuario usuario) {
        ArrayList<NivelDeCalor> nivelesDeCalor = usuario.getSensibilidad().ajustarNivelesDeCalor(pronostico.nivelesDeCalorRequeridos());
        ArrayList<Atuendo> atuendosMostradosUtilez = this.atuendosUtilez( (ArrayList<Atuendo>)this.atuendosMostrados,usuario,evento,pronostico,nivelesDeCalor);
        if(!atuendosMostradosUtilez.isEmpty()) {
            atuendosMostradosUtilez.get(1);
            //max(Comparator.comparing(atuendo -> atuendo.promedioCalificaciones(usuario,evento,pronostico)));
        }else{
            return this.atuendosUtilez((ArrayList<Atuendo>) this.conjuntosPredefinidos.stream()
                    .map(conjuntoPredefinido -> conjuntoPredefinido.cargarAtuendo((ArrayList<Prenda>) this.prendas)).collect(Collectors.toList()),
                    usuario,evento,pronostico,nivelesDeCalor).get(1);
        }
        return null;
    }
    public List<String> tiposCategorias(List<Prenda> prendas){ return prendas.stream().map(prenda -> prenda.getCategoria()).distinct().collect(Collectors.toList()); }
    public int cantidadCategorioas(List<Prenda> prendas){ return this.tiposCategorias(prendas).size(); }
    public int cantidadAtuendosGenerados() { return this.atuendosMostrados.size(); }
    public void bloquearPrenda(int i) {	this.prendas.get(i).bloquearse(); }
    public int cantidadDePrendas(){ return this.prendas.size(); }
    public void bloquearExcedente(int excedente) { if(this.cantidadDePrendas()>excedente) { for(int j= this.cantidadDePrendas()-1; j>=excedente; j--) { this.bloquearPrenda(j);} } }
    public void desbloquearTodo() { this.prendas.forEach(prenda->prenda.desbloquearse()); }
    public boolean puedoCrear(Atuendo atuendo){ return atuendo.getPrendas().stream().allMatch(prenda -> this.prendas.stream().anyMatch(prenda1 -> prenda.somosIguales(prenda1))); }
    public boolean atuendoMostrado(Atuendo atuendo){ return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo.somosIguales(atuendo1)); }
    public int cantidadDePrendasEnCategoria(String categoria) { return this.prendas.stream().filter(prenda -> prenda.getCategoria() == categoria).collect(Collectors.toList()).size(); }
    //Getters-y-Setters------------------------------------------------
    public ArrayList<Atuendo> atuendosGenerados(){ return (ArrayList<Atuendo>) this.atuendosMostrados; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Prenda> getPrendas() { return this.prendas; }
    public void agregarPrenda(Prenda prenda) { this.prendas.add(prenda); }
    public void agregarPrendas(ArrayList<Prenda> prendas){ prendas.forEach(prenda -> this.agregarPrenda(prenda)); }
    public void agregarUsuario(Usuario usuario1) { this.usuarios.add(usuario1); }
    public void quitarUsuario(Usuario usuario1) { this.usuarios = this.usuarios.stream().filter(usuario -> usuario != usuario1).collect(Collectors.toList()); }
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
