package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.AtuendoModel;
import ar.utn.dds.repositories.DAO.DAOMySQL;
import ar.utn.dds.repositories.RepositorioOutfit;

public class FactoryRepositorioAtuendo {
	private static RepositorioOutfit repo;

    public static RepositorioOutfit get(){
        if(repo == null){
           
               repo = RepositorioOutfit.getInstance(new DAOMySQL(AtuendoModel.getInstance()));
           
        }
        return repo;
    }

}
