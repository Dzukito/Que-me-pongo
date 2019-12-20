package ar.utn.dds.modelo.ropa;
import javax.persistence.*;


public enum Material {
    PLASTICO("Plastico"),
    CUERO("Cuero"),
    ALGODON("Algodon"),
    ACEROINOXIDABLE("Acero Inoxidable"),
    NYLON("Nylon"),
    POLIESTER("Poliester"),
    MALLA("Malla"),
    LINO("Lino"),
    LYCRA("Lycra"),
    JEAN("JEAN"),
    GABARDINA("Lino"),
    FRANELA("Franela");
	
	
	private long id_material;


    private final String nombre;
	
    Material(String nombre) {
        this.nombre = nombre;
    }
}
