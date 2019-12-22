package ar.utn.dds.modelo.ropa;
import javax.persistence.*;


public enum Material {
    PLASTICO("PLASTICO"),
    CUERO("CUERO"),
    ALGODON("ALGODON"),
    ACEROINOXIDABLE("Acero Inoxidable"),
    NYLON("NYLON"),
    SEDA("SEDA"),
    POLIESTER("POLIESTER"),
    MALLA("MALLA"),
    LINO("LINO"),
    LYCRA("LYCRA"),
    JEAN("JEAN"),
    GABARDINA("GABARDINA"),
    FRANELA("FRANELA");
	
	private long id_material;


    private final String nombre;
	
    Material(String nombre) {
        this.nombre = nombre;
    }
}
