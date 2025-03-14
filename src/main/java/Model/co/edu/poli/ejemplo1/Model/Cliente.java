package Model.co.edu.poli.ejemplo1.Model;

/**
 * 
 */
public class Cliente {

    public Cliente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    private String id;

    private String nombre;

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }





}