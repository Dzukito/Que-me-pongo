package ar.utn.dds.models;

import ar.utn.dds.modelo.Usuario;

import java.util.List;

public class UsuarioModel extends Model {
    private static UsuarioModel instance;

    public static UsuarioModel getInstance() {
        if(instance == null){
            instance = new UsuarioModel();
        }
        return instance;
    }

    @Override
    public List<Usuario> buscarTodos(){
        return entityManager().createQuery("from Usuario").getResultList();
    }

    @Override
    public Usuario buscar(long id){
        return entityManager().find(Usuario.class, id);
    }

}
