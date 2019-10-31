package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
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
		
		@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
		@JoinColumn(name = "id_sexo")
        private Sexo sexo;

        //Not-used-methods---------------------------------------------------
        public boolean mismoNivelDeCalor(List<NivelDeCalor> nivelesDeCalorDeOtro){
                return this.nivelesDeCalor.stream().allMatch(nivelDeCalor -> nivelesDeCalorDeOtro.stream().anyMatch(nivelDeCalor1 -> nivelDeCalor.hashCode() == nivelDeCalor1.hashCode()));
        }
        //Private-methods----------------------------------------------------
        //Public-methods-----------------------------------------------------
        public Atuendo cargarAtuendo(ArrayList<Prenda> prendas){
                Atuendo atuendo = new Atuendo();
                this.conjunto.stream().forEach(tipoPrenda ->
                        atuendo.agregarPrenda(
                                prendas.stream().filter(prenda -> prenda.getTipoDePrenda().mismoTipoDePrenda(tipoPrenda)).collect(Collectors.toList()).get(1)
                        ));
                return atuendo;
        }
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
        public int nivelDeCalor(Categoria categoria){ return this.conjunto.stream().filter(tipoPrenda -> tipoPrenda.categoria() == categoria.getCategoria()).collect(Collectors.toList()).size(); }
        public List<TipoPrenda> getConjunto() {
                return conjunto;
        }
        public Sexo getSexo() {
                return sexo;
        }
        @Override
        public int hashCode(){
                return Objects.hash(conjunto, sexo);
        }


        //Builders-----------------------------------------------------------
        ConjuntosPredefinidos(List<TipoPrenda> conjunto){
                this.conjunto = conjunto;
                this.sexo = Sexo.UNISEX;
                this.nivelesDeCalor = new ArrayList<NivelDeCalor>();
        }

        ConjuntosPredefinidos(List<TipoPrenda> conjunto, Sexo sexo){
                this.conjunto = conjunto;
                this.sexo = sexo;
        }
}


