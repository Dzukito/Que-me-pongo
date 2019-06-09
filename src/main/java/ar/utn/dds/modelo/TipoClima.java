package ar.utn.dds.modelo;

import java.util.ArrayList;
import java.util.List;

public enum TipoClima {
    ArrayList<String> lluviosoSatisface, nubladoSatisface;

    LLUVIOSO("Lluvioso",new ArrayList<String>(["Piloto","Paraguas"]),[]),
    NUBLADO("Nublado",[],[]),
    NEVANDO("NEVANDO",["Bufanda"],[]),
    SOLEADO("Soleado",[],[]),
    VENTOSO("VENTOSO",[],[]);


    private final String tipoClima;
    private final List<String> prendasSatisfacen;
    private final List<String> prendasNegadas;
    TipoClima(String tipoClima,ArrayList<String> prendasSatisfacen,ArrayList<String> prendasNegadas){
        this.tipoClima = tipoClima;
        this.prendasNegadas = prendasNegadas;
        this.prendasSatisfacen = prendasSatisfacen;

    }
}
