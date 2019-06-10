package ar.utn.dds.DAO;

import ar.utn.dds.modelo.TipoPrenda;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class TipoPrendaDAO implements DAO<TipoPrenda, String> {
    @Override
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
        /*FileReader fr = new FileReader("TiposDePrendas.json");
        Gson gson = new Gson();
        Type tipoListaTipoPrenda = new TypeToken<List<TipoPrenda>> (){}.getType();
        List<TipoPrenda> tiposDePrendas = gson.fromJson(fr,tipoListaTipoPrenda);*/

        return null;
    }

    @Override
    public TipoPrenda obtener(String id) {
        return null;
    }
}
