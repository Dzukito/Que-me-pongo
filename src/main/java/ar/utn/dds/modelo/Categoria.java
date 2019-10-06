package ar.utn.dds.modelo;
import javax.persistence.*;


@Entity
@Table(name="categoria")
public enum Categoria {
    TORSO("Torso",5,1),
    PARTEINFERIOR("Parte Inferior",3,1),
    CALZADO("Calzado",3,1),
    ACCESORIOS("Accesorios",10,0),
    CUELLO("Cuello",1,0),
    MANOS("Manos",1,0),
    CABEZA("Cabeza",1,0);
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
