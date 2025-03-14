package Model.co.edu.poli.ejemplo1.Model;

public class Alimenticio extends Producto {

    private String calorias;

    public Alimenticio(String id, String tipo, String descripcion, String calorias) {
        super(id, tipo, descripcion);
        this.calorias = calorias;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Alimenticio clone() {
        return (Alimenticio) super.clone();
    }
}
