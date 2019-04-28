package ar.utn.dds.modelo;

import com.sun.javafx.collections.MappingChange;

import java.util.*;

public class Atuendo {
    private Map<String,Prenda> prendas;

    Atuendo(Map<String,Prenda> prendas){
       this.prendas = prendas;
    }
}
