package co.edu.poli.ejercicio.Model;

public class Cliente {
    private String nombre;
    private String correo;
    private String id;

    public Cliente(String nombre, String correo, String id) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
