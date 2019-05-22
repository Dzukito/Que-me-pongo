package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.AlMenosUnColor;
import ar.utn.dds.excepciones.SoloTieneUnColor;

import java.util.ArrayList;
import java.util.Objects;

public class Prenda{
    private ArrayList<Color> colores;
    private TipoPrenda tipoPrenda;
    private String nombrePrenda;
    private Material material;
    private Estilo estilo;
    
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> colores, Material material){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.tipoPrenda.perteneceMaterial(material);
            if(colores.isEmpty()){
                throw new AlMenosUnColor();
            }

    }
    public String tipo(){
        return this.tipoPrenda.tipo();
    }
    public String getNombrePrenda(){
        return nombrePrenda;
    }
    public Material getMaterial(){
        return material;
    }
    public Color colorPrimario(){
        return this.colores.get(0);
    }
    public  Color colorSecundario(){
        if (this.colores.size() == 1) {
            throw new SoloTieneUnColor();
        }
        return this.colores.get(1);
    }    
    @Override
    public int hashCode(){
        return Objects.hash(colores, tipoPrenda, nombrePrenda, material);
    }
    public String categoria() {
        return this.tipoPrenda.categoria();
    }
}
