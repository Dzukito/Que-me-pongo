package ar.utn.dds.repositories;

import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioAtuendo extends Repositorio {
    private static RepositorioAtuendo instance;

    public static RepositorioAtuendo getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioAtuendo(dao);
        }
        return instance;
    }

    private RepositorioAtuendo(DAO dao){
        this.setDao(dao);
    }

    public List<Atuendo> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Atuendo buscar(long id){
        return this.dao.buscar(id);
    }
}
