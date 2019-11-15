package ar.utn.dds.repositories;

import ar.utn.dds.modelo.Membrecia;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioMembrecia extends Repositorio {
    private static RepositorioMembrecia instance;

    public static RepositorioMembrecia getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioMembrecia(dao);
        }
        return instance;
    }

    private RepositorioMembrecia(DAO dao){
        this.setDao(dao);
    }

    public List<Membrecia> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Membrecia buscar(long id){
        return this.dao.buscar(id);
    }
}
