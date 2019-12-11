package ar.utn.dds.models;

import java.util.List;

import ar.utn.dds.modelo.clases.Fotografo;


public class FotografoModel extends Model {
    private static FotografoModel instance;

    public static FotografoModel getInstance() {
        if(instance == null){
            instance = new FotografoModel();
        }
        return instance;
    }

    @Override
    public List<Fotografo> buscarTodos(){
        return entityManager().createQuery("from Fotografo").getResultList();
    }

    @Override
    public Fotografo buscar(long id){
        return entityManager().find(Fotografo.class, id);
    }

}
