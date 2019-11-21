package ar.utn.dds.modelo;
import javax.persistence.*;


public enum Material {
    PLASTICO("Plastico"),
    CUERO("Cuero"),
    ALGODON("Algodon"),
    ACEROINOXIDABLE("Acero Inoxidable"),
    MALLA("Malla"),
    LINO("Lino"),
    JEAN("JEAN"),
    GABARDINA("Lino"),
    FRANELA("Franela");
	
	
	private long id_material;


    private final String nombre;
	
    Material(String nombre) {
        this.nombre = nombre;
    }
}
