package ar.utn.dds.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name="centroClimatologico")
public class CentroClimatologico {
	
	@Id
	@GeneratedValue
	private long id_centroClimatologico;
	
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "meteorologo_id")
    private Meteorologo meteorologo;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_centroClimatologico")
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
