package ar.utn.dds.modelo.ropa;
import javax.persistence.*;



public enum Categoria {
    TORSO("TORSO",5,1),
    PARTEINFERIOR("PARTEINFERIOR",3,1),
    CALZADO("CALZADO",3,1),
    ACCESORIOS("ACCESORIOS",10,0),
    CUELLO("CUELLO",1,0),
    MANOS("MANOS",1,0),
    CABEZA("CABEZA",1,0);
	
	
	private long id_categoria;
	
    private String categoria;
	
    private int nivelCalorMinimo;
	
    private int nivelCalorMaximo;

    public int getPuntoMedio(){ return ((this.nivelCalorMaximo/2)-(this.nivelCalorMaximo%2)); }
    public int getNivelCalorMinimo(){
        return this.nivelCalorMinimo;
    }
    public int getNivelCalorMaximo(){
        return this.nivelCalorMinimo;
    }
    public String getCategoria(){
        return this.categoria;
    }
    Categoria(String categoria, int nivelCalorMaximo,int nivelCalorMinimo){
        this.categoria = categoria;
    }
}
