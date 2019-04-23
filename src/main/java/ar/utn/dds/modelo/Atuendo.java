package ar.utn.dds.modelo;

import com.sun.javafx.collections.MappingChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Atuendo {
    private MappingChange.Map<Categoria,Prenda> prendas;
    Atuendo(List<Prenda> prendas){
        prendas = new HashMap<Categoria,Prenda>();
        ((HashMap) prendas).putAll(prendas.stream().map(prenda -> (prenda.categoria(),prenda)));
    }
}
