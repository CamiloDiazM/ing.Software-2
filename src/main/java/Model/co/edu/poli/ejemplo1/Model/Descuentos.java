// src/main/java/Model/co/edu/poli/ejemplo1/Model/Descuentos.java
package Model.co.edu.poli.ejemplo1.Model;

public class Descuentos extends CarritoDecorator {
    private double descuento;

    public Descuentos(Carrito carrito, double descuento) {
        super(carrito);
        this.descuento = descuento;
    }

    @Override
    public double obtenerPrecio() {
        return super.obtenerPrecio() * (1 - descuento);
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " con un descuento del " + (descuento * 100) + "%.";
    }
}