package ar.utn.dds.models;

import ar.utn.dds.modelo.ropa.TipoPrenda;

import java.util.List;

public class TipoPrendaModel extends Model {
    private static TipoPrendaModel instance;

    public static TipoPrendaModel getInstance() {
        if(instance == null){
            instance = new TipoPrendaModel();
        }
        return instance;
    }

    private TipoPrendaModel(){

    }

    @Override
    public List<TipoPrenda> buscarTodos(){
        return entityManager().createQuery("from tipoprenda").getResultList();
    }

    @Override
    public TipoPrenda buscar(long id){
        return entityManager().find(TipoPrenda.class, id);
    }

}
