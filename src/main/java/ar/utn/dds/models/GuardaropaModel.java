package ar.utn.dds.models;

import ar.utn.dds.modelo.Guardaropa;

import java.util.List;

public class GuardaropaModel extends Model {
    private static GuardaropaModel instance;

    public static GuardaropaModel getInstance() {
        if(instance == null){
            instance = new GuardaropaModel();
        }
        return instance;
    }

    @Override
    public List<Guardaropa> buscarTodos(){
        return entityManager().createQuery("from Guardaropa").getResultList();
    }

    @Override
    public Guardaropa buscar(int id){
        return entityManager().find(Guardaropa.class, id);
    }

}
