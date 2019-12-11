package ar.utn.dds.models;

import ar.utn.dds.modelo.clases.Evento;

import java.util.List;

public class EventoModel extends Model {
    private static EventoModel instance;

    public static EventoModel getInstance() {
        if(instance == null){
            instance = new EventoModel();
        }
        return instance;
    }

    @Override
    public List<Evento> buscarTodos(){
        return entityManager().createQuery("from Evento").getResultList();
    }

    @Override
    public Evento buscar(long id){
        return entityManager().find(Evento.class, id);
    }

}
