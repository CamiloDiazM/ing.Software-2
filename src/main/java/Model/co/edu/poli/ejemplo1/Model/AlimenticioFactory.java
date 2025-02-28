package Model.co.edu.poli.ejemplo1.Model;

public class AlimenticioFactory implements ProductoFactory {
    @Override
    public Producto crearProducto(String id, String tipo, String descripcion, String calorias) {
        return new Alimenticio(id, tipo, descripcion, calorias);
    }
}
