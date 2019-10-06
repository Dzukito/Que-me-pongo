package ar.utn.dds.modelo;

import sun.reflect.generics.repository.MethodRepository;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name="centroClimatologico")
public class CentroClimatologico {
	
	@Id
	private long id_centroClimatologico;
	
    private Meteorologo meteorologo;
    private List<Meteorologo> meteorologos;

    public void agregarMeteorolo(Meteorologo meteorologo){
        if(this.meteorologo == null){
            this.meteorologo = meteorologo;
        }
        this.meteorologos.add(meteorologo);
    }
    public void cambiarMeteorologo(){
        this.meteorologo = this.meteorologos.stream().filter(meteorologo1 -> meteorologo1 !=this.meteorologo).collect(Collectors.toList()).get((int) (Math.random() * this.meteorologos.size()-1));
    }
    public Meteorologo getMeteorologo() {
        return meteorologo;
    }

    //Constructores-------------------------------------------

    CentroClimatologico(){
        this.meteorologo = new MeteorologoAccuWeatherAdapter();
        this.meteorologos = new ArrayList<Meteorologo>();
    }
    CentroClimatologico(Meteorologo meteorologo){
        this.meteorologo = meteorologo;
        this.meteorologos = new ArrayList<Meteorologo>();
        this.meteorologos.add(meteorologo);
    }
    CentroClimatologico(Meteorologo meteorologo, ArrayList<Meteorologo> meteorologos){
        this.meteorologos = meteorologos;
        this.meteorologo = meteorologo;
    }
}
