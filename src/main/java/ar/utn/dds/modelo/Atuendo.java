package ar.utn.dds.modelo;

//import com.sun.javafx.collections.MappingChange;

import java.util.*;

public class Atuendo {
    private Map<String,Prenda> prendas;

  /*  public ArrayList<Prenda> prendasDelAtuendo(){
        return this.prendas.values();
    }*/
    Atuendo(Map<String,Prenda> prendas){
       this.prendas = prendas;
    }
    
    public Prenda getPrenda(Categoria categoria) {
    	return this.prendas.get(categoria.getCategoria());
    }
}
