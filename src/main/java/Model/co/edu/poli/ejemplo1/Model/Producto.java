package Model.co.edu.poli.ejemplo1.Model;

/**
 * 
 */
public class Producto {

    public Producto(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    private String id;

    private String descripcion;

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}