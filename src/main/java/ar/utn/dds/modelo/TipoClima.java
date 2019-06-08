package ar.utn.dds.modelo;

public enum TipoClima {
    LLUVIOSO("Lluvioso"),
    NUBLADO("Nublado"),
    SOLEADO("Soleado"),
    VENTOSO("VENTOSO");


    private final String tipoClima;

    TipoClima(String tipoClima){
        this.tipoClima = tipoClima;
    }
}
