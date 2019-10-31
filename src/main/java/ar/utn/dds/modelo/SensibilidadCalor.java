package ar.utn.dds.modelo;

public class SensibilidadCalor {
    private NivelDeCalor nivelDeCalor;
    private Sensibilidad sensibilidad;

    public NivelDeCalor getNivelDeCalor() { return nivelDeCalor; }
    public void setNivelDeCalor(NivelDeCalor nivelDeCalor) { this.nivelDeCalor = nivelDeCalor; }
    public Sensibilidad getSensibilidad() { return sensibilidad; }
    public void setSensibilidad(Sensibilidad sensibilidad) { this.sensibilidad = sensibilidad; }

    SensibilidadCalor(NivelDeCalor nivelDeCalor, Sensibilidad sensibilidad){
        this.nivelDeCalor = nivelDeCalor;
        this.sensibilidad = sensibilidad;
    }
}
