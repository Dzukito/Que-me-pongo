package ar.utn.dds.repositories;

import ar.utn.dds.modelo.clases.Evento;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioEvento extends Repositorio {
    private static RepositorioEvento instance;

    public static RepositorioEvento getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioEvento(dao);
        }
        return instance;
    }

    private RepositorioEvento(DAO dao){
        this.setDao(dao);
    }

    public List<Evento> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Evento buscar(long id){
        return this.dao.buscar(id);
    }
}
