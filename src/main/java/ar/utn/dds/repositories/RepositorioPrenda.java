package ar.utn.dds.repositories;

import ar.utn.dds.modelo.Prenda;
import ar.utn.dds.DAO.DAO;

import java.util.List;

public class RepositorioPrenda extends Repositorio {
    private static RepositorioPrenda instance;

    public static RepositorioPrenda getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioPrenda(dao);
        }
        return instance;
    }

    private RepositorioPrenda(DAO dao){
        this.setDao(dao);
    }

    public List<Prenda> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Prenda buscar(long id){
        return this.dao.buscar(id);
    }
}
