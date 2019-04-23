package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private List<Guardaropa> roperos;
    private String userName;

    public int cantidadPrendas(int guardaropa){
        return this.roperos.get(guardaropa).cantidadDePrendas();
    }
    private void inicializarGuardaropa(Guardaropa guardaropa){
        this.roperos.add(guardaropa);
    }
    Usuario(String userName){
        this.userName = userName;
        this.roperos = new ArrayList<Guardaropa>();
    }
    Usuario(String userName, Guardaropa ropero){
        this.inicializarGuardaropa(ropero);
    }
}
