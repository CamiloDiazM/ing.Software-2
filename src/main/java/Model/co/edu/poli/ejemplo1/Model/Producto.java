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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}