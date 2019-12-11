package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.clima.NivelDeCalor;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.ropa.TipoPrenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.*;


@Entity
@Table(name="conjuntosPredefinidos")
public class ConjuntosPredefinidos {
		
	@Id
	@GeneratedValue
	private long id_conjuntoPredefinido;
		
    @ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="map_conjunto_tipoPrenda", joinColumns={@JoinColumn(name="id_conjutosPredefinidos")}, inverseJoinColumns={@JoinColumn(name="id_tipoPrenda")})
    private List<TipoPrenda> conjunto;

    @OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_nivelDeCalor")
	private List<NivelDeCalor> nivelesDeCalor;
		
	@JoinColumn(name = "sexo")
    private String sexo;

    //Not-used-methods---------------------------------------------------
        public boolean mismoNivelDeCalor(List<NivelDeCalor> nivelesDeCalorDeOtro){
                return this.nivelesDeCalor.stream().allMatch(nivelDeCalor -> nivelesDeCalorDeOtro.stream().anyMatch(nivelDeCalor1 -> nivelDeCalor.hashCode() == nivelDeCalor1.hashCode()));
        }
    //Metodos privados----------------------------------------------------
    //Metodos publicos-----------------------------------------------------
        public boolean yaExisteConjunto(ArrayList<ConjuntosPredefinidos> conjuntosGuardaropa){
                return conjuntosGuardaropa.stream().anyMatch(conjunto -> this.mismoConjunto(conjunto));
        }
        public boolean mismoConjunto(ConjuntosPredefinidos conjunto2){
                return this.conjunto.stream().allMatch(tipoPrenda -> conjunto2.getConjunto().stream().anyMatch(tipoPrenda1 -> tipoPrenda.mismoTipoDePrenda(tipoPrenda1)));
        }
        public boolean esteAtuendoEsmiConjunto(Atuendo atuendo){
                ConjuntosPredefinidos conjunto = new ConjuntosPredefinidos(atuendo.getTiposDePrenda());
                return this.mismoConjunto(conjunto);
        }
        @Override
        public int hashCode(){
                return Objects.hash(conjunto, sexo);
        }
    // Getters y Setters--------------------------------------------------
        public int nivelDeCalor(Categoria categoria){ return this.conjunto.stream().filter(tipoPrenda -> tipoPrenda.categoria() == categoria.getCategoria()).collect(Collectors.toList()).size(); }
        public List<TipoPrenda> getConjunto() {
            return conjunto;
        }
        public String getSexo() {
            return sexo;
        }
        public Atuendo cargarAtuendo(ArrayList<Prenda> prendas){
        Atuendo atuendo = new Atuendo();
        this.conjunto.stream().forEach(tipoPrenda ->
                atuendo.agregarPrenda(
                        prendas.stream().filter(prenda -> prenda.getTipoDePrenda().mismoTipoDePrenda(tipoPrenda)).collect(Collectors.toList()).get(1)
                ));
        return atuendo;
    }
        public List<NivelDeCalor> getNivelesDeCalor() { return nivelesDeCalor; }

    //Contructores-----------------------------------------------------------
        public ConjuntosPredefinidos(List<TipoPrenda> conjunto){
                this.conjunto = conjunto;
                this.sexo = "unisex";
                this.nivelesDeCalor = new ArrayList<NivelDeCalor>();
        }
        ConjuntosPredefinidos(List<TipoPrenda> conjunto, String sexo){
                this.conjunto = conjunto;
                this.sexo = sexo;
        }
}


