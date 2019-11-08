package ar.utn.dds.models;

import ar.utn.dds.modelo.Membrecia;

import java.util.List;

public class MembreciaModel extends Model {
    private static MembreciaModel instance;

    public static MembreciaModel getInstance() {
        if(instance == null){
            instance = new MembreciaModel();
        }
        return instance;
    }

    private MembreciaModel(){

    }

    @Override
    public List<Membrecia> buscarTodos() {
        return entityManager().createQuery("from Membrecia").getResultList();
    }

    @Override
    public Membrecia buscar(long id) {
        return entityManager().find(Membrecia.class, id);
    }
}
