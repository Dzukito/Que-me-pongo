package ar.utn.dds.repositories.factories;

import ar.utn.dds.config.Config;
import ar.utn.dds.models.PrendaModel;
import ar.utn.dds.repositories.RepositorioPrenda;
import ar.utn.dds.repositories.DAO.DAOMySQL;

public class FactoryRepositorioPrenda {
	private static RepositorioPrenda repo;

    public static RepositorioPrenda get(){
        if(repo == null){
           
               repo = RepositorioPrenda.getInstance(new DAOMySQL(PrendaModel.getInstance()));
           
        }
        return repo;
    }

}
