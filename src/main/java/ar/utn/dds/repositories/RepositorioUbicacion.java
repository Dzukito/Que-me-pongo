package ar.utn.dds.repositories;

import ar.utn.dds.modelo.clases.Ubicacion;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioUbicacion extends Repositorio {
    private static RepositorioUbicacion instance;

    public static RepositorioUbicacion getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioUbicacion(dao);
        }
        return instance;
    }

    private RepositorioUbicacion(DAO dao){
        this.setDao(dao);
    }

    public List<Ubicacion> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Ubicacion buscar(long id){
        return this.dao.buscar(id);
    }
}
