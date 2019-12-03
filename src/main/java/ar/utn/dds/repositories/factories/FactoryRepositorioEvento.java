package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.EventoModel;
import ar.utn.dds.models.GuardaropaModel;
import ar.utn.dds.repositories.DAO.DAOMySQL;
import ar.utn.dds.repositories.RepositorioEvento;
import ar.utn.dds.repositories.RepositorioOutfit;

public class FactoryRepositorioEvento {
	private static RepositorioEvento repo;

    public static RepositorioEvento get(){
        if(repo == null){
               repo = RepositorioEvento.getInstance(new DAOMySQL(EventoModel.getInstance()));
        }
        return repo;
    }

}
