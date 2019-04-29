package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private List<Guardaropa> roperos;
    private String userName;

    public int cantidadPrendas(int guardaropa){
        return this.roperos.get(guardaropa).cantidadDePrendas();
    }
    public Guardaropa guardaropa(int i){
        return this.roperos.get(i);
    }
    public ArrayList<Atuendo>  atuendosGuardaropa(Guardaropa ropero){
        return ropero.atuendosDisponibles();
    }
    public int cantidadAtuendos(int i){
        return roperos.get(i).cantidadAtuendosDisponibles();
    }
    public void agregarPreda(Prenda prenda, Guardaropa ropero){
        ropero.agregarPrenda(prenda);
    }
    public void agregarRopero(Guardaropa ropero){
        this.roperos.add(ropero);
    }
    Usuario(String userName, ArrayList<Guardaropa> roperos){
        this.userName = userName;
        this.roperos = roperos;
    }
}
