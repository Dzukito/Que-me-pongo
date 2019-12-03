package ar.utn.dds.models;

import ar.utn.dds.modelo.Prenda;

import java.util.List;

public class PrendaModel extends Model {
    private static PrendaModel instance;

    public static PrendaModel getInstance() {
        if(instance == null){
            instance = new PrendaModel();
        }
        return instance;
    }

    @Override
    public List<Prenda> buscarTodos(){
        return entityManager().createQuery("from Prenda").getResultList();
    }

    @Override
    public Prenda buscar(long id){
        return entityManager().find(Prenda.class, id);
    }

}
