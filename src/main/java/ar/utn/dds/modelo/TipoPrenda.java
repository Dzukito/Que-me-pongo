package ar.utn.dds.modelo;

import java.util.Set;

public class TipoPrenda {
    private String tipo;
    private Categoria categoria;
    private Set<Material> materiales;

    public String categoria(){
        return this.categoria.getCategoria();
    }
    public String tipo(){
        return this.tipo;
    }
    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
    }

    public Set<Material> materiales() {
        return materiales;
    }
}
