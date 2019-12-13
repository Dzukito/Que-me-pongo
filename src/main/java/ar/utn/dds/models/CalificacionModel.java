package ar.utn.dds.models;

import java.util.List;

import ar.utn.dds.modelo.clases.CalificacionAtuendo;

public class CalificacionModel extends Model {
	 private static CalificacionModel instance;

	    public static CalificacionModel getInstance() {
	        if(instance == null){
	            instance = new CalificacionModel();
	        }
	        return instance;
	    }

	    @Override
	    public List<CalificacionAtuendo> buscarTodos(){
	        return entityManager().createQuery("from CalificacionAtuendo").getResultList();
	    }

	    @Override
	    public CalificacionAtuendo buscar(long id){
	        return entityManager().find(CalificacionAtuendo.class, id);
	    }
}
