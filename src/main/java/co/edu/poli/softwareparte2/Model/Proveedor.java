package co.edu.poli.softwareparte2.Model;

public class Proveedor {

    private String nombre;

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Proveedor: " + nombre;
    }
}
