package ar.utn.dds.modelo.interfaces;

import ar.utn.dds.modelo.clases.Atuendo;

public interface RechazarSugerenciaObservador {
    void updateRechazarSugerencia(Atuendo atuendo);
    void downdateRechazarSugerencia(Atuendo atuendo, Atuendo atuendoViejo);
}
