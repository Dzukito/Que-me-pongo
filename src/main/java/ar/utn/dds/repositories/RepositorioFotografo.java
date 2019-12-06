package ar.utn.dds.repositories;

import java.util.List;

import ar.utn.dds.modelo.Fotografo;
import ar.utn.dds.repositories.DAO.DAO;

public class RepositorioFotografo  extends Repositorio {

	private static RepositorioFotografo instance;
	
	public static RepositorioFotografo getInstance(DAO dao) {
		if(instance == null){
			instance = new RepositorioFotografo(dao);
		}
		return instance;
	}

	    private RepositorioFotografo(DAO dao){
	        this.setDao(dao);
	    }

	    public List<Fotografo> buscarTodos(){
	        return this.dao.buscarTodos();
	    }

	    public Fotografo buscar(long id){
	        return this.dao.buscar(id);
	    }
	
}
