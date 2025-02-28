package Model.co.edu.poli.ejemplo1.Model;

public class ElectricoFactory implements ProductoFactory {
    @Override
    public Producto crearProducto(String id, String tipo, String descripcion, String voltaje) {
        return new Electrico(id, tipo, descripcion, voltaje);
    }
}
