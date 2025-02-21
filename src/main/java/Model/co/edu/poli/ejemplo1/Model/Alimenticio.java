package Model.co.edu.poli.ejemplo1.Model;

public class Alimenticio {
    private String idProducto;
    private String descripcion;
    private String calorias;

    public Alimenticio(String idProducto, String descripcion, String calorias) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.calorias = calorias;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCalorias() {
        return calorias;
    }

    @Override
    public String toString() {
        return "Alimenticio{" +
                "idProducto='" + idProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", calorias='" + calorias + '\'' +
                '}';
    }
}
