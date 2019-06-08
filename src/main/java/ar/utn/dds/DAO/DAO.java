package ar.utn.dds.DAO;

import java.util.List;

public interface DAO<T, K> {
    void insertar(T a);
    void modificar(T a);
    void eliminar(T a);
    List<T> obtenerTodos();
    T obtener(K id);
}
