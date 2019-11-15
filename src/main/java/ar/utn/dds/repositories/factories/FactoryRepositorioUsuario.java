package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.UsuarioModel;
import ar.utn.dds.repositories.RepositorioUsuario;
import ar.utn.dds.repositories.DAO.DAOMySQL;

public class FactoryRepositorioUsuario {
    private static RepositorioUsuario repo;

    public static RepositorioUsuario get(){
    	if(repo == null){
            
            repo = RepositorioUsuario.getInstance(new DAOMySQL(UsuarioModel.getInstance()));
        
     }
        return repo;
    }
}
