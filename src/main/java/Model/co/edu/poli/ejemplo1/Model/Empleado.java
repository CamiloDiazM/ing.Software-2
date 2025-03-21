package Model.co.edu.poli.ejemplo1.Model;

public class Empleado implements Componente {
    private String id;
    private String nombre;

    public Empleado(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public void mostrarDetalles(StringBuilder sb) {
        sb.append("Empleado [ID=").append(id).append(", Nombre=").append(nombre).append("]\n");
    }
}