package Model.co.edu.poli.ejemplo1.Services;

import java.util.List;

public interface DAO<T> {
    void registrar(T t);
    T obtenerPorId(String id);
    List<T> obtenerTodos();
    void actualizar(T t);
    void eliminar(String id);
}
