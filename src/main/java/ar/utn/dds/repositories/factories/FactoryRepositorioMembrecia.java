package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.MembreciaModel;
import ar.utn.dds.repositories.RepositorioMembrecia;
import ar.utn.dds.DAO.DAOMySQL;

public class FactoryRepositorioMembrecia {
    private static RepositorioMembrecia repo;

    public static RepositorioMembrecia get(){
if(repo == null){
            
            repo = RepositorioMembrecia.getInstance(new DAOMySQL(MembreciaModel.getInstance()));
        
     }
        return repo;
    }
}
