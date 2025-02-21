package Model.co.edu.poli.ejemplo1.Model;

public class Electrico {
    private String idProducto;
    private String descripcion;
    private String voltaje;

    public Electrico(String idProducto, String descripcion, String voltaje) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.voltaje = voltaje;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getVoltaje() {
        return voltaje;
    }

    @Override
    public String toString() {
        return "Electrico{" +
                "idProducto='" + idProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", voltaje='" + voltaje + '\'' +
                '}';
    }
}
