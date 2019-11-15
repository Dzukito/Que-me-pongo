package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.GuardaropaModel;
import ar.utn.dds.repositories.RepositorioGuardaropa;
import ar.utn.dds.repositories.DAO.DAOMySQL;

public class FactoryRepositorioGuardaropa {
	private static RepositorioGuardaropa repo;

    public static RepositorioGuardaropa get(){
        if(repo == null){
           
               repo = RepositorioGuardaropa.getInstance(new DAOMySQL(GuardaropaModel.getInstance()));
           
        }
        return repo;
    }

}
