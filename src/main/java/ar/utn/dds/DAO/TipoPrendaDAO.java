package ar.utn.dds.DAO;

import ar.utn.dds.modelo.TipoPrenda;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

//public class TipoPrendaDAO implements DAO<TipoPrenda, String> {
public class TipoPrendaDAO implements DAO{
/*    @Override
    public void insertar(TipoPrenda a) {

    }

    @Override
    public void modificar(TipoPrenda a) {

    }

    @Override
    public void eliminar(TipoPrenda a) {

    }

    @Override
    public List<TipoPrenda> obtenerTodos() {
        FileReader fr = new FileReader("TiposDePrendas.json");
        Gson gson = new Gson();
        Type tipoListaTipoPrenda = new TypeToken<List<TipoPrenda>> (){}.getType();
        List<TipoPrenda> tiposDePrendas = gson.fromJson(fr,tipoListaTipoPrenda);

        return null;
    }

    @Override
    public TipoPrenda obtener(String id) {
        return null;
    }
*/
	@Override
	public <T> List<T> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T buscar(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregar(Object unObjeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(Object unObjeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Object unObjeto) {
		// TODO Auto-generated method stub
		
	}
}
