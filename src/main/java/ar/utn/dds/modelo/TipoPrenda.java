package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;

import java.util.*;

public class TipoPrenda {
    private String tipo;
    private Categoria categoria;
    private Set<Material> materiales;
    private Set<TipoPrenda> superponibles;

    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = new HashSet<TipoPrenda>();
    }
    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales,Set<TipoPrenda> superponibles){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = superponibles;
    }
    public boolean esSuperponible(TipoPrenda prenda){
        return superponibles.contains(prenda);
    }
    public String categoria(){
        return this.categoria.getCategoria();
    }
    public String tipo(){
        return this.tipo;
    }
    
    public Set<Material> materiales() {
        return materiales;
    }

    public Boolean perteneceMaterial(Material material) {
        if(!this.materiales().contains(material)) {
            throw new ElMaterialNoPerteneceALaPrenda();
        }
        return true;
    }
}
