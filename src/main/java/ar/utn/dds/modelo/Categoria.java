package ar.utn.dds.modelo;

public enum Categoria {
    TORSO("Torso"),
    PARTEINFERIOR("Parte Inferior"),
    CALZADO("Calzado"),
    ACCESORIOS("Accesorios"),
    CUELLO("Cuello"),
    MANOS("Manos"),
    CABEZA("Cabeza");
    private String categoria;

    public String getCategoria(){
        return this.categoria;
    }

    Categoria(String categoria){
        this.categoria = categoria;
    }
}
