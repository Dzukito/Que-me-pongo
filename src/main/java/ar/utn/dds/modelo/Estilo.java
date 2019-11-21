package ar.utn.dds.modelo;
import javax.persistence.*;


public enum Estilo {
    ELEGANTE,
    ELEGANTSPORT,
    DEPORTIVO,
    ENTRECASA,
    NAVIDENIO,
    NORMAL,
    PLAYERO;
	
	
	//lo transformo en clase porque prenda necesita un array de estilos, pero hibernate no permite del todo mapear array de enums
	
	private long id_estilo;

    private Estilo estilo;
	
	 public Estilo getEstilo(){
	        return this.estilo;}
	
}
