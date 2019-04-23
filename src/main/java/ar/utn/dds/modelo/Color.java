package ar.utn.dds.modelo;

public enum Color {
    Rojo("Rojo"),
    Azul("Azul"),
    Amarillo("Amarillo"),
    Blanco("Blanco"),
    Negro("Negro"),
    Violeta("Violeta"),
    Verde("Verde"),
    Naranja("Naranja");

    private final String color;

    Color(String color){
        this.color = color;
    }
}
