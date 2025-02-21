package Model.co.edu.poli.ejemplo1.Model;

public class Producto {

    public Producto(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    private String id;

    private String tipo;

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

}