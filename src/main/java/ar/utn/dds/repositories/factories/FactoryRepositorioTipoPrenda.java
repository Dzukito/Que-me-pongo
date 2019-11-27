package ar.utn.dds.repositories.factories;

import ar.utn.dds.models.TipoPrendaModel;
import ar.utn.dds.repositories.RepositorioTipoPrenda;
import ar.utn.dds.DAO.DAOMySQL;

public class FactoryRepositorioTipoPrenda {
    private static RepositorioTipoPrenda repo;

    public static RepositorioTipoPrenda get(){
    	if(repo == null){
            
            repo = RepositorioTipoPrenda.getInstance(new DAOMySQL(TipoPrendaModel.getInstance()));
        
     }
        return repo;
    }
}
