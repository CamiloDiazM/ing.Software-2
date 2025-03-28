package Model.co.edu.poli.ejemplo1.Model;

public class Empleado implements Componente {
    private String id;
    private String nombre;

    public Empleado(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String mostrarDetalles() {
        return "Empleado [ID=" + id + ", Nombre=" + nombre + "]\n";
    }
}