package ar.utn.dds.modelo;

public class CalificacionAtuendo {
    private Pronostico pronostico;
    private Evento evento;
    private Usuario usuario;
    private int calificacion; // del 1-10 si es muy baja no le abrigo lo suficiente, si es mucho abrigo

    CalificacionAtuendo(){}
    CalificacionAtuendo(Pronostico pronostico, Evento evento, Usuario usuario, int calificacion){
        this.pronostico = pronostico;
        this.evento = evento;
        this.calificacion = calificacion;
        this.usuario = usuario;
    }
    
    public void ajustarPreferencia(Usuario usuario) {
    	if(calificacion<4) 
    		usuario.setSensacionTermica(usuario.getSensacionTermica()-1); //supone que el usuario es mas friolento
    	else if(calificacion>6)
    		usuario.setSensacionTermica(usuario.getSensacionTermica()+1);//supone que es caluroso
    }
    
    /*
     * ajustarPreferencia: Si la puntuacion es <4 le restara un punto de sensacion suponiendo
     * que es mas friolento. Si la puntuacion es >6 considera que es mas caluroso.
     * Si la puntuacion esta entre 4 5 6 el sistema considera que el usuario esta satisfecho 
     * Asi se va actualizando y aprende del usuario ajustando su medidor subjetivo de T°
     */
}
