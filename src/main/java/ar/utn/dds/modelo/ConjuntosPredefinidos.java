package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ConjuntosPredefinidos {
        private ArrayList<TipoPrenda> conjunto;
        private ArrayList<NivelDeCalor> nivelesDeCalor;
        private Sexo sexo;

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
        public boolean mismoNivelDeCalor(ArrayList<NivelDeCalor> nivelesDeCalorDeOtro){
                return this.nivelesDeCalor.stream().allMatch(nivelDeCalor -> nivelesDeCalorDeOtro.stream().anyMatch(nivelDeCalor1 -> nivelDeCalor.hashCode() == nivelDeCalor1.hashCode()));
        }
        public int nivelDeCalor(Categoria categoria){ return this.conjunto.stream().filter(tipoPrenda -> tipoPrenda.categoria() == categoria.getCategoria()).collect(Collectors.toList()).size(); }
        public ArrayList<TipoPrenda> getConjunto() {
                return conjunto;
        }
        public Sexo getSexo() {
                return sexo;
        }
        @Override
        public int hashCode(){
                return Objects.hash(conjunto, sexo);
        }



        ConjuntosPredefinidos(ArrayList<TipoPrenda> conjunto){
                this.conjunto = conjunto;
                this.sexo = new Unisex();
                this.nivelesDeCalor = new ArrayList<NivelDeCalor>();

        }
        ConjuntosPredefinidos(ArrayList<TipoPrenda> conjunto, Sexo sexo){
                this.conjunto = conjunto;
                this.sexo = sexo;
        }
}


