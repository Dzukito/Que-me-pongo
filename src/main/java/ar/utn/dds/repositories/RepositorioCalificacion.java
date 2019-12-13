package ar.utn.dds.repositories;

import java.util.List;

import ar.utn.dds.modelo.clases.CalificacionAtuendo;
import ar.utn.dds.repositories.DAO.DAO;

public class RepositorioCalificacion extends Repositorio{
	   private static RepositorioCalificacion instance;

	    public static RepositorioCalificacion getInstance(DAO dao) {
	        if(instance == null){
	            instance = new RepositorioCalificacion(dao);
	        }
	        return instance;
	    }

	    private RepositorioCalificacion(DAO dao){
	        this.setDao(dao);
	    }

	    public List<CalificacionAtuendo> buscarTodos(){
	        return this.dao.buscarTodos();
	    }

	    public CalificacionAtuendo buscar(long id){
	        return this.dao.buscar(id);
	    }
}
