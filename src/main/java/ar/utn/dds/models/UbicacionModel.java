package ar.utn.dds.models;

import java.util.List;

import ar.utn.dds.modelo.clases.Ubicacion;

public class UbicacionModel extends Model{

    private static UbicacionModel instance;

    public static UbicacionModel getInstance() {
        if(instance == null){
            instance = new UbicacionModel();
        }
        return instance;
    }

    @Override
    public List<Ubicacion> buscarTodos(){
        return entityManager().createQuery("from Ubicacion").getResultList();
    }

    @Override
    public Ubicacion buscar(long id){
        return entityManager().find(Ubicacion.class, id);
    }
}
