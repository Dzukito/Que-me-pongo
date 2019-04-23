package ar.utn.dds.modelo;

import java.util.List;
import java.util.Set;

public class TipoPrenda {
    private String nombrePrenda;
    private Set<Material> materiales;
    TipoPrenda(String nombrePrenda, Set<Material> materiales){
        this.nombrePrenda = nombrePrenda;
        this.materiales = materiales;
    }
}
