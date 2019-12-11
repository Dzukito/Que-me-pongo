package ar.utn.dds.repositories;

import ar.utn.dds.modelo.clases.Guardaropa;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioGuardaropa extends Repositorio {
    private static RepositorioGuardaropa instance;

    public static RepositorioGuardaropa getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioGuardaropa(dao);
        }
        return instance;
    }

    private RepositorioGuardaropa(DAO dao){
        this.setDao(dao);
    }

    public List<Guardaropa> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Guardaropa buscar(long id){
        return this.dao.buscar(id);
    }
}
