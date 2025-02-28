package Model.co.edu.poli.ejemplo1.Model;

public interface ProductoFactory {
    Producto crearProducto(String id, String tipo, String descripcion, String especifico);
}
