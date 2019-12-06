package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.FotografoModel;
import ar.utn.dds.repositories.RepositorioFotografo;
import ar.utn.dds.repositories.DAO.DAOMySQL;

public class FactoryRepositorioFotografo {
	private static RepositorioFotografo repo;

    public static RepositorioFotografo get(){
        if(repo == null){
           
               repo = RepositorioFotografo.getInstance(new DAOMySQL(FotografoModel.getInstance()));
           
        }
        return repo;
    }

}
