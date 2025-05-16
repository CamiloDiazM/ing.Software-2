package co.edu.poli.ejercicio.Model;

import co.edu.poli.ejercicio.Model.Visitor.ClaseVisitable;
import co.edu.poli.ejercicio.Model.Visitor.Visitante;

public class Producto implements ClaseVisitable {

    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String aceptar(Visitante visitor) {
        return visitor.visitar(this);
    }

}
