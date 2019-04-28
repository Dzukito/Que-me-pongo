package ar.utn.dds.modelo;

public class Categoria {
    private TipoPrenda tipoPrenda;
    private Material material;

    public String tipo(){
        return this.tipoPrenda.tipo();
    }
    public String categoria(){
        return this.tipoPrenda.categoria();
    }
    public Material material(){
        return this.material();
    }

    public Categoria(TipoPrenda tipoPrenda, Material material)
    {
        this.tipoPrenda =  tipoPrenda;
        this.material = material;
    }
}
