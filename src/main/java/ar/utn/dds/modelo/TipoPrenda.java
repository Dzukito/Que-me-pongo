package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;

import java.util.Set;

public class TipoPrenda {
    private String tipo;
    private Categoria categoria;
    private Set<Material> materiales;

    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
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
