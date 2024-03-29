package ar.utn.dds.repositories;

import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.repositories.DAO.DAO;

import java.util.List;

public class RepositorioUsuario extends Repositorio {
    private static RepositorioUsuario instance;

    public static RepositorioUsuario getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioUsuario(dao);
        }
        return instance;
    }

    private RepositorioUsuario(DAO dao){
        this.setDao(dao);
    }

    public List<Usuario> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Usuario buscar(long id){
        return this.dao.buscar(id);
    }
}
