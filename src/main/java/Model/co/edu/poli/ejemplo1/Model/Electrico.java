package Model.co.edu.poli.ejemplo1.Model;

public class Electrico extends Producto {

    private String voltaje;

    public Electrico(String idProducto, String tipo, String descripcion, String voltaje) {
        super(idProducto, tipo, descripcion);
        this.voltaje = voltaje;
    }

    public String getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(String voltaje) {
        this.voltaje = voltaje;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Electrico clone() {
        return (Electrico) super.clone();
    }
}
