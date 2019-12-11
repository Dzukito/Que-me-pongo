package ar.utn.dds.modelo.clases;


import ar.utn.dds.excepciones.EsaPrendaYaLaTengo;
import javax.persistence.*;
import ar.utn.dds.excepciones.noPuedeSuperponerse;
import ar.utn.dds.modelo.clima.Pronostico;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.ropa.TipoPrenda;
import ar.utn.dds.modelo.clima.NivelDeCalor;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Entity
@Table(name="atuendo")
public class Atuendo {
	
	@Id
	@GeneratedValue
	private long id_atuendo;

	@ManyToMany
	@JoinTable(name="map_prenda_atuendo", joinColumns={@JoinColumn(name="id_atuendo")}, inverseJoinColumns={@JoinColumn(name="id_prenda")})
    private List<Prenda> prendas;
    @Column(name = "usabilidad")
    private int usabilidad;
    @OneToMany(mappedBy = "atuendo", cascade = {CascadeType.ALL})
    private List<CalificacionAtuendo> calificaciones;


    //Metodos-no-se-usan--------------------------------------------------------------------
    public List<String> TiposDePrendas() { return prendas.stream().map(prenda -> prenda.getTipoDePrenda().categoria()).collect(Collectors.toList()); }
    public int nivelDeCalorTotal(){
        return prendas.size();
    }
    public boolean tieneEstilo(){ return this.prendas.stream().map(prenda -> prenda.getEstilo()).distinct().collect(Collectors.toList()).size() == 1; }
    public void noPuedoAgregarPrenda(Prenda prenda){ if(this.prendas.stream().anyMatch(prenda1 -> !prenda1.esSuperponible(prenda))) {throw new noPuedeSuperponerse();} }
    public List<String> NombresPrendas() { return prendas.stream().map(prenda -> prenda.getNombrePrenda()).collect(Collectors.toList()); }

    //Metodos-privados----------------------------------------------------------------------
    private void esaPrendaYaLaTengo(Prenda nuevaPrenda){
        if (this.prendas.stream().anyMatch(prenda -> prenda.getTipoDePrenda().tipo() == nuevaPrenda.getTipoDePrenda().tipo() )) {
            throw new EsaPrendaYaLaTengo();
        }
    }
    private boolean tengoTusPrendas(Atuendo atuendo){ return this.prendas.stream().allMatch(prenda -> atuendo.tengoPrenda(prenda)); }

    //Metodos-publicos----------------------------------------------------------------------
    public boolean compatibleConTiempo(Pronostico pronostico, Usuario usuario) {
        return !prendas.stream().anyMatch(prenda->pronostico.prendasNegadas().contains(prenda.tipo())) &&
                prendas.stream().anyMatch(prenda->pronostico.prendasSatisfacen().contains(prenda.tipo()));
    }
    public boolean satisfaceNivelesDeCalor(ArrayList<NivelDeCalor>nivelesDeCalor){
        return nivelesDeCalor.stream().allMatch( nivelDeCalor ->
                this.prendas.stream().filter(
                    prenda -> prenda.getTipoDePrenda().getCategoria() == nivelDeCalor.getCategoria())
                    .collect(Collectors.toList()).size() == nivelDeCalor.getNivelDeCalor()
        );
    }
    public boolean tieneEstiloEnParticular(Estilo estilo){
        return this.prendas.stream().anyMatch(prenda -> prenda.tieneEstilo(estilo));
    }
    public void cambiarPrenda(Prenda prendaNueva){
        this.prendas = this.prendas.stream().filter(prenda -> prenda.getCategoria() != prendaNueva.getCategoria()).collect(Collectors.toList());
        this.prendas.add(prendaNueva);
    }
    public boolean somosIguales(Atuendo atuendo){
       return (this.tengoTusPrendas(atuendo) && atuendo.tengoTusPrendas(this));
    }
    public boolean tengoPrenda(Prenda prenda){
        return this.prendas.stream().anyMatch(prenda1 -> prenda1.somosIguales(prenda));
    }
    public float promedioCalificaciones(Usuario usuario, Evento evento, Pronostico pronostico) {
        AtomicReference<Integer> w= new AtomicReference<>(0);
        this.getCalificacionesPor(usuario, pronostico, evento).stream().map(calificaicon -> w.updateAndGet(v -> v + calificaicon));
        return  w.get() / this.getCalificacionesPor(usuario, pronostico, evento).size();
    }

    //Getters-y-Setters----------------------------------------------------------------------
    public void agregarPrenda(Prenda prenda){
        try {
            this.esaPrendaYaLaTengo(prenda);
            this.prendas.add(prenda);

        }catch (EsaPrendaYaLaTengo e){
            this.cambiarPrenda(prenda);
        }
    }
    public void agregarUsabilidad() { this.usabilidad = this.usabilidad +1; }
    public int nivelDeCalor(Categoria categoria){ return this.prendas.stream().filter(prenda -> prenda.getCategoria() == categoria.getCategoria()).collect(Collectors.toList()).size(); }
    public List<Integer> getCalificacionesPor(Usuario usuario, Pronostico pronostico, Evento evento){
        return  this.calificaciones.stream()
                .filter(calificacionAtuendo -> calificacionAtuendo.mismasCondiciones(usuario,evento,pronostico))
                .map(calificacionAtuendo -> calificacionAtuendo.getCalificacion())
                .collect(Collectors.toList());
    }
    public List<CalificacionAtuendo> getCalificaciones() { return this.calificaciones; }
    public List<Prenda> getPrendas(){ return this.prendas; }
    public ArrayList<TipoPrenda> getTiposDePrenda(){ return (ArrayList<TipoPrenda>) this.getPrendas().stream().map(prenda -> prenda.getTipoDePrenda()).collect(Collectors.toList()); }
    public Prenda getPrenda(String categoria) { return this.prendas.stream().filter(prenda -> prenda.getCategoria() == categoria).collect(Collectors.toList()).get(0); }
    public List<List<String>> getImagenes() { return prendas.stream().map(prenda -> prenda.getFotografo().imagenes()).collect(Collectors.toList()); }
    public void addCalificacion(CalificacionAtuendo calif) { this.calificaciones.add(calif); }
    //Constructores--------------------------------------------------
    public Atuendo(){
        this.prendas = new ArrayList<Prenda>();
        this.usabilidad = 0;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
    }
    public Atuendo(List<Prenda> prendas){
        this.prendas = prendas;
        this.usabilidad = 0;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
    }
    Atuendo(List<Prenda> prendas, int usabilidad){
        this.prendas = prendas;
        this.usabilidad = usabilidad;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
    }
    Atuendo(List<Prenda> prendas, int usabilidad, CalificacionAtuendo calificacion){
        this.prendas = prendas;
        this.usabilidad = usabilidad;
        this.calificaciones = new ArrayList<CalificacionAtuendo>();
        this.calificaciones.add(calificacion);
    }
}