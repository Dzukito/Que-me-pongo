package ar.utn.dds.modelo.interfaces;

import ar.utn.dds.modelo.clases.Atuendo;

public interface AceptarSuegerenciaObservador {
    void updateAceptarSugerencia(Atuendo atuendo);
    void downdateAceptarSugerencia(Atuendo atuendo, Atuendo atuendoViejo);
}
