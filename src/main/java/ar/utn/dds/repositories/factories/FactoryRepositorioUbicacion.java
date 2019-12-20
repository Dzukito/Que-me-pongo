package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.UbicacionModel;
import ar.utn.dds.repositories.RepositorioUbicacion;
import ar.utn.dds.repositories.DAO.DAOMySQL;

public class FactoryRepositorioUbicacion {
	
	private static RepositorioUbicacion repo;

    public static RepositorioUbicacion get(){
        if(repo == null){
           
               repo = RepositorioUbicacion.getInstance(new DAOMySQL(UbicacionModel.getInstance()));
           
        }
        return repo;
    }

}
