package ar.utn.dds.modelo.clima;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;


//@Entity
//@Table(name="tipoClima")
public enum TipoClima {

    LLUVIOSO("Lluvioso",new ArrayList<String>(Arrays.asList("Paraguas","Piloto","Rompevientos")),new ArrayList<String>()),
    NUBLADO("Nublado",new ArrayList<String>(),new ArrayList<String>()),
    NEVANDO("NEVANDO",new ArrayList<String>(Arrays.asList("Bufanda","Guantes","Gorro")),new ArrayList<String>(Arrays.asList("Paraguas"))),
    SOLEADO("Soleado",new ArrayList<String>(Arrays.asList("Gorro","Lentes")),new ArrayList<String>(Arrays.asList("Paraguas"))),
    VENTOSO("VENTOSO",new ArrayList<String>(Arrays.asList("Rompevientos","Piloto")),new ArrayList<String>(Arrays.asList("Pollera","Gorro","Paraguas")));

//	@Id
//	@GeneratedValue
//	private long id_tipoClima;
	
//	@Column(name = "tipoClima")
    private final String tipoClima;
	
    private final List<String> prendasSatisfacen;
    private final List<String> prendasNegadas;
    
    
    TipoClima(String tipoClima,ArrayList<String> prendasSatisfacen,ArrayList<String> prendasNegadas){
        this.tipoClima = tipoClima;
        this.prendasNegadas = prendasNegadas;
        this.prendasSatisfacen = prendasSatisfacen;

    }
    public List<String> prendasSatisfacen(){
        return this.prendasSatisfacen;
    }
    public List<String> prendasNegadas(){
        return this.prendasNegadas;
    }
    public boolean esUnaPrendaQueSatisface(String prenda){
        return this.prendasSatisfacen.contains(prenda);
    }
    public boolean esUnaPrendaNegada(String prenda) {
        return this.prendasNegadas.contains(prenda);
    }
}
