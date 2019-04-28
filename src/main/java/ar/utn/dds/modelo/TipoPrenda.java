package ar.utn.dds.modelo;

import java.util.Set;

public class TipoPrenda {
    private String nombrePrenda;
    private String categoria;
    private Set<Material> materiales;

    public String categoria(){
        return this.categoria;
    }
    public String tipo(){
        return this.nombrePrenda;
    }
    TipoPrenda(String nombrePrenda, Set<Material> materiales){
        this.nombrePrenda = nombrePrenda;
        this.materiales = materiales;
    }
}
