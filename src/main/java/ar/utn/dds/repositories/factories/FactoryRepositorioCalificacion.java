package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.CalificacionModel;
import ar.utn.dds.repositories.RepositorioCalificacion;
import ar.utn.dds.repositories.DAO.DAOMySQL;

public class FactoryRepositorioCalificacion {

	private static RepositorioCalificacion repo;

    public static RepositorioCalificacion get(){
        if(repo == null){
           
               repo = RepositorioCalificacion.getInstance(new DAOMySQL(CalificacionModel.getInstance()));
           
        }
        return repo;
    }
}
