package ar.utn.dds.repositories;

import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioOutfit extends Repositorio {
    private static RepositorioOutfit instance;

    public static RepositorioOutfit getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioOutfit(dao);
        }
        return instance;
    }

    private RepositorioOutfit(DAO dao){
        this.setDao(dao);
    }

    public List<Atuendo> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Atuendo buscar(int id){
        return this.dao.buscar(id);
    }
}
