package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.AlMenosUnColor;
import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;
import ar.utn.dds.excepciones.SoloTieneUnColor;

import java.util.ArrayList;

public class Prenda{
    private ArrayList<Color> colores;
    private TipoPrenda tipoPrenda;
    private String nombrePrenda;
    private Material material;

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
        return this.colores.get(1);
    }
    public  Color colorSecundario(){
        if (this.colores.size() == 1) {
            throw new SoloTieneUnColor();
        }
        return this.colores.get(2);
    }
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> colores, Material material){
        try{
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
            if(tipoPrenda.materiales().contains(material)) {
                throw new ElMaterialNoPerteneceALaPrenda();
            }
            if(colores.isEmpty()){
                throw new AlMenosUnColor();
            }
        }catch (ElMaterialNoPerteneceALaPrenda | AlMenosUnColor e){}
    }
    public String categoria() {
        return this.tipoPrenda.categoria();
    }
}
