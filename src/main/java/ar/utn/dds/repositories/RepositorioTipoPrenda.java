package ar.utn.dds.repositories;

import ar.utn.dds.modelo.TipoPrenda;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioTipoPrenda extends Repositorio {
    private static RepositorioTipoPrenda instance;

    public static RepositorioTipoPrenda getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioTipoPrenda(dao);
        }
        return instance;
    }

    private RepositorioTipoPrenda(DAO dao){
        this.setDao(dao);
    }

    public List<TipoPrenda> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public TipoPrenda buscar(long id){
        return this.dao.buscar(id);
    }
}
