package ar.utn.dds.modelo.ropa;


public enum Estilo {
    ELEGANTE,
    ELEGANTSPORT,
    DEPORTIVO,
    ENTRECASA,
    NAVIDENIO,
    NORMAL,
    PLAYERO;

	private long id_estilo;

    private Estilo estilo;
	
	 public Estilo getEstilo(){
	        return this.estilo;}
	
}
