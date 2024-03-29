package ar.utn.dds.repositories.DAO;

import java.util.List;

//public interface DAO<T, K> {
public interface DAO {
//    void insertar(T a);
//    void modificar(T a);
//    void eliminar(T a);
//    List<T> obtenerTodos();
//    T obtener(K id);
    public <T> List<T> buscarTodos();
    public <T> T buscar(long id);
    public void agregar(Object unObjeto);
    public void modificar(Object unObjeto);
    public void eliminar(Object unObjeto);
}
