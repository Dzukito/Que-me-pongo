package ar.utn.dds.models;

import ar.utn.dds.modelo.clases.Atuendo;

import java.util.List;

public class AtuendoModel extends Model {
    private static AtuendoModel instance;

    public static AtuendoModel getInstance() {
        if(instance == null){
            instance = new AtuendoModel();
        }
        return instance;
    }

    @Override
    public List<Atuendo> buscarTodos(){
        return entityManager().createQuery("from Atuendo").getResultList();
    }

    @Override
    public Atuendo buscar(long id){
        return entityManager().find(Atuendo.class, id);
    }

}
