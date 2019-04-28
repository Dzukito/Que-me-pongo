package ar.utn.dds.modelo;

import java.util.ArrayList;

public class Prenda{
    private ArrayList<Color> colores;
    private Categoria categoria;
    private String nombrePrenda;

    public Color colorPrimario(){
        return this.colores.get(1);
    }
    public  Color colorSecundario(){
        return this.colores.get(2);
        //excepcion
    }
    Prenda(Categoria categoria, String nombrePrenda, Color colorPrimario, ArrayList<Color> colores ){
        this.categoria = categoria;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
    }
    public String categoria() {
        return this.categoria.categoria();
    }
}
