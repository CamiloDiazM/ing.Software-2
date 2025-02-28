package Model.co.edu.poli.ejemplo1.Services;

import Model.co.edu.poli.ejemplo1.Model.Producto;
import java.util.List;

public interface ProductoDAO extends DAO<Producto> {
    List<Producto> obtenerPorTipo(String tipo);
}
